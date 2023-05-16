package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Models.DiaChi;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.OrderItemEnity;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanDialog extends AppCompatActivity {
    APIService apiService;
    TextView Ten,Gia;
    ImageView anh,avatar;
    TextView soluong,Tong,Tong_sp,StoreID,Idproduct;
    TextView DiaChi_diachi;
    TextView Sdt;
    Product pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.thanhtoan_dialog);

        int dialogWidth =  getResources().getDimensionPixelSize(R.dimen.dialog_width);
        soluong=findViewById(R.id.soluong_TT);
        int dialogHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setLayout(dialogWidth, dialogHeight);

        TextView DiaChi_Ten=findViewById(R.id.diachi_ten);
        DiaChi_diachi=findViewById(R.id.diachi);
        Sdt=findViewById(R.id.diachi_sdt);
        Tong_sp=findViewById(R.id.tong_sp);
        avatar=findViewById(R.id.img_avatar);

        User user=new User();
        user = SharePrefManager.getInstance(this).getUser();
        DiaChi_Ten.setText(user.getName());
        Sdt.setText(user.getPhone());
        Glide.with(getApplicationContext()).load(user.getAvatar().toString().trim()).into(avatar);


        int iduser = SharePrefManager.getInstance(this).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getdiachi(iduser).enqueue(new Callback<com.example.shopfruits.Models.DiaChi>() {
            @Override
            public void onResponse(Call<com.example.shopfruits.Models.DiaChi> call, Response<com.example.shopfruits.Models.DiaChi> response) {
                DiaChi dc=new DiaChi();
                dc=response.body();
                DiaChi_diachi.setText(dc.getDiaChi());
            }

            @Override
            public void onFailure(Call<com.example.shopfruits.Models.DiaChi> call, Throwable t) {
                Toast.makeText(ThanhToanDialog.this, "Sai rooif nef", Toast.LENGTH_SHORT).show();
            }
        });
        tinhtoan();
        GetItemProduct();
        ConstraintLayout DatHang=findViewById(R.id.MuaHang);
        DatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.setSold(pd.getSold() + Integer.parseInt(soluong.getText().toString()));
                int slcon = Integer.parseInt(soluong.getText().toString());
                if (slcon > pd.getQuantity()) {
                    Toast.makeText(ThanhToanDialog.this, "Bạn đã mua quá số lượng sản phẩm tồn kho", Toast.LENGTH_SHORT).show();
                } else {
                    pd.setQuantity(pd.getQuantity() - Integer.parseInt(soluong.getText().toString()));


                    apiService.updateproduct(pd).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            SaveDonHang();
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                        }
                    });


                }
            }
        });

    }


    public void GetItemProduct() {
        Ten=findViewById(R.id.text_tenSP);
        Gia=findViewById(R.id.tv_giaSP);

        anh=findViewById(R.id.img_TT);
        StoreID=findViewById(R.id.storeID_TT);
        Idproduct=findViewById(R.id.id_productTT);

        Intent intent = getIntent();
        int id= intent.getIntExtra("idsp",0);

        Idproduct.setText(String.valueOf(id));
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.chitiet(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                pd=response.body();
                Ten.setText(pd.getName());
                String gia=String.valueOf(pd.getPrice());
                Gia.setText(gia);
                String storeid=String.valueOf(pd.getStoreID());
                StoreID.setText(storeid);

                Glide.with(getApplicationContext()).load(pd.getImg().toString().trim()).into(anh);
                apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);



            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ThanhToanDialog.this,t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void tinhtoan()
    {

        ConstraintLayout tru=findViewById(R.id.tru_TT);
        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sl = soluong.getText().toString().trim();

                final int sl_sp = Integer.parseInt(sl);
                if(sl_sp>1) {
                    int new_sl_sp = sl_sp - 1;
                    String sl_new = String.valueOf(new_sl_sp);
                    soluong.setText(sl_new);
                    String gia = Gia.getText().toString().trim();
                    int int_gia = Integer.parseInt(gia);
                    int tong = int_gia * new_sl_sp;
                    Tong = findViewById(R.id.tv_tong);
                    Tong.setText( String.valueOf(tong));
                    String sl_moi=String.valueOf(new_sl_sp);
                    Tong_sp.setText("Tổng cộng("+sl_moi+" sản phẩm):");
                }

            }
        });
        ConstraintLayout cong=findViewById(R.id.cong_TT);
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sl=soluong.getText().toString().trim();
                final int sl_sp=Integer.parseInt(sl);

                int new_sl_sp = sl_sp + 1;
                String sl_new = String.valueOf(new_sl_sp);
                soluong.setText(sl_new);
                String gia = Gia.getText().toString().trim();
                int int_gia = Integer.parseInt(gia);
                int tong = int_gia * new_sl_sp;

                Tong = findViewById(R.id.tv_tong);
                Tong.setText(String.valueOf(tong));
                String sl_moi=String.valueOf(new_sl_sp);
                Tong_sp.setText("Tổng cộng("+sl_moi+" sản phẩm):");
            }
        });
    }
    public void SaveChiTietDH(int iddh)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        OrderItemEnity odit =new OrderItemEnity();
        odit.setOrderID(iddh);
        odit.setCount(Integer.parseInt(soluong.getText().toString()));
        odit.setProductID(Integer.parseInt(Idproduct.getText().toString()));

        apiService.saveCHiTietDH(odit).enqueue(new Callback<OrderItemEnity>() {
            @Override
            public void onResponse(Call<OrderItemEnity> call, Response<OrderItemEnity> response) {

            }

            @Override
            public void onFailure(Call<OrderItemEnity> call, Throwable t) {

            }
        });
    }

    public void SaveDonHang () {
        int useriD = SharePrefManager.getInstance(this).getuserID();
        int idstore= Integer.parseInt(StoreID.getText().toString().trim());
        int tong= Integer.parseInt(Tong.getText().toString().trim());
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        OrderEnity orderEnity=new OrderEnity();
        orderEnity.setUserID(useriD);
        orderEnity.setCostSum(tong);
        orderEnity.setPhone(Sdt.getText().toString());
        orderEnity.setStoreID(idstore);
        orderEnity.setStatusTT("Chưa Thanh Toán");
        orderEnity.setStatus("Chưa Xác Nhận");
        orderEnity.setAddress(DiaChi_diachi.getText().toString().trim());
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
        orderEnity.setCreatedAt(sqlDate);
        orderEnity.setUpdatedAt(sqlDate);
        apiService.saveDonHang(orderEnity).enqueue(new Callback<OrderEnity>() {
            @Override
            public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                OrderEnity orderEnity1=new OrderEnity();
                orderEnity1=response.body();
                SaveChiTietDH(orderEnity1.getOrderID());
                Intent it=new Intent(ThanhToanDialog.this, ChiTietDH_Activity.class);
                it.putExtra("iddh", String.valueOf(orderEnity1.getOrderID()));
                startActivity(it);
            }

            @Override
            public void onFailure(Call<OrderEnity> call, Throwable t) {

            }
        });

    }

}
