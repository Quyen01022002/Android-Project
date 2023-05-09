package com.example.shopfruits.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.Models.ReviewModel;
import com.example.shopfruits.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    List<ReviewModel> array;
    Context context;

    public ReviewAdapter(List<ReviewModel> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhgia,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView ten,content;
         public ImageView start_1,start_2,start_3,start_4,start_5;
        ConstraintLayout add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.imageView10);
            ten=itemView.findViewById(R.id.ten);
            content=itemView.findViewById(R.id.textView32);
            start_1=itemView.findViewById(R.id.imagest1);
            start_2=itemView.findViewById(R.id.imagest2);
            start_3=itemView.findViewById(R.id.imagest3);
            start_4=itemView.findViewById(R.id.imagest4);
            start_5=itemView.findViewById(R.id.imagest5);




        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        ReviewModel product = array.get(position);
        holder.ten.setText(product.getName());
        holder.content.setText(product.getContents());
        if((int)product.getRating()==1)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.start);
            holder.start_3.setBackgroundResource(R.drawable.start);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

            }
        else if((int)product.getRating()==2)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.start);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

            }
        else if((int)product.getRating()==3)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.star);
            holder.start_4.setBackgroundResource(R.drawable.start);
            holder.start_5.setBackgroundResource(R.drawable.start);

            }else if((int)product.getRating()==4)
        {



            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.star);
            holder.start_4.setBackgroundResource(R.drawable.star);
            holder.start_5.setBackgroundResource(R.drawable.start);

            }else if((int)product.getRating()==5)
        {
            holder.start_1.setBackgroundResource(R.drawable.star);
            holder.start_2.setBackgroundResource(R.drawable.star);
            holder.start_3.setBackgroundResource(R.drawable.star);
            holder.start_4.setBackgroundResource(R.drawable.star);
            holder.start_5.setBackgroundResource(R.drawable.star);

            }





        Glide.with(context)
                .load(product.getAvatar().toString().trim())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
