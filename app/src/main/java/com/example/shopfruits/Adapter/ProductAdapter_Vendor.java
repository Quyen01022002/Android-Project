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
import com.example.shopfruits.Activity.Vendor.ChiTietAcivity_Shop;
import com.example.shopfruits.Activity.Vendor.ChinhSuaSPDialog;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.R;

import java.util.List;

public class ProductAdapter_Vendor extends RecyclerView.Adapter<ProductAdapter_Vendor.MyViewHolder> {
    List<Product> array;
    Context context;

    public ProductAdapter_Vendor(List<Product> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_vendor,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp,idsp,gia;
        ConstraintLayout add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_sp_vendor);
            tenSp=itemView.findViewById(R.id.tv_namesp_vendor);
            idsp=itemView.findViewById(R.id.tv_id);

            gia=itemView.findViewById(R.id.tv_gia_vendor);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product"+idsp.getText().toString(), Toast.LENGTH_SHORT).show();
                    Product product = new Product();
                    Intent it=new Intent(context, ChiTietAcivity_Shop.class);
                    it.putExtra("idProduct", idsp.getText());

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

        holder.gia.setText(String.valueOf(product.getPrice()));
        Glide.with(context)
                .load(product.getImg().toString().trim())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
