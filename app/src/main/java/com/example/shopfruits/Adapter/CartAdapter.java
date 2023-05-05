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
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.User.CartActivity;
import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    List<Cart> array;
    Context context;
    APIService apiService;

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
        public ConstraintLayout Xoa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_cart);
            tenSp=itemView.findViewById(R.id.tv_namespcart);
            gia=itemView.findViewById(R.id.tv_giaCart);
            id=itemView.findViewById(R.id.txr_id);
            Xoa=itemView.findViewById(R.id.xoa);
            Xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
                    apiService.deleteProduct(Integer.parseInt(id.getText().toString())).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(context, id.getText(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                    int iduser = SharePrefManager.getInstance(context).getuserID();
                    Intent it=new Intent(context, CartActivity.class);
                    it.putExtra("id", iduser);

                    context.startActivity(it);
                }


            });


        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        Cart cart =array.get(position);
        holder.tenSp.setText(cart.getName());
        String proID=String.valueOf(cart.getId());
        holder.id.setText(proID);
        String gia=String.valueOf(cart.getPrice());
        holder.gia.setText(gia);
        Glide.with(context)
                .load(cart.getImg().toString().trim())
                .into(holder.images);

    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
