package com.example.shopfruits.Activity.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.User.MainActivity;
import com.example.shopfruits.Activity.User.ThanhToanDialog;
import com.example.shopfruits.Adapter.ReviewAdapter;
import com.example.shopfruits.Models.FollowUsserModel;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.ReviewModel;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietAcivity_Shop extends AppCompatActivity {
    APIService apiService;
    TextView Ten,MaSP,DaBan,TonKho,Gia;
    CheckBox checkBox;
    Product pd;
    ImageView anh;
    RatingBar ratingBar;
    ImageView QuayLai;
    List<FollowUsserModel> fl=new ArrayList<>();
    ConstraintLayout danhgia,mota,chinhsua,ThongTin,ngungban;
    ScrollView danhgialist;
    private static final int REQUEST_CODE_MY_DIALOG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.chitietsanpham_vendor);
        AnhXa();
        GetItemProduct();

        danhgialist=findViewById(R.id.danhgia_list);
        Intent intent = getIntent();
        String id= intent.getStringExtra("idProduct");
        int idsp=Integer.parseInt (id);
        chinhsua=findViewById(R.id.chinhsuasanpham);
        chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it=new Intent(ChiTietAcivity_Shop.this,ChinhSuaSPDialog.class);
            it.putExtra("idProduct",idsp);
            startActivity(it);
            }
        });
        mota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mota.setBackgroundResource(R.drawable.mota_bottom);
                danhgialist.setVisibility(View.GONE);
                danhgia.setBackgroundDrawable(null);
                ThongTin.setVisibility(View.VISIBLE);
            }
        });

        danhgia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RecyclerView DanhGia_RC=findViewById(R.id.RC_Danhgia);
                danhgia.setBackgroundResource(R.drawable.mota_bottom);
                danhgialist.setVisibility(View.VISIBLE);
                mota.setBackgroundDrawable(null);
                ThongTin.setVisibility(View.GONE);
                apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
                apiService.getlistdanhgia(idsp).enqueue(new Callback<List<ReviewModel>>() {
                    @Override
                    public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {
                        List<ReviewModel> or=response.body();

                       ReviewAdapter danhgiaAdapter=new ReviewAdapter(or, ChiTietAcivity_Shop.this);
                        DanhGia_RC.setHasFixedSize(true);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(ChiTietAcivity_Shop.this, LinearLayoutManager.VERTICAL, false);

                        DanhGia_RC.setLayoutManager(layoutManager);
                        DanhGia_RC.setAdapter(danhgiaAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<ReviewModel>> call, Throwable t) {

                    }
                });
            }
        });

    }


    public void GetItemProduct() {
        Intent intent = getIntent();
        String id= intent.getStringExtra("idProduct");
        int idsp=Integer.parseInt (id);
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.chitiet(idsp).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                 pd=response.body();
                Ten.setText(pd.getName());
                ratingBar.setRating(pd.getRating());
                MaSP.setText(String.valueOf(pd.getProductID()));
                Gia.setText(String.valueOf(pd.getPrice()));
                TonKho.setText(String.valueOf(pd.getQuantity()));
                DaBan.setText(String.valueOf(pd.getSold()));
                if(pd.getActive()==true && pd.getSelling()==true)
                {
                    checkBox.setChecked(true);
                    ngungban.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            pd.setSelling(false);
                            apiService.updateproduct(pd).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    Toast.makeText(ChiTietAcivity_Shop.this, "Đã ngừng bán", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onFailure(Call<Product> call, Throwable t) {

                                }
                            });
                        }
                    });
                }
                else
                {
                    checkBox.setChecked(false);
                    TextView banlai=findViewById(R.id.textView2);
                    banlai.setText("Bán Lại");
                    ngungban.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            pd.setSelling(true);
                            apiService.updateproduct(pd).enqueue(new Callback<Product>() {
                                @Override
                                public void onResponse(Call<Product> call, Response<Product> response) {
                                    Toast.makeText(ChiTietAcivity_Shop.this, "Đã mở bán lại", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onFailure(Call<Product> call, Throwable t) {

                                }
                            });
                        }
                    });
                }
                Glide.with(getApplicationContext()).load(pd.getImg().toString().trim()).into(anh);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ChiTietAcivity_Shop.this,t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void AnhXa()
    {
        Ten=findViewById(R.id.textView32);
        MaSP=findViewById(R.id.textView33);
        anh=findViewById(R.id.imageView10);
        QuayLai=findViewById(R.id.quaylai);
        danhgia=findViewById(R.id.danhgia);
        mota=findViewById(R.id.mota);
        DaBan=findViewById(R.id.daban);
        TonKho=findViewById(R.id.textView44);
        checkBox=findViewById(R.id.checkBox2);
        Gia=findViewById(R.id.textView48);
        ThongTin=findViewById(R.id.thongtin);
        ratingBar=findViewById(R.id.ratingBar);
        ngungban=findViewById(R.id.ngungban);


    }
}
