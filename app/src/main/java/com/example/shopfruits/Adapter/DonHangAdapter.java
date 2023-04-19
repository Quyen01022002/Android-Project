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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.CartActivity;
import com.example.shopfruits.Activity.ChiTietAcivity;
import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    List<OrderEnity> array;
    Context context;
    APIService apiService;

    ItemDonHangAdapter itemDonHangAdapter;

    public DonHangAdapter(List<OrderEnity> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oder,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView TrangThai,TongTien;
        public RecyclerView itemDH;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TrangThai=itemView.findViewById(R.id.tv_trangthai);
            TongTien=itemView.findViewById(R.id.TongDH);
            itemDH=itemView.findViewById(R.id.rc_itemdh);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product", Toast.LENGTH_SHORT).show();
//                    Product product = new Product();
//                    Intent it=new Intent(context, ChiTietAcivity.class);
//                    it.putExtra("id", idsp.getText());
//
//                    context.startActivity(it);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){

        OrderEnity product = array.get(position);
        holder.TrangThai.setText(product.getStatus());
        holder.TongTien.setText(String.valueOf(product.getCostSum()));
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getitemdh(product.getOrderID()).enqueue(new Callback<List<DonHangModel>>() {
            @Override
            public void onResponse(Call<List<DonHangModel>> call, Response<List<DonHangModel>> response) {
                List<DonHangModel> pd;
                pd=response.body();
                ItemDonHangAdapter cartAdapter= new ItemDonHangAdapter(pd,context);
                holder.itemDH.setHasFixedSize(true);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                holder.itemDH.setLayoutManager(layoutManager);
                holder.itemDH.setAdapter(cartAdapter);
            }

            @Override
            public void onFailure(Call<List<DonHangModel>> call, Throwable t) {

            }
        });


        }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
