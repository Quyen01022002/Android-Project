package com.example.shopfruits.Activity.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.ProductAdapter_Vendor;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLySPActivity extends AppCompatActivity {
    APIService apiService;
    ProductAdapter_Vendor productAdapter;
    List<Product> productList;
    RecyclerView rcProduct;
    ConstraintLayout ThemSP;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_sanpham);
        rcProduct=findViewById(R.id.rc_sanpham);
        ThemSP=findViewById(R.id.themsp);
        Intent intent = getIntent();
        String id= intent.getStringExtra("idst");
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);

        apiService.sanphamstore(Integer.parseInt(id)).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList=response.body();
                productAdapter=new ProductAdapter_Vendor(productList,QuanLySPActivity.this);
                rcProduct.setHasFixedSize(true);
                GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
                rcProduct.setLayoutManager(layoutManager);
                rcProduct.setAdapter(productAdapter);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        ThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(QuanLySPActivity.this,ThemSPDialog.class);
                startActivity(it);
            }
        });

        back=findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(QuanLySPActivity.this, VendorActivity.class);
                startActivity(it);
            }
        });

    }
}
