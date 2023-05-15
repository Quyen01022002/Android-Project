package com.example.shopfruits.Activity.Vendor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QLKHViewPager2Adapter extends FragmentStateAdapter {

    public QLKHViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){

            case 0:
                return  new KhachHangDaMua();
            case 1:
                return  new KhachHangDangTheoDoi();
            default:
                return new KhachHangDaMua();
        }


    }

    @Override
    public int getItemCount(){return 2;}





}
