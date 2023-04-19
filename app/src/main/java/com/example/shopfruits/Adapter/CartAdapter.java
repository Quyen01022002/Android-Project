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
import com.example.shopfruits.Activity.MainActivity;
import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    List<Cart> array;
    Context context;

    public CartAdapter(List<Cart> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp,id,gia;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_cart);
            tenSp=itemView.findViewById(R.id.tv_namespcart);
            gia=itemView.findViewById(R.id.tv_giaCart);
            id=itemView.findViewById(R.id.txr_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn category"+tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent it=new Intent(context, MainActivity.class);
                    it.putExtra("idcartitem", id.getText());
                    context.startActivity(it);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Cart cart =array.get(position);
        holder.tenSp.setText(cart.getName());
        String proID=String.valueOf(cart.getCategoryID());
        holder.id.setText(proID);
        String gia=String.valueOf(cart.getPrice());
        holder.gia.setText(gia);
        Glide.with(context)
                .load(cart.getImg())
                .into(holder.images);

    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
