package com.example.shopfruits.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
        public TextView tenSp,trangthai,id,dangnhap;
        public ConstraintLayout DN;
        public ImageView start_1,start_2,start_3,start_4,start_5;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_avatar_shop);
            tenSp=itemView.findViewById(R.id.tv_shopname_shop);
            trangthai=itemView.findViewById(R.id.tv_trangthai_shop);
            id=itemView.findViewById(R.id.shop_id);
            start_1=itemView.findViewById(R.id.imagest1_st);
            start_2=itemView.findViewById(R.id.imagest2_st);
            start_3=itemView.findViewById(R.id.imagest3_st);
            start_4=itemView.findViewById(R.id.imagest4_st);
            start_5=itemView.findViewById(R.id.imagest5_st);
            DN=itemView.findViewById(R.id.tv_dnShop);
            dangnhap=itemView.findViewById(R.id.tv_danhnhap);
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
        if(stores.getActive()==true && stores.getOpen()==true)
        {
            holder.trangthai.setText("Đang hoạt động");

        }
        else{
            holder.trangthai.setText("Không hoạt động");
            holder.DN.setBackgroundResource(R.drawable.rc_shape);
            holder.dangnhap.setTextColor(Color.parseColor("#FFFFFF"));

        }
        if((int)stores.getRating()==1)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.start);
            holder.start_3.setBackgroundResource(R.drawable.start);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

        }
        else if((int)stores.getRating()==2)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.start);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

        }
        else if((int)stores.getRating()==0)
        {



            holder.start_1.setBackgroundResource(R.drawable.start);
            holder.start_2.setBackgroundResource(R.drawable.start);
            holder.start_3.setBackgroundResource(R.drawable.start);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

        }
        else if((int)stores.getRating()==3)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.star);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

        }else if((int)stores.getRating()==4)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.star);
            holder.start_4.setBackgroundResource(R.drawable.star);
            holder.start_5.setBackgroundResource(R.drawable.start);

        }else if((int)stores.getRating()==5)
        {
            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.star);
            holder.start_4.setBackgroundResource(R.drawable.star);
            holder.start_5.setBackgroundResource(R.drawable.star);

        }

        Glide.with(context)
                .load(stores.getAvatar())
                .into(holder.images);

    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
