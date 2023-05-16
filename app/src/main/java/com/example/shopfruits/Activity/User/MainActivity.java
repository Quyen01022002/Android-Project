package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.CategoryAdapter;
import com.example.shopfruits.Adapter.ProductAdapter;
import com.example.shopfruits.Adapter.ProductAdapterTop5;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    TextView name,userEmail;

    ProductAdapter productAdapter;
    CategoryAdapter categoryAdapter;
    RecyclerView rctop5,rc_loai;
    ProductAdapterTop5 productAdaptertop5;
    RecyclerView rcProduct;
    User user;
    APIService apiService;
    List<Product> productList;
    ImageView imageViewProfile,timkiem;
    ConstraintLayout cart,profile;
    EditText edit_timkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        Intent intent = getIntent();
        String id= intent.getStringExtra("idcate");

        AnhXa();
        GetProductTop5();
        getCategory();
        if(id==null)
        {
            GetProduct();
        }
        else {

            int idcate=Integer.parseInt (id);
            GetProductCate(idcate);


        }

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_shop)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);



        if(SharePrefManager.getInstance(this).isLoggedIn()){


            name = headerView.findViewById(R.id.tv_Name);
            userEmail = headerView.findViewById(R.id.tv_email);
            imageViewProfile = headerView.findViewById(R.id.img);
            profile = headerView.findViewById(R.id.profile);


            user = SharePrefManager.getInstance(this).getUser();
            Log.d("loggs", "userLogin: " +user.getName());
            name.setText(user.getName());
            userEmail.setText(user.getEmail());
            Glide.with(getApplicationContext()).load(user.getAvatar()).into(imageViewProfile);

        }
        edit_timkiem=findViewById(R.id.editTextTimKiem);
        timkiem.setOnClickListener(new View.OnClickListener() {
            boolean isShowing = false;
            @Override
            public void onClick(View view) {
                if (!isShowing) {
                    edit_timkiem.setVisibility(View.VISIBLE);
                    isShowing = true;
                } else {
                    edit_timkiem.setVisibility(View.GONE);
                    isShowing = false;

                    GetProductTimKiem(edit_timkiem.getText().toString().trim());
                }
            }
        });

        // lưu vào Pre
        getIDCart(user.getUserID());
        SharePrefManager.getInstance(getApplicationContext()).userID(user.getUserID());

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this, CartActivity.class);
                it.putExtra("iduser", user.getUserID());
                startActivity(it);
            }
        });
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,profile.class);
                startActivity(it);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(MainActivity.this,profile.class);
                startActivity(it);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void AnhXa(){
        cart=findViewById(R.id.cart);
        timkiem=findViewById(R.id.imageView7);
        rcProduct=(RecyclerView) findViewById(R.id.rc_product);
        rctop5=(RecyclerView) findViewById(R.id.rc_top5);
        rc_loai=(RecyclerView) findViewById(R.id.rc_loai);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            SharePrefManager.getInstance(getApplicationContext()).logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private int getIDCart(int iduser)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getidcart(iduser).enqueue(new Callback<CartEnity>() {
            @Override
            public void onResponse(Call<CartEnity> call, Response<CartEnity> response) {
                CartEnity cart1=new CartEnity();
                cart1=response.body();
                int id=cart1.getCartID();
                SharePrefManager.getInstance(getApplicationContext()).cartID(id);
            }

            @Override
            public void onFailure(Call<CartEnity> call, Throwable t) {

            }
        });
        return 0;
    }
    private void GetProductTimKiem(String key) {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.timkiem(key).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,MainActivity.this);
                    rcProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void GetProduct() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getProductAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,MainActivity.this);
                    rcProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void GetProductCate(int idcate) {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);

        apiService.sanphamcate(idcate).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,MainActivity.this);
                    rcProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void GetProductTop5() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.gettop5().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    List<Product> productListtop5=response.body();
                    productAdaptertop5=new ProductAdapterTop5(productListtop5,MainActivity.this);
                    rctop5.setHasFixedSize(true);
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager((getApplicationContext()) , LinearLayoutManager.HORIZONTAL, false);

                    rctop5.setLayoutManager(layoutManager);
                    rctop5.setAdapter(productAdaptertop5);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void getCategory() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    List<Category> listcate=response.body();
                    categoryAdapter=new CategoryAdapter(listcate,MainActivity.this);
                    rc_loai.setHasFixedSize(true);
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);

                    rc_loai.setLayoutManager(layoutManager);
                    rc_loai.setAdapter(categoryAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
}