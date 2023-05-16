package com.example.shopfruits.Activity.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.CartAdapter;
import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.CartItem;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    APIService apiService;
    RecyclerView rcitemcart;
    ConstraintLayout QuayLai;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.cart_activity);
        rcitemcart=findViewById(R.id.cartview);
        QuayLai=findViewById(R.id.quaylai_cart);
        QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CartActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        
        String id= intent.getStringExtra("iditem");

        String idstore= intent.getStringExtra("idstore");



        if(id==null)
        {
            GetItemCart();
        }
        else {
            int iditem=Integer.parseInt(id);
            int stroreid=Integer.parseInt(idstore);
            SaveCartItem(iditem,stroreid);
            GetItemCart();
        }


    }

    public void GetItemCart() {
        int iduser = SharePrefManager.getInstance(this).getuserID();

        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.cartitem(iduser).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> pd;
                pd=response.body();
                CartAdapter cartAdapter= new CartAdapter(pd,CartActivity.this);
                rcitemcart.setHasFixedSize(true);
                GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
                rcitemcart.setLayoutManager(layoutManager);
                rcitemcart.setAdapter(cartAdapter);

            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void SaveCartItem(int id,int storeid) {
        int cartID = SharePrefManager.getInstance(this).getcartID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        CartItem cartItem=new CartItem();
        cartItem.setCartID(cartID);
        cartItem.setProductID(id);
        cartItem.setStoreID(storeid);
        cartItem.setCount(1);
        apiService.save(cartItem).enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {

                CartItem dathem=new CartItem();
                dathem=response.body();


            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
            }
        });

    }




}





