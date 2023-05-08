package com.example.shopfruits.Activity.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.ItemDonHangAdapter;
import com.example.shopfruits.Adapter.ShipperOptionAdapter;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDH_Activity_shop extends AppCompatActivity {
    APIService apiService;
    RecyclerView itemDH;
    TextView TrangThai;
    ConstraintLayout XacNhan;
    int  cateid;
    OrderEnity orderEnity=new OrderEnity();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chitietdonhang_shop);
        XacNhan=findViewById(R.id.XacNhan_Constrian_shop);
        Intent it=getIntent();
        TextView NguoiNhan=findViewById(R.id.tv_Ten_shop);
        TextView DiaChi=findViewById(R.id.tv_diachi_shop);
        TextView Phone=findViewById(R.id.tv_phone_shop);
        ImageView Avatar=findViewById(R.id.nguoiNhan_Avatar);
        User user=new User();
        user = SharePrefManager.getInstance(this).getUser();
        NguoiNhan.setText(user.getName());

        Glide.with(getApplicationContext()).load(user.getAvatar().toString().trim()).into(Avatar);

        int iddh = SharePrefManager.getInstance(getApplicationContext()).getDHID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getorder(iddh).enqueue(new Callback<OrderEnity>() {
            @Override
            public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                orderEnity=response.body();
                TrangThai=findViewById(R.id.tv_trangthai_dh);
                TrangThai.setText(orderEnity.getStatus());
                DiaChi.setText(orderEnity.getAddress());
                Phone.setText(orderEnity.getPhone());
                itemDH=findViewById(R.id.rc_product_dh);
                getitemproduct(orderEnity.getOrderID());

                if(TrangThai.getText().toString().equals("Đã Xác Nhận"))
                {
                    XacNhan.setVisibility(View.VISIBLE);
                    TextView tv_xacnhan=findViewById(R.id.xacnhan);
                    tv_xacnhan.setText("Đóng gói");
                    XacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            orderEnity.setStatus("Đã Đóng Gói");
                            apiService.capnhat(orderEnity).enqueue(new Callback<OrderEnity>() {
                                @Override
                                public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                                    Intent it=new Intent(ChiTietDH_Activity_shop.this, ChiTietDH_Activity_shop.class);


                                    startActivity(it);
                                }

                                @Override
                                public void onFailure(Call<OrderEnity> call, Throwable t) {

                                }
                            });
                        }
                    });


                }
                if(TrangThai.getText().toString().equals("Đang Giao"))
                {
                    XacNhan.setVisibility(View.VISIBLE);
                    TextView tv_xacnhan=findViewById(R.id.xacnhan);
                    tv_xacnhan.setText("Đã giao");
                    XacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            orderEnity.setStatus("Đã Giao");
                            apiService.capnhat(orderEnity).enqueue(new Callback<OrderEnity>() {
                                @Override
                                public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                                    Intent it=new Intent(ChiTietDH_Activity_shop.this, ChiTietDH_Activity_shop.class);


                                    startActivity(it);
                                }

                                @Override
                                public void onFailure(Call<OrderEnity> call, Throwable t) {

                                }
                            });
                        }
                    });


                }
                if(TrangThai.getText().toString().equals("Đã Đóng Gói"))
                {  Spinner myspiner3=findViewById(R.id.my_spinner3);
                    apiService.getdanhsachshipper().enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        List<User> list=new ArrayList<>();
                        list=response.body();

                        ShipperOptionAdapter adapter = new ShipperOptionAdapter(ChiTietDH_Activity_shop.this, list);
                        myspiner3.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });



                    TextView chonshipper=findViewById(R.id.tv_chonshipper);
                    chonshipper.setVisibility(View.VISIBLE);
                    ConstraintLayout Chon=findViewById(R.id.ChonShipper);
                    Chon.setVisibility(View.VISIBLE);
                    myspiner3.setVisibility(View.VISIBLE);

                    myspiner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            User selectedOption = (User) parent.getItemAtPosition(position);
                          cateid = selectedOption.getUserID();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Xử lý khi không có tùy chọn nào được chọn
                        }
                    });
                    Chon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                    orderEnity.setStatus("Đang Giao");
                                    orderEnity.setShipperID(cateid);
                                    apiService.capnhat(orderEnity).enqueue(new Callback<OrderEnity>() {
                                        @Override
                                        public void onResponse(Call<OrderEnity> call, Response<OrderEnity> response) {
                                            Intent it=new Intent(ChiTietDH_Activity_shop.this, ChiTietDH_Activity_shop.class);
                                            it.putExtra("iddh", orderEnity.getOrderID());

                                            startActivity(it);
                                        }

                                        @Override
                                        public void onFailure(Call<OrderEnity> call, Throwable t) {


                                }
                            });

                        }
                    });

                }




            }

            @Override
            public void onFailure(Call<OrderEnity> call, Throwable t) {

            }
        });

    }

    public void getitemproduct(int id)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getitemdh(id).enqueue(new Callback<List<DonHangModel>>() {
            @Override
            public void onResponse(Call<List<DonHangModel>> call, Response<List<DonHangModel>> response) {
                List<DonHangModel> pd;
                pd=response.body();
                ItemDonHangAdapter cartAdapter= new ItemDonHangAdapter(pd, ChiTietDH_Activity_shop.this);
                itemDH.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(ChiTietDH_Activity_shop.this, LinearLayoutManager.VERTICAL, false);
                itemDH.setLayoutManager(layoutManager);
                itemDH.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<DonHangModel>> call, Throwable t) {

            }
        });
    }
}
