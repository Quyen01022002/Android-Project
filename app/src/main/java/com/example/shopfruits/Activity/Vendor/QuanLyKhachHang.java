package com.example.shopfruits.Activity.Vendor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopfruits.R;
import com.example.shopfruits.databinding.QuanlykhachhangBinding;
import com.google.android.material.tabs.TabLayout;

public class QuanLyKhachHang extends AppCompatActivity {

    private QuanlykhachhangBinding binding;
    private QLKHViewPager2Adapter viewPager2Adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlykhachhang);
    binding=QuanlykhachhangBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
       


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đã mua hàng"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đang theo dõi"));

        FragmentManager fragmentManager= getSupportFragmentManager();
        viewPager2Adapter = new QLKHViewPager2Adapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);

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




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.menuSearch:
                Toast.makeText(this,"Bạn đang chọn search", Toast.LENGTH_SHORT).show();break;
            case R.id.menuNewGroup:
                Toast.makeText(this,"Bạn đang chọn more", Toast.LENGTH_SHORT).show();break;
            case R.id.menuBroadcast:
                Toast.makeText(this,"Bạn đang chọn more", Toast.LENGTH_SHORT).show();break;
            case R.id.menuWeb:
                Toast.makeText(this,"Bạn đang chọn more", Toast.LENGTH_SHORT).show();break;
            case R.id.menuMessage:
                Toast.makeText(this,"Bạn đang chọn more", Toast.LENGTH_SHORT).show();break;
            case R.id.menuSetting:
                Toast.makeText(this,"Bạn đang chọn Setting", Toast.LENGTH_SHORT).show();break;
       }
       return super.onOptionsItemSelected(item);

    }







}