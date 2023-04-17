package com.example.tranbuuquyen_tuan08;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_profile extends AppCompatActivity {
    Button btnChoose,btnUpload;
    EditText editTextUserName;
    APIService apiService;
    ImageView imageViewChoose, imageViewUpLoad;

    TextView textViewussername;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    public static final int MY_REQUEST_CODE=100;
    public static final String TAG =edit_profile.class.getName();
    private void AnhXa()
    {
        btnChoose=findViewById(R.id.btnChoose);
        btnUpload=findViewById(R.id.btnUpload);
        imageViewUpLoad=findViewById(R.id.imgMultipart);
        editTextUserName=findViewById(R.id.editUserName);
        textViewussername=findViewById(R.id.tvUsername);
        imageViewChoose=findViewById(R.id.imgChoose);


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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults)
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        AnhXa();
        mProgressDialog=new ProgressDialog(edit_profile.this);
        mProgressDialog.setMessage("Please wait upload......");
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUri!=null)
                {
                    UploadImage1();
                }
            }
        });
    }
    public void UploadImage1(){
        mProgressDialog.show();
        Intent intent = getIntent();
        String id= intent.getStringExtra("id");
        editTextUserName.setText(id);
        RequestBody requestUsername=RequestBody.create(MediaType.parse("multipart/form-data"),id);
        String IMAGE_PATH=RealPathUtil.getRealPath(this,mUri);
        Log.e("ffff",IMAGE_PATH);
        File file=new File(IMAGE_PATH);
        RequestBody requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part partbodyavatr=MultipartBody.Part.createFormData(constants.MY_IMAGES,file.getName(),requestFile);
        apiService = RetrofitClient.getInstance().getRetrofit(constants.URL_REGISTRATION).create(APIService.class);
       apiService.upload(requestUsername,partbodyavatr).enqueue(new Callback<result>() {
           @Override
           public void onResponse(Call<result> call, Response<result> response) {
               response.body().getResult();

           }

           @Override
           public void onFailure(Call<result> call, Throwable t) {

               Toast.makeText(edit_profile.this,t.getMessage() , Toast.LENGTH_SHORT).show();
           }
       });
    }
}
