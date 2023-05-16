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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.Activity.User.CartActivity;
import com.example.shopfruits.Activity.User.ChiTietAcivity;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    List<Product> array;
    Context context;

    public ProductAdapter(List<Product> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp,idsp,storeID,gia;
        ConstraintLayout add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.imageProduct);
            tenSp=itemView.findViewById(R.id.tv_nameproduct);
            idsp=itemView.findViewById(R.id.tv_id);
            add=itemView.findViewById(R.id.themgoihang);
            storeID=itemView.findViewById(R.id.idstore);
            gia=itemView.findViewById(R.id.tv_gia_sp);


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product"+idsp.getText().toString(), Toast.LENGTH_SHORT).show();

                    Intent it=new Intent(context, CartActivity.class);
                    it.putExtra("iditem", idsp.getText());
                    it.putExtra("idstore", storeID.getText());
                    context.startActivity(it);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product"+idsp.getText().toString(), Toast.LENGTH_SHORT).show();
                    Product product = new Product();
                    Intent it=new Intent(context, ChiTietAcivity.class);
                    it.putExtra("id", idsp.getText());

                    context.startActivity(it);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Product product = array.get(position);
        holder.tenSp.setText(product.getName());
        String proID=String.valueOf(product.getProductID());
        holder.idsp.setText(proID);
        String idstore=String.valueOf(product.getStoreID());
        holder.storeID.setText(idstore);
        holder.gia.setText(String.valueOf(product.getPrice()));
        Glide.with(context)
                .load(product.getImg().toString().trim())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
