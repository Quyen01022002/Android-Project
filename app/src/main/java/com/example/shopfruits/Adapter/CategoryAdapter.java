package com.example.shopfruits.Adapter;

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
import com.example.shopfruits.Activity.User.MainActivity;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.R;

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
            images=itemView.findViewById(R.id.img_category);
            tenSp=itemView.findViewById(R.id.tv_catename);
            id=itemView.findViewById(R.id.tv_idcate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn category"+tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent it=new Intent(context, MainActivity.class);
                    it.putExtra("idcate", id.getText());
                    context.startActivity(it);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Category category =array.get(position);
        holder.tenSp.setText(category.getName());
        String proID=String.valueOf(category.getCategoryID());
        holder.id.setText(proID);
        Glide.with(context)
                .load(category.getImage())
                .into(holder.images);

    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
