package com.example.shopfruits.Activity.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.ProductAdapter_Vendor;
import com.example.shopfruits.Adapter.UserAdapter;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyShipperActivity extends AppCompatActivity {
    APIService apiService;
    UserAdapter userAdapter;
    List<User> userList;
    RecyclerView rcUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_shipper);
        rcUser=findViewById(R.id.rc_shipper);
        Intent intent = getIntent();
        String id= intent.getStringExtra("idst");
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);

        apiService.getShipperOfStore(Integer.parseInt(id)).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList=response.body();
                userAdapter=new UserAdapter(userList, QuanLyShipperActivity.this);
                rcUser.setHasFixedSize(true);
                GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
                rcUser.setLayoutManager(layoutManager);
                rcUser.setAdapter(userAdapter);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }
}
