package com.example.shopfruits.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.R;

import java.util.List;

public class ItemDonHangAdapter extends RecyclerView.Adapter<ItemDonHangAdapter.MyViewHolder> {
    List<DonHangModel> array;
    Context context;

    public ItemDonHangAdapter(List<DonHangModel> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dh,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView Ten,Gia,SL;
        public ImageView anh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Ten=itemView.findViewById(R.id.tv_nameitem_dh);
            Gia=itemView.findViewById(R.id.tv_giaitem_dh);
            SL=itemView.findViewById(R.id.tv_slitem_dh);
            anh=itemView.findViewById(R.id.img_anhitem_dh);




        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){

        DonHangModel dh = array.get(position);
        holder.Ten.setText(dh.getName());
        holder.SL.setText(String.valueOf(dh.getCount()));
        holder.Gia.setText(String.valueOf(dh.getPrice()));
        Glide.with(context)
                .load(dh.getImg().toString().trim())
                .into(holder.anh);



    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
