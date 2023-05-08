package com.example.shopfruits.Activity.Shipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopfruits.Activity.Vendor.DonggoiOrderFragment;
import com.example.shopfruits.Activity.Vendor.NewOrderFragment;
import com.example.shopfruits.Activity.Vendor.XnOrderFragment;

public class ViewPager2Adapter_Shipper extends FragmentStateAdapter {

    public ViewPager2Adapter_Shipper(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){

            case 0:
                return  new com.example.shopfruits.Activity.Shipper.NewOrderFragment();
            case 1:
                return  new DangGiaoOrderFragment();
            case 2:
                return  new DaGiaoOrderFragment();

            default:
                return new com.example.shopfruits.Activity.Shipper.NewOrderFragment();
        }


    }

    @Override
    public int getItemCount(){return 5;}





}
