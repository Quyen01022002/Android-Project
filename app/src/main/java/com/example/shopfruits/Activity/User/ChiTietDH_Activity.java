package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.shopfruits.Adapter.ItemDonHangAdapter;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.Review;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDH_Activity extends AppCompatActivity {
    APIService apiService;
    RecyclerView itemDH;
    TextView TrangThai;
    ConstraintLayout XacNhan;
    OrderEnity orderEnity=new OrderEnity();
    User  user=new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chitietdonhang);
        XacNhan=findViewById(R.id.XacNhan_Constrian);

        Intent it=getIntent();
        TextView NguoiNhan=findViewById(R.id.tv_Ten);
        ImageView Avatar=findViewById(R.id.nguoiNhan_Avatar);

        user = SharePrefManager.getInstance(this).getUser();
        NguoiNhan.setText(user.getName());


        Glide.with(getApplicationContext()).load(user.getAvatar().toString().trim()).into(Avatar);

        int iddh=Integer.parseInt(it.getStringExtra("iddh"));
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getorder(iddh).enqueue(new Callback<OrderEnity>() {
            @Override
            public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                orderEnity=response.body();
                TrangThai=findViewById(R.id.tv_trangthai_dh);
                TrangThai.setText(orderEnity.getStatus());
                itemDH=findViewById(R.id.rc_product_dh);
                getitemproduct(orderEnity.getOrderID());

                if(TrangThai.getText().toString().equals("Chưa Xác Nhận"))
                {
                    XacNhan.setVisibility(View.VISIBLE);
                    XacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            orderEnity.setStatus("Đã Xác Nhận");
                            apiService.capnhat(orderEnity).enqueue(new Callback<OrderEnity>() {
                                @Override
                                public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                                        finish();
                                    Toast.makeText(ChiTietDH_Activity.this, "Đã xác nhận", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<OrderEnity> call, Throwable t) {

                                }
                            });
                        }
                    });


                }
                if(TrangThai.getText().toString().equals("Đã Xác Nhận"))
                {   ConstraintLayout DaXacNhan;
                    LinearLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.DaXN);
                    ChuaXacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan=findViewById(R.id.Da_XN);
                    DaXacNhan.setVisibility(View.VISIBLE);



                }if(TrangThai.getText().toString().equals("Đã Đóng Gói"))
                {   ConstraintLayout DaDonggoi;
                    LinearLayout DaDongGoi;
                    DaDonggoi=findViewById(R.id.Da_DG);
                    DaDongGoi=findViewById(R.id.Nut_Da_Dong_Goi);
                    DaDonggoi.setVisibility(View.VISIBLE);
                    DaDongGoi.setVisibility(View.VISIBLE);
                    ConstraintLayout DaXacNhan;
                    LinearLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.DaXN);
                    ChuaXacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan=findViewById(R.id.Da_XN);
                    DaXacNhan.setVisibility(View.VISIBLE);




                }
                if(TrangThai.getText().toString().equals("Đang Giao"))
                {   ConstraintLayout DangGiao;
                    LinearLayout Dang_Giao;
                    Dang_Giao=findViewById(R.id.Nut_Dang_Giao);
                    DangGiao=findViewById(R.id.Dang_Giao);
                   Dang_Giao.setVisibility(View.VISIBLE);
                    DangGiao.setVisibility(View.VISIBLE);
                    ConstraintLayout DaDonggoi;
                    LinearLayout DaDongGoi;
                    DaDonggoi=findViewById(R.id.Da_DG);
                    DaDongGoi=findViewById(R.id.Nut_Da_Dong_Goi);
                    DaDonggoi.setVisibility(View.VISIBLE);
                    DaDongGoi.setVisibility(View.VISIBLE);
                    ConstraintLayout DaXacNhan;
                    LinearLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.DaXN);
                    ChuaXacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan=findViewById(R.id.Da_XN);
                    DaXacNhan.setVisibility(View.VISIBLE);


                }
                if(TrangThai.getText().toString().equals("Đã Giao"))
                {
                    ConstraintLayout DaGiao;
                    LinearLayout Da_Giao;
                    DaGiao=findViewById(R.id.Da_Giao);
                    Da_Giao=findViewById(R.id.DaGiao_lin);
                    Da_Giao.setVisibility(View.VISIBLE);
                    DaGiao.setVisibility(View.VISIBLE);
                    ConstraintLayout DangGiao;
                    LinearLayout Dang_Giao;
                    Dang_Giao=findViewById(R.id.Nut_Dang_Giao);
                    DangGiao=findViewById(R.id.Dang_Giao);
                    Dang_Giao.setVisibility(View.GONE);
                    DangGiao.setVisibility(View.GONE);
                    ConstraintLayout DaDonggoi;
                    LinearLayout DaDongGoi;
                    DaDonggoi=findViewById(R.id.Da_DG);
                    DaDongGoi=findViewById(R.id.Nut_Da_Dong_Goi);
                    DaDonggoi.setVisibility(View.VISIBLE);
                    DaDongGoi.setVisibility(View.VISIBLE);
                    ConstraintLayout DaXacNhan;
                    LinearLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.DaXN);
                    ChuaXacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan=findViewById(R.id.Da_XN);
                    DaXacNhan.setVisibility(View.VISIBLE);
                    apiService.checkdanhgia(iddh,user.getUserID()).enqueue(new Callback<List<Review>>() {
                        @Override
                        public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                            List<Review> rv=response.body();
                            if(rv.size()==0)
                            {
                                XacNhan=findViewById(R.id.XacNhan_Constrian);
                                XacNhan.setVisibility(View.VISIBLE);
                                TextView Danhgia=findViewById(R.id.xacnhan);
                                Danhgia.setText("Đánh Giá");

                                XacNhan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent it=new Intent(ChiTietDH_Activity.this,DanhGia.class);
                                        it.putExtra("iddh",iddh);
                                        startActivity(it);
                                    }
                                });


                            }                        }

                        @Override
                        public void onFailure(Call<List<Review>> call, Throwable t) {

                        }
                    });




                }



            }

            @Override
            public void onFailure(Call<OrderEnity> call, Throwable t) {

            }
        });

    }

    public void getitemproduct(int id)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getitemdh(id).enqueue(new Callback<List<DonHangModel>>() {
            @Override
            public void onResponse(Call<List<DonHangModel>> call, Response<List<DonHangModel>> response) {
                List<DonHangModel> pd;
                pd=response.body();
                ItemDonHangAdapter cartAdapter= new ItemDonHangAdapter(pd,ChiTietDH_Activity.this);
                itemDH.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(ChiTietDH_Activity.this, LinearLayoutManager.VERTICAL, false);
                itemDH.setLayoutManager(layoutManager);
                itemDH.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<DonHangModel>> call, Throwable t) {

            }
        });
    }
}
