package com.example.shopfruits.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopfruits.R;
import com.example.shopfruits.databinding.FragmentGalleryBinding;
import com.google.android.material.tabs.TabLayout;

public class GalleryFragment extends Fragment {


    private FragmentGalleryBinding binding;
    private ViewPager2Adapter_User viewPager2Adapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gallery, container, false);
        binding= FragmentGalleryBinding.inflate(getLayoutInflater());
        TabLayout tab=view.findViewById(R.id.tabLayout);


        tab.addTab(tab.newTab().setText("Tất Cả"));
        tab.addTab(tab.newTab().setText("Xác Nhận"));
        tab.addTab(tab.newTab().setText("Đã Đóng Gói"));
        tab.addTab(tab.newTab().setText("Đang Giao"));
        tab.addTab(tab.newTab().setText("Đã Giao"));
        tab.addTab(tab.newTab().setText("Hủy"));
        ViewPager2 viewPager=view.findViewById(R.id.viewPager2);
        FragmentManager fragmentManager= getChildFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter_User(fragmentManager, getLifecycle());
        viewPager.setAdapter(viewPager2Adapter);

       tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
               tab.selectTab(tab.getTabAt(position));
            }
        });
return view;
    }

}