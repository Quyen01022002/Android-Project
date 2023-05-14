package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Models.DiaChi;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {
    TextView name,email,diachi,phone,userID;
    ImageView avatar;
    APIService apiService;
    MaterialButton edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        if(SharePrefManager.getInstance(this).isLoggedIn()){


            name =findViewById(R.id.profile_name);
            email = findViewById(R.id.profile_email);
            avatar = findViewById(R.id.profile_avatar);
            diachi = findViewById(R.id.profile_diachi);
            phone=findViewById(R.id.profile_phone);
            userID=findViewById(R.id.profile_userID);
           edit=findViewById(R.id.edit_profile_btn);

            User user=new User();
            user = SharePrefManager.getInstance(this).getUser();
            Log.d("loggs", "userLogin: " +user.getName());
            name.setText(user.getName());
            email.setText(user.getEmail());
            phone.setText(user.getPhone());
            userID.setText(String.valueOf(user.getUserID()));
            Glide.with(getApplicationContext()).load(user.getAvatar()).into(avatar);

        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(profile.this,editprofileDialog.class);
                startActivity(it);
            }
        });
        int iduser = SharePrefManager.getInstance(this).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getdiachi(iduser).enqueue(new Callback<DiaChi>() {
            @Override
            public void onResponse(Call<DiaChi> call, Response<DiaChi> response) {
                DiaChi dc=new DiaChi();
                dc=response.body();
                diachi.setText(dc.getDiaChi());
            }

            @Override
            public void onFailure(Call<com.example.shopfruits.Models.DiaChi> call, Throwable t) {
                Toast.makeText(profile.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
