package com.example.shopfruits.Activity.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.DonHang_ShopAdapter;
import com.example.shopfruits.Adapter.UserAdapter;
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.databinding.FragmentKhachhangBinding;
import com.example.shopfruits.databinding.FragmentNeworderBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KhachHangDangTheoDoi extends Fragment {
    FragmentKhachhangBinding binding;
    RecyclerView rc_user;
    UserAdapter userAdapter;
    APIService apiService;

    public KhachHangDangTheoDoi(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding= FragmentKhachhangBinding.inflate(inflater, container,false);
        rc_user=binding.rcKhachhang;
        Intent intent = getActivity().getIntent();
        String id= intent.getStringExtra("idst");
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getKhachHangTheoDoiShop((Integer.parseInt(id))).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users=response.body();

                userAdapter=new UserAdapter(users, getActivity());
                rc_user.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                rc_user.setLayoutManager(layoutManager);
                rc_user.setAdapter(userAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });




        return binding.getRoot();
    }


}
