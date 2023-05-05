package com.example.shopfruits.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.Activity.Vendor.VendorActivity;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.List;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.MyViewHolder> {
    List<Stores> array;
    Context context;

    public StoresAdapter(List<Stores> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp,trangthai,id;
        public ConstraintLayout DN;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_avatar_shop);
            tenSp=itemView.findViewById(R.id.tv_shopname_shop);
            trangthai=itemView.findViewById(R.id.tv_trangthai_shop);
            id=itemView.findViewById(R.id.shop_id);
            DN=itemView.findViewById(R.id.tv_dnShop);
            DN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it=new Intent(context, VendorActivity.class);
                    it.putExtra("idst", id.getText());
                    SharePrefManager.getInstance(context).StoreID(Integer.parseInt(id.getText().toString().trim()));


                    context.startActivity(it);
                }
            });


        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Stores stores =array.get(position);
        holder.tenSp.setText(stores.getName());

        holder.id.setText(String.valueOf(stores.getStoreID()));
       if(stores.getActive()==true) {
           holder.trangthai.setText("Đang hoạt động");
       }
        Glide.with(context)
                .load(stores.getAvatar())
                .into(holder.images);

    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
