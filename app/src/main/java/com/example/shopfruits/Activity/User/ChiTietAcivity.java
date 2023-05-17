package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.example.shopfruits.Adapter.DonHang_ShopAdapter;
import com.example.shopfruits.Adapter.ReviewAdapter;
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Models.FollowUsserModel;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.ReviewModel;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietAcivity extends AppCompatActivity {
    APIService apiService;
    TextView Ten,Gia,MoTa,mua,tenshop;
    ImageView anh,avatar;
    ImageView QuayLai;
    List<FollowUsserModel> fl=new ArrayList<>();
    ConstraintLayout danhgia,mota;
    ScrollView danhgialist;
    TextView theodoi;
    ImageView start_1,start_2,start_3,start_4,start_5;
    private static final int REQUEST_CODE_MY_DIALOG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.chitiet_activity);
        AnhXa();
        GetItemProduct();
        danhgialist=findViewById(R.id.danhgia_list);
        Intent intent = getIntent();
        String id= intent.getStringExtra("id");
        int idsp=Integer.parseInt (id);
    avatar=findViewById(R.id.imageView10);
        mota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mota.setBackgroundResource(R.drawable.mota_bottom);
                MoTa.setVisibility(View.VISIBLE);
                danhgialist.setVisibility(View.GONE);
                danhgia.setBackgroundDrawable(null);
            }
        });
        danhgia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RecyclerView DanhGia_RC=findViewById(R.id.RC_Danhgia);
                danhgia.setBackgroundResource(R.drawable.mota_bottom);
                MoTa.setVisibility(View.GONE);
                danhgialist.setVisibility(View.VISIBLE);
                mota.setBackgroundDrawable(null);
                apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
                apiService.getlistdanhgia(idsp).enqueue(new Callback<List<ReviewModel>>() {
                    @Override
                    public void onResponse(Call<List<ReviewModel>> call, Response<List<ReviewModel>> response) {
                        List<ReviewModel> or=response.body();

                       ReviewAdapter danhgiaAdapter=new ReviewAdapter(or, ChiTietAcivity.this);
                        DanhGia_RC.setHasFixedSize(true);
                        LinearLayoutManager layoutManager
                                = new LinearLayoutManager(ChiTietAcivity.this, LinearLayoutManager.VERTICAL, false);

                        DanhGia_RC.setLayoutManager(layoutManager);
                        DanhGia_RC.setAdapter(danhgiaAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<ReviewModel>> call, Throwable t) {

                    }
                });
            }
        });
        mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = getIntent();

                String id= it.getStringExtra("id");
                Intent intent = new Intent(ChiTietAcivity.this, ThanhToanDialog.class);
                int idsp=Integer.parseInt (id);
                intent.putExtra("idsp",idsp);
                startActivityForResult(intent, REQUEST_CODE_MY_DIALOG);

            }
        });
        QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ChiTietAcivity.this, MainActivity.class);
                startActivity(it);
            }
        });

    }


    public void GetItemProduct() {
        Intent intent = getIntent();
        String id= intent.getStringExtra("id");
        int idsp=Integer.parseInt (id);
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.chitiet(idsp).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product pd=response.body();
                Ten.setText(pd.getName());
                String gia=String.valueOf(pd.getPrice());
                Gia.setText(gia);
                MoTa.setText(pd.getDescription());
                Glide.with(getApplicationContext()).load(pd.getImg().toString().trim()).into(anh);
                apiService.getstorebyid(pd.getStoreID()).enqueue(new Callback<Stores>() {
                    @Override
                    public void onResponse(Call<Stores> call, Response<Stores> response) {
                        Stores st=new Stores();
                        st=response.body();
                        tenshop.setText(st.getName());
                        checkFollow(st.getStoreID());
                        Glide.with(getApplicationContext()).load(st.getAvatar().toString().trim()).into(avatar);
                    }

                    @Override
                    public void onFailure(Call<Stores> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ChiTietAcivity.this,t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkFollow(int strid)
    {
        int iduser = SharePrefManager.getInstance(this).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.CheckFollow(iduser,strid).enqueue(new Callback<List<FollowUsserModel>>() {
            @Override
            public void onResponse(Call<List<FollowUsserModel>> call, Response<List<FollowUsserModel>> response) {

                fl=response.body();
                if(fl.size()==0)
                {
                    theodoi.setText("Theo Dõi");
                    theodoi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            follow(strid,iduser);
                            finish();
                        }
                    });
                }
                else {
                    theodoi.setText("Đang Theo Dõi");
                    theodoi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            unfollow(fl.get(0).getId());
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<FollowUsserModel>> call, Throwable t) {

            }
        });


    }
    public void unfollow(int id)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        FollowUsserModel fl=new FollowUsserModel();

        apiService.unfollow(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
public void follow(int stid,int userid)
{
    apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
    FollowUsserModel fl=new FollowUsserModel();
    fl.setIsDeleted(true);
    fl.setStoreID(stid);
    fl.setUserID(userid);
    apiService.follow(fl).enqueue(new Callback<FollowUsserModel>() {
        @Override
        public void onResponse(Call<FollowUsserModel> call, Response<FollowUsserModel> response) {

        }

        @Override
        public void onFailure(Call<FollowUsserModel> call, Throwable t) {

        }
    });
}


    public void AnhXa()
    {
        Ten=findViewById(R.id.tv_nameSPItem);
        Gia=findViewById(R.id.tv_GiaItem);
        MoTa=findViewById(R.id.tv_mota);
        anh=findViewById(R.id.itemsp);
        mua=findViewById(R.id.tv_mua);
        QuayLai=findViewById(R.id.quaylai);
        mota=findViewById(R.id.mota);
        danhgia=findViewById(R.id.danhgia);
        tenshop=findViewById(R.id.tenshop);
        theodoi=findViewById(R.id.theodoi);

    }
}
