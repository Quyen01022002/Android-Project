package com.example.shopfruits.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.User.ThemShopDialog;
import com.example.shopfruits.Activity.Vendor.QuanLySPActivity;
import com.example.shopfruits.Activity.Vendor.ThemSPDialog;
import com.example.shopfruits.Adapter.StoresAdapter;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopFragment extends Fragment {

    RecyclerView DonHang;
    StoresAdapter storesAdapter;
    APIService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_fragment, container, false);
        int useriD = SharePrefManager.getInstance(getActivity()).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        DonHang=view.findViewById(R.id.rc_shop);
        apiService.getstore(useriD).enqueue(new Callback<List<Stores>>() {
            @Override
            public void onResponse(Call<List<Stores>> call, Response<List<Stores>> response) {
                List<Stores> or=response.body();

                storesAdapter=new StoresAdapter(or, getActivity());
                DonHang.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                DonHang.setLayoutManager(layoutManager);
                DonHang.setAdapter(storesAdapter);
            }

            @Override
            public void onFailure(Call<List<Stores>> call, Throwable t) {

            }
        });
        ConstraintLayout DkShop=view.findViewById(R.id.constraintLayout13);
        DkShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), ThemShopDialog.class);
                startActivity(it);
            }
        });

        return view;
    }
}