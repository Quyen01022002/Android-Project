package com.example.shopfruits.Activity.Vendor;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.DonHang_ShopAdapter;
import com.example.shopfruits.Adapter.OptionAdapter;
import com.example.shopfruits.Adapter.UserAdapter;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.until.RealPathUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailShipper extends AppCompatActivity {
    APIService apiService;
    Button btnChoose;
    String idShipper ;
    int idStore;
    DonHang_ShopAdapter donHang_shopAdapter;

    ImageView images;
    RecyclerView rc_orderOfShipper;
    TextView tv_name, tv_phone, tv_email, tv_idShipper;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    int cateid;
    public static final int MY_REQUEST_CODE=100;
    public static final String TAG = DetailShipper.class.getName();
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shipper_order);

        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;

        int dialogHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setLayout(dialogWidth, dialogHeight);

        AnhXa();

        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        Intent intent = getIntent();
        idShipper= intent.getStringExtra("idShipper");
        idStore = SharePrefManager.getInstance(this).getStoreID();
        getShipper(Integer.parseInt(idShipper));
        getOrderOfShipperOnStore(Integer.parseInt(idShipper),idStore);
    }

    private void getShipper(int iduser){
        //apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getShipper(iduser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user= response.body();
                tv_idShipper.setText(String.valueOf(user.getUserID()));
                tv_phone.setText(user.getPhone());
                tv_name.setText(user.getName());
                tv_email.setText(user.getEmail());
                Glide.with(getApplicationContext()).load(user.getAvatar().toString().trim()).into(images);



            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



    }

    private void getOrderOfShipperOnStore(int idShipper, int idStore){

        apiService.getOrderOfShipperOnStore(idShipper,idStore).enqueue(new Callback<List<DonHang_Shop_Model>>() {
            @Override
            public void onResponse(Call<List<DonHang_Shop_Model>> call, Response<List<DonHang_Shop_Model>> response) {
                List<DonHang_Shop_Model> list = response.body();
                donHang_shopAdapter=new DonHang_ShopAdapter(list, DetailShipper.this);
                rc_orderOfShipper.setHasFixedSize(true);
                GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
                rc_orderOfShipper.setLayoutManager(layoutManager);
                rc_orderOfShipper.setAdapter(donHang_shopAdapter);

            }

            @Override
            public void onFailure(Call<List<DonHang_Shop_Model>> call, Throwable t) {

            }
        });



    }

    private void AnhXa()
    {
        tv_phone=findViewById(R.id.profile_phone);
        tv_email=findViewById(R.id.profile_email);
        tv_name=findViewById(R.id.profile_name);
        tv_idShipper=findViewById(R.id.profile_id);
        rc_orderOfShipper=findViewById(R.id.rc_donhang_shipper);
        images=findViewById(R.id.profile_avatar);


    }

}
