package com.example.shopfruits.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Adapter.DonHangAdapter;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.databinding.FragmentGalleryBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {
    RecyclerView DonHang;
    DonHangAdapter donHangAdapter;
    APIService apiService;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DonHang=binding.RcDonHang;
        int useriD = SharePrefManager.getInstance(getActivity()).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getDonHang(useriD).enqueue(new Callback<List<OrderEnity>>() {
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}