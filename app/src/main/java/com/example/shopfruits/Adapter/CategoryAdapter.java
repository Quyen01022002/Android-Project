package com.example.tranbuuquyen_tuan08;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    List<Category> array;
    Context context;

    public CategoryAdapter(List<Category> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp,id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.image_product);
            tenSp=itemView.findViewById(R.id.tvNameProduct);
            id=itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn category"+tenSp.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Category category =array.get(position);
        holder.tenSp.setText(category.getName());

        Glide.with(context)
                .load(category.getImages())
                .into(holder.images);

    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
