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
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.R;

import java.util.List;

public class ProductAdapterTop5 extends RecyclerView.Adapter<ProductAdapterTop5.MyViewHolder> {
    List<Product> array;
    Context context;

    public ProductAdapterTop5(List<Product> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top5,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp,gia;
        public ImageView start_1,start_2,start_3,start_4,start_5;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.imageViewRC);
            tenSp=itemView.findViewById(R.id.textNametop1);
            gia=itemView.findViewById(R.id.textViewGia);
            start_1=itemView.findViewById(R.id.imagest1_top5);
            start_2=itemView.findViewById(R.id.imagest2_top5);
            start_3=itemView.findViewById(R.id.imagest3_top5);
            start_4=itemView.findViewById(R.id.imagest4_top5);
            start_5=itemView.findViewById(R.id.imagest5_top5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product"+tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Product product = array.get(position);
        holder.tenSp.setText(product.getName());
        holder.gia.setText(String.valueOf(product.getPrice()));
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
                .load(product.getImg().toString().trim())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
