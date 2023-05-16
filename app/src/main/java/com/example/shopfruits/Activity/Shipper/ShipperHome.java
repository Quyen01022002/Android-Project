package com.example.shopfruits.Activity.Shipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.shopfruits.Activity.Vendor.ViewPager2Adapter;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.databinding.QuanlysdonhangBinding;
import com.example.shopfruits.databinding.ShipperhomeBinding;
import com.google.android.material.tabs.TabLayout;

public class ShipperHome extends AppCompatActivity {

    private ShipperhomeBinding binding;
    private ViewPager2Adapter_Shipper viewPager2Adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    binding=ShipperhomeBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
       


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tất Cả"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đang Giao"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đã Giao"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Hủy"));
        if(SharePrefManager.getInstance(this).isLoggedIn()){

            User user=new User();
            user = SharePrefManager.getInstance(this).getUser();
            Log.d("loggs", "userLogin: " +user.getName());
            Glide.with(getApplicationContext()).load(user.getAvatar()).into(binding.imgAvatarShipper);

        }
        FragmentManager fragmentManager= getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter_Shipper(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);
        binding.imgAvatarShipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ShipperHome.this,profile_shipper.class);
                startActivity(it);
            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });

    }









    }






