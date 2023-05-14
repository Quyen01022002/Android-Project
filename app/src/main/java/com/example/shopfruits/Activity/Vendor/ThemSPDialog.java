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

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.OptionAdapter;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.Product;
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

public class ThemSPDialog extends AppCompatActivity {
    APIService apiService;
    Button btnChoose;
    EditText editTenSP,sl,mota,gia;

    ImageView imageViewChoose, imageViewUpLoad;

    TextView textViewussername;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    int cateid;
    ConstraintLayout them;
    public static final int MY_REQUEST_CODE=100;
    public static final String TAG = ThemSPDialog.class.getName();
    Spinner mySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.themsanpham);
        them=findViewById(R.id.them);

        int dialogWidth = ViewGroup.LayoutParams.MATCH_PARENT;

        int dialogHeight = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setLayout(dialogWidth, dialogHeight);
         mySpinner = findViewById(R.id.my_spinner);

        getCategory();
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category selectedOption = (Category) parent.getItemAtPosition(position);
                cateid = selectedOption.getCategoryID();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có tùy chọn nào được chọn
            }
        });
        AnhXa();
        mProgressDialog=new ProgressDialog(ThemSPDialog.this);
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
    private void getCategory() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> list=new ArrayList<>();
                list=response.body();
                OptionAdapter adapter = new OptionAdapter(ThemSPDialog.this, list);
                mySpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
    private void AnhXa()
    {
        btnChoose=findViewById(R.id.buttonchon);

        imageViewUpLoad=findViewById(R.id.imgsp);

        imageViewChoose=findViewById(R.id.imgsp);
        editTenSP=findViewById(R.id.edit_tensp);
        sl=findViewById(R.id.sl);
        mota=findViewById(R.id.mota);
        gia=findViewById(R.id.gia);


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
    public void UploadImage1() {
        mProgressDialog.show();
        int useriD = SharePrefManager.getInstance(this).getuserID();
        String IMAGE_PATH = RealPathUtil.getRealPath(this, mUri);
        Log.e("ffff", IMAGE_PATH);
        apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);

        File file = new File(IMAGE_PATH);
        int storeID = SharePrefManager.getInstance(this).getStoreID();

        Product us = new Product();


        us.setImg(IMAGE_PATH);
        us.setName(String.valueOf(editTenSP.getText().toString().trim()));
        us.setPrice(Integer.parseInt(gia.getText().toString().trim()));
        us.setQuantity(Integer.parseInt(sl.getText().toString().trim()));
        us.setDescription(mota.getText().toString().trim());
        us.setSold(0);
        us.setStoreID(storeID);
        us.setActive(true);
        us.setSelling(true);
        us.setCategoryID(cateid);


        apiService.savesp(us).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Toast.makeText(ThemSPDialog.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                Intent it=new Intent(ThemSPDialog.this, QuanLySPActivity.class);
                it.putExtra("idst", storeID);
                startActivity(it);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
}
