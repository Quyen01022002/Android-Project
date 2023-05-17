package com.example.shopfruits.Activity.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.User.MainActivity;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorActivity extends AppCompatActivity {
    APIService apiService;
    TextView tenshop;
    ImageView avatar;
    ConstraintLayout QL_SP,QL_DH, TK_DT, QL_KH, QL_ShP,DangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_home);
    tenshop=findViewById(R.id.tv_vendor_tenshop);
    avatar=findViewById(R.id.imageView8);
    QL_SP=findViewById(R.id.ql_sp);
    QL_DH=findViewById(R.id.ql_dh);
    TK_DT=findViewById(R.id.tk_dt);
    QL_KH=findViewById(R.id.ql_kh);
    QL_ShP=findViewById(R.id.ql_shp);
        DangXuat=findViewById(R.id.dangxuat);
        apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        Intent intent = getIntent();
        String id= intent.getStringExtra("idst");
        SharePrefManager.getInstance(getApplicationContext()).StoreID(Integer.parseInt(id));
        QL_SP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(VendorActivity.this, QuanLySPActivity.class);
                it.putExtra("idst", id);
                startActivity(it);
            }
        });
        QL_ShP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(VendorActivity.this, QuanLyShipperActivity.class);
                it.putExtra("idst", id);
                startActivity(it);
            }
        });
        QL_DH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(VendorActivity.this, QuanLyDonHang.class);
                it.putExtra("idst", id);
                startActivity(it);
            }
        });
        QL_KH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(VendorActivity.this, QuanLyKhachHang.class);
                it.putExtra("idst",id);
                startActivity(it);
            }
        });
        TK_DT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(VendorActivity.this, ThongKeActivity.class);
                it.putExtra("idst",id);
                startActivity(it);
            }
        });

        DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(VendorActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        apiService.getstorebyid(Integer.parseInt(id)).enqueue(new Callback<Stores>() {
            @Override
            public void onResponse(Call<Stores> call, Response<Stores> response) {
                Stores st=new Stores();
                st=response.body();
                tenshop.setText(st.getName());
                Glide.with(getApplicationContext()).load(st.getAvatar()).into(avatar);
            }

            @Override
            public void onFailure(Call<Stores> call, Throwable t) {

            }
        });




    }
}
