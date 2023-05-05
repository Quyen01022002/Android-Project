package com.example.shopfruits.Activity.Vendor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){

            case 0:
                return  new NewOrderFragment();
            case 1:
                return  new XnOrderFragment();
            case 2:
                return  new DonggoiOrderFragment();
            case 3:
                return  new DangGiaoOrderFragment();
            case 4:
                return  new DaGiaoOrderFragment();
            default:
                return new NewOrderFragment();
        }


    }

    @Override
    public int getItemCount(){return 5;}





}
