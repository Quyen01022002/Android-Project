package com.example.shopfruits.Adapter;

import static android.content.Intent.getIntent;

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
import com.example.shopfruits.Activity.Vendor.DetailShipper;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    List<User> array;
    Context context;

    public UserAdapter(List<User> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenUser, id;
        ConstraintLayout add;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            images=itemView.findViewById(R.id.img_user);
            tenUser=itemView.findViewById(R.id.tv_nameuser);
            id=itemView.findViewById(R.id.user_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn user: "+tenUser.getText().toString(), Toast.LENGTH_SHORT).show();

                    Intent it=new Intent(context, DetailShipper.class);
                    it.putExtra("idShipper", id.getText());

                    context.startActivity(it);
                }
            });
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position){
        User user = array.get(position);
        holder.tenUser.setText(user.getName());
        String proID=String.valueOf(user.getUserID());
        holder.id.setText(proID);
        Glide.with(context)
                .load(user.getAvatar().toString().trim())
                .into(holder.images);
    }
    @Override
    public int getItemCount(){return array==null?0:array.size();}
}
