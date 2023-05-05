package com.example.shopfruits.Activity.Vendor;

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
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.databinding.FragmentXacnhanBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class XnOrderFragment extends Fragment {
    FragmentXacnhanBinding binding;
    RecyclerView DonHang;
    DonHang_ShopAdapter donHangAdapter;
    APIService apiService;

    public XnOrderFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding= FragmentXacnhanBinding.inflate(inflater, container,false);
        DonHang=binding.rcDonhangDonggoi;
        int id = SharePrefManager.getInstance(getActivity()).getStoreID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.GetTrangThai(id,"Đã Xác Nhận").enqueue(new Callback<List<DonHang_Shop_Model>>() {
            @Override
            public void onResponse(Call<List<DonHang_Shop_Model>> call, Response<List<DonHang_Shop_Model>> response) {
                List<DonHang_Shop_Model> or=response.body();

                donHangAdapter=new DonHang_ShopAdapter(or, getActivity());
                DonHang.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                DonHang.setLayoutManager(layoutManager);
                DonHang.setAdapter(donHangAdapter);
            }

            @Override
            public void onFailure(Call<List<DonHang_Shop_Model>> call, Throwable t) {

            }
        });




        return binding.getRoot();
    }


}
