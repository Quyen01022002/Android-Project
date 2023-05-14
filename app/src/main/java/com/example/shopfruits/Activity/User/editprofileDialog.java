package com.example.shopfruits.Activity.User;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.until.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editprofileDialog extends AppCompatActivity {
    APIService apiService;
    Button btnChoose;
    EditText editTenSP,email,phone;

    ImageView imageViewChoose, imageViewUpLoad;

    TextView textViewussername;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    int cateid;
    ConstraintLayout them;
    public static final int MY_REQUEST_CODE=100;
    public static final String TAG = editprofileDialog.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_profile);
        them=findViewById(R.id.luu);

        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;

        int dialogHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setLayout(dialogWidth, dialogHeight);


        AnhXa();
        if(SharePrefManager.getInstance(this).isLoggedIn()){




            User user=new User();
            user = SharePrefManager.getInstance(this).getUser();
            Log.d("loggs", "userLogin: " +user.getName());
            editTenSP.setText(user.getName());
            email.setText(user.getEmail());
            phone.setText(user.getPhone());

            Glide.with(getApplicationContext()).load(user.getAvatar()).into(imageViewChoose);

        }
        mProgressDialog=new ProgressDialog(editprofileDialog.this);
        mProgressDialog.setMessage("Chờ đi cha nội......");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage1();
            }
        });


    }

    private void AnhXa()
    {
        btnChoose=findViewById(R.id.buttonchon_anh);

        imageViewUpLoad=findViewById(R.id.img_avatar);

        imageViewChoose=findViewById(R.id.img_avatar);
        editTenSP=findViewById(R.id.edit_tennnd);
        email=findViewById(R.id.edit_email);
        phone=findViewById(R.id.edit_phone);



    }
    public static String[] storge_permissions={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] storge_permissions_33={
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
    };
    public static String[] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = storge_permissions_33;
        } else {
            p=storge_permissions;
        }
        return p;
    }
    private void CheckPermission(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            openGallery();
        }
        else {
            requestPermissions(permissions(),MY_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==MY_REQUEST_CODE)
        {
            if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                openGallery();
            }
        }
    }
    private void openGallery()
    {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode()== Activity.RESULT_OK)
                    {
                        Intent data=result.getData();
                        if(data==null)
                        {
                            return;
                        }
                        Uri uri=data.getData();
                        mUri=uri;
                        try{
                            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imageViewChoose.setImageBitmap(bitmap);

                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    public void UploadImage1(){
        mProgressDialog.show();
        int useriD = SharePrefManager.getInstance(this).getuserID();
        String IMAGE_PATH= RealPathUtil.getRealPath(this,mUri);
        Log.e("ffff",IMAGE_PATH);
        apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        RequestBody requestUsername=RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(useriD));
        File file=new File(IMAGE_PATH);

        User us=new User();
        us = SharePrefManager.getInstance(this).getUser();

        us.setAvatar(IMAGE_PATH);
        us.setName(editTenSP.getText().toString().trim());
        us.setPhone(phone.getText().toString().trim());
        us.setEmail(email.getText().toString().trim());



        apiService.update(us).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(editprofileDialog.this, "Da up load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
