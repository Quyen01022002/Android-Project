package com.example.shopfruits.ui.gallery;

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
import com.example.shopfruits.Adapter.DonHangAdapter;
import com.example.shopfruits.Adapter.DonHang_ShopAdapter;
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.databinding.FragmentDagiaoBinding;
import com.example.shopfruits.databinding.FragmentDanggiaoBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DangGiaoOrderFragment extends Fragment {
    FragmentDanggiaoBinding binding;
    RecyclerView DonHang;
    DonHangAdapter donHangAdapter;
    APIService apiService;

    public DangGiaoOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding= FragmentDanggiaoBinding.inflate(inflater, container,false);
        DonHang=binding.rcDonhangDonggoi;
        int useriD = SharePrefManager.getInstance(getActivity()).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getDonHangbyTT(useriD,"Đang Giao").enqueue(new Callback<List<OrderEnity>>() {
            @Override
            public void onResponse(Call<List<OrderEnity>> call, Response<List<OrderEnity>> response) {
                List<OrderEnity> or=response.body();

                donHangAdapter=new DonHangAdapter(or, getActivity());
                DonHang.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                DonHang.setLayoutManager(layoutManager);
                DonHang.setAdapter(donHangAdapter);
            }

            @Override
            public void onFailure(Call<List<OrderEnity>> call, Throwable t) {

            }
        });




        return binding.getRoot();
    }


}
