package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietAcivity extends AppCompatActivity {
    APIService apiService;
    TextView Ten,Gia,MoTa,mua;
    ImageView anh;
    ImageView QuayLai;
    private static final int REQUEST_CODE_MY_DIALOG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chitiet_activity);
        AnhXa();
        GetItemProduct();
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

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ChiTietAcivity.this,t.getMessage() , Toast.LENGTH_SHORT).show();
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

    }
}