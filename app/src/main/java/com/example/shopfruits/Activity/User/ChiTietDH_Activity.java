package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chitietdonhang);
        XacNhan=findViewById(R.id.XacNhan_Constrian);

        Intent it=getIntent();
        TextView NguoiNhan=findViewById(R.id.tv_Ten);
        ImageView Avatar=findViewById(R.id.nguoiNhan_Avatar);
        User user=new User();
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

                                }

                                @Override
                                public void onFailure(Call<OrderEnity> call, Throwable t) {

                                }
                            });
                        }
                    });


                }
                if(TrangThai.getText().toString().equals("Đã Xác Nhận"))
                {   ConstraintLayout DaXacNhan,NutDaXacNhan;
                    ConstraintLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.chuaxacnhan);
                    ChuaXacNhan.setVisibility(View.VISIBLE);
                    TextView TV_Da_XacNhan;
                    TV_Da_XacNhan=findViewById(R.id.tv_Da_Xac_Nhan);
                    DaXacNhan=findViewById(R.id.Da_Xac_Nhan);
                    NutDaXacNhan=findViewById(R.id.Da_Xac_Nhan02);
                    TV_Da_XacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan.setVisibility(View.VISIBLE);
                    NutDaXacNhan.setVisibility(View.VISIBLE);



                }if(TrangThai.getText().toString().equals("Đã Đóng Gói"))
                {   ConstraintLayout DaXacNhan,NutDaXacNhan;
                    TextView TV_Da_XacNhan;
                    TV_Da_XacNhan=findViewById(R.id.tv_Da_Xac_Nhan);
                    DaXacNhan=findViewById(R.id.Da_Xac_Nhan);
                    NutDaXacNhan=findViewById(R.id.Da_Xac_Nhan02);
                    TV_Da_XacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan.setVisibility(View.VISIBLE);
                    NutDaXacNhan.setVisibility(View.VISIBLE);
                    ConstraintLayout DaDongGoi,NutDaDongGoi;
                    TextView Tv_DaDongGoi;
                    Tv_DaDongGoi=findViewById(R.id.tv_Da_Dong_Goi);
                    DaDongGoi=findViewById(R.id.Da_Dong_Goi);
                    NutDaDongGoi=findViewById(R.id.Nut_Da_Dong_Goi);
                    Tv_DaDongGoi.setVisibility(View.VISIBLE);
                    DaDongGoi.setVisibility(View.VISIBLE);
                    NutDaDongGoi.setVisibility(View.VISIBLE);

                    ConstraintLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.chuaxacnhan);
                    ChuaXacNhan.setVisibility(View.VISIBLE);



                }
                if(TrangThai.getText().toString().equals("Đang Giao"))
                {   ConstraintLayout DaXacNhan,NutDaXacNhan;
                    TextView TV_Da_XacNhan;
                    TV_Da_XacNhan=findViewById(R.id.tv_Da_Xac_Nhan);
                    DaXacNhan=findViewById(R.id.Da_Xac_Nhan);
                    NutDaXacNhan=findViewById(R.id.Da_Xac_Nhan02);
                    TV_Da_XacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan.setVisibility(View.VISIBLE);
                    NutDaXacNhan.setVisibility(View.VISIBLE);
                    //Đóng Gói
                    ConstraintLayout DaDongGoi,NutDaDongGoi;
                    TextView Tv_DaDongGoi;
                    Tv_DaDongGoi=findViewById(R.id.tv_Da_Dong_Goi);
                    DaDongGoi=findViewById(R.id.Da_Dong_Goi);
                    NutDaDongGoi=findViewById(R.id.Nut_Da_Dong_Goi);
                    Tv_DaDongGoi.setVisibility(View.VISIBLE);
                    DaDongGoi.setVisibility(View.VISIBLE);
                    NutDaDongGoi.setVisibility(View.VISIBLE);
                    //Đang Giao
                    ConstraintLayout DangGiao,NutDanggiao;
                    TextView Tv_DangGiao;
                    Tv_DangGiao=findViewById(R.id.tv_Dang_Giao);
                    DangGiao=findViewById(R.id.Dang_Giao);
                    NutDanggiao=findViewById(R.id.Nut_Dang_Giao);
                    Tv_DangGiao.setVisibility(View.VISIBLE);
                    DangGiao.setVisibility(View.VISIBLE);
                    NutDanggiao.setVisibility(View.VISIBLE);
                    ConstraintLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.chuaxacnhan);
                    ChuaXacNhan.setVisibility(View.VISIBLE);



                }
                if(TrangThai.getText().toString().equals("Đã Giao"))
                {   ConstraintLayout DaXacNhan,NutDaXacNhan;
                    TextView TV_Da_XacNhan;
                    TV_Da_XacNhan=findViewById(R.id.tv_Da_Xac_Nhan);
                    DaXacNhan=findViewById(R.id.Da_Xac_Nhan);
                    NutDaXacNhan=findViewById(R.id.Da_Xac_Nhan02);
                    TV_Da_XacNhan.setVisibility(View.VISIBLE);
                    DaXacNhan.setVisibility(View.VISIBLE);
                    NutDaXacNhan.setVisibility(View.VISIBLE);
                    //Đóng Gói
                    ConstraintLayout DaDongGoi,NutDaDongGoi;
                    TextView Tv_DaDongGoi;
                    Tv_DaDongGoi=findViewById(R.id.tv_Da_Dong_Goi);
                    DaDongGoi=findViewById(R.id.Da_Dong_Goi);
                    NutDaDongGoi=findViewById(R.id.Nut_Da_Dong_Goi);
                    Tv_DaDongGoi.setVisibility(View.VISIBLE);
                    DaDongGoi.setVisibility(View.VISIBLE);
                    NutDaDongGoi.setVisibility(View.VISIBLE);
                    //Đang Giao
                    ConstraintLayout DangGiao,NutDanggiao;
                    TextView Tv_DangGiao;
                    Tv_DangGiao=findViewById(R.id.tv_Dang_Giao);
                    DangGiao=findViewById(R.id.Dang_Giao);
                    NutDanggiao=findViewById(R.id.Nut_Dang_Giao);
                    Tv_DangGiao.setVisibility(View.VISIBLE);
                    DangGiao.setVisibility(View.VISIBLE);
                    NutDanggiao.setVisibility(View.VISIBLE);

                    //Đang Giao
                    ConstraintLayout DaGiao,Nut_DaGiao;
                    TextView tv_Da_Giao;
                    tv_Da_Giao=findViewById(R.id.tv_Da_Giao);
                    DaGiao=findViewById(R.id.Da_Giao);
                    Nut_DaGiao=findViewById(R.id.Nut_Da_Giao);
                    tv_Da_Giao.setVisibility(View.VISIBLE);
                    DaGiao.setVisibility(View.VISIBLE);
                    Nut_DaGiao.setVisibility(View.VISIBLE);

                    ConstraintLayout ChuaXacNhan;
                    ChuaXacNhan=findViewById(R.id.chuaxacnhan);
                    ChuaXacNhan.setVisibility(View.VISIBLE);
                    TextView xacnhan=findViewById(R.id.xacnhan);
                    xacnhan.setText("Đánh giá");
                    XacNhan=findViewById(R.id.XacNhan_Constrian);
                    XacNhan.setVisibility(View.VISIBLE);
                    XacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent it=new Intent(ChiTietDH_Activity.this,DanhGia.class);
                            it.putExtra("iddh",iddh);
                            startActivity(it);
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
