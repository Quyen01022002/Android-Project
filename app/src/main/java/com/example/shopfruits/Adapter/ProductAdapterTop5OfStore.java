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
import com.example.shopfruits.Models.ProductOnTop5;
import com.example.shopfruits.R;

import java.util.List;

public class ProductAdapterTop5OfStore extends RecyclerView.Adapter<ProductAdapterTop5OfStore.MyViewHolder> {
    List<ProductOnTop5> array;
    Context context;

    public ProductAdapterTop5OfStore(List<ProductOnTop5> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_oftop5,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp;
        public TextView countsp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_sp_vendor);
            tenSp=itemView.findViewById(R.id.tv_namesp_vendor);
            countsp=itemView.findViewById(R.id.tv_countSp);
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
        ProductOnTop5 product = array.get(position);
        holder.tenSp.setText(product.getName());
        holder.countsp.setText(String.valueOf(product.getCountSold()));
        Glide.with(context)
                .load(product.getImg().toString().trim())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
