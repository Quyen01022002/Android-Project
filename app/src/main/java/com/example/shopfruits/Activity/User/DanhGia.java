package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.ItemDonHangAdapter;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.Review;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhGia extends AppCompatActivity {
    APIService apiService;
    RecyclerView itemDH;
    float rating=0;
    List<DonHangModel> pd=new ArrayList<>();
    ImageView start_1,start_2,start_3,start_4,start_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.danhgiadonhang);
        AnhXa();

        start_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_1.setBackgroundResource(R.drawable.star);
                start_2.setBackgroundResource(R.drawable.start);
                start_3.setBackgroundResource(R.drawable.start);
                start_4.setBackgroundResource(R.drawable.start);
                start_5.setBackgroundResource(R.drawable.start);
                rating=1;

            }
        });
        start_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_1.setBackgroundResource(R.drawable.star);
                start_2.setBackgroundResource(R.drawable.star);
                start_3.setBackgroundResource(R.drawable.start);
                start_4.setBackgroundResource(R.drawable.start);
                start_5.setBackgroundResource(R.drawable.start);
                rating=2;
            }
        });
        start_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_1.setBackgroundResource(R.drawable.star);
                start_2.setBackgroundResource(R.drawable.star);
                start_3.setBackgroundResource(R.drawable.star);
                start_4.setBackgroundResource(R.drawable.start);
                start_5.setBackgroundResource(R.drawable.start);
                rating=3;
            }
        });

 start_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_1.setBackgroundResource(R.drawable.star);
                start_2.setBackgroundResource(R.drawable.star);
                start_3.setBackgroundResource(R.drawable.star);
                start_4.setBackgroundResource(R.drawable.star);
                start_5.setBackgroundResource(R.drawable.start);
                rating=4;
            }
        });

 start_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start_1.setBackgroundResource(R.drawable.star);
                start_2.setBackgroundResource(R.drawable.star);
                start_3.setBackgroundResource(R.drawable.star);
                start_4.setBackgroundResource(R.drawable.star);
                start_5.setBackgroundResource(R.drawable.star);
                rating=5;
            }
        });
        Intent it=getIntent();
        int iddh=it.getIntExtra("iddh",0);
        getitemproduct(iddh);
        ConstraintLayout guidanhgia=findViewById(R.id.constraintLayout28);
        guidanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                danhgia(iddh);
            }
        });



    }
    public void AnhXa()
    {
        start_1=findViewById(R.id.imagest1_dg);
        start_2=findViewById(R.id.imagest2_dg);
        start_3=findViewById(R.id.imagest3_dg);
        start_4=findViewById(R.id.imagest4_dg);
        start_5=findViewById(R.id.imagest5_dg);
        itemDH=findViewById(R.id.rc_product_dh);
    }
    public void danhgia(int id)
    {
        EditText content=findViewById(R.id.content);
        int useriD = SharePrefManager.getInstance(DanhGia.this).getuserID();
        Review rv=new Review();
        rv.setOrderID(id);
        rv.setRating(rating);
        rv.setProductID(pd.get(0).getProductID());
        rv.setStoreID(pd.get(0).getStoreID());
         rv.setUserID(useriD);
         rv.setContents(content.getText().toString());
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.Danhgia(rv).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {

            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {

            }
        });
    }
    public void getitemproduct(int id)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getitemdh(id).enqueue(new Callback<List<DonHangModel>>() {
            @Override
            public void onResponse(Call<List<DonHangModel>> call, Response<List<DonHangModel>> response) {

                pd=response.body();
                ItemDonHangAdapter cartAdapter= new ItemDonHangAdapter(pd,DanhGia.this);
                itemDH.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(DanhGia.this, LinearLayoutManager.VERTICAL, false);
                itemDH.setLayoutManager(layoutManager);
                itemDH.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<DonHangModel>> call, Throwable t) {

            }
        });
    }
}
