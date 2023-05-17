package com.example.shopfruits.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Activity.User.profile;
import com.example.shopfruits.Adapter.CategoryAdapter;
import com.example.shopfruits.Adapter.ProductAdapter;
import com.example.shopfruits.Adapter.ProductAdapterTop5;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.DiaChi;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.databinding.FragmentHomeBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    TextView name,userEmail;

    ProductAdapter productAdapter;
    CategoryAdapter categoryAdapter;
    RecyclerView rctop5,rc_loai;
    ProductAdapterTop5 productAdaptertop5;
    RecyclerView rcProduct;
    User user;
    APIService apiService;
    List<Product> productList;
    ImageView imageViewProfile;
    ConstraintLayout cart;
    View view;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home, container, false);
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Intent intent = getActivity().getIntent();
        String id= intent.getStringExtra("idcate");

        AnhXa();
        GetProductTop5();
        getCategory();
        if(id==null)
        {
            GetProduct();
        }
        else {

            int idcate=Integer.parseInt (id);
            GetProductCate(idcate);


        }
        TextView diachi=view.findViewById(R.id.home_diachi);
        int iduser = SharePrefManager.getInstance(getActivity()).getuserID();
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getdiachi(iduser).enqueue(new Callback<DiaChi>() {
            @Override
            public void onResponse(Call<DiaChi> call, Response<DiaChi> response) {
                DiaChi dc=new DiaChi();
                dc=response.body();
                diachi.setText(dc.getDiaChi());
            }

            @Override
            public void onFailure(Call<com.example.shopfruits.Models.DiaChi> call, Throwable t) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }
    private void AnhXa(){
        cart=view.findViewById(R.id.cart);
        rcProduct=(RecyclerView) view.findViewById(R.id.rc_product);
        rctop5=(RecyclerView) view.findViewById(R.id.rc_top5);
        rc_loai=(RecyclerView) view.findViewById(R.id.rc_loai);
    }
    private int getIDCart(int iduser)
    {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getidcart(iduser).enqueue(new Callback<CartEnity>() {
            @Override
            public void onResponse(Call<CartEnity> call, Response<CartEnity> response) {
                CartEnity cart1=new CartEnity();
                cart1=response.body();
                int id=cart1.getCartID();
                SharePrefManager.getInstance(getActivity()).cartID(id);
            }

            @Override
            public void onFailure(Call<CartEnity> call, Throwable t) {

            }
        });
        return 0;
    }

    private void GetProduct() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getProductAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,getActivity());
                    rcProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void GetProductCate(int idcate) {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);

        apiService.sanphamcate(idcate).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList=response.body();
                    productAdapter=new ProductAdapter(productList,getActivity());
                    rcProduct.setHasFixedSize(true);
                    GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void GetProductTop5() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.gettop5().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    List<Product> productListtop5=response.body();
                    productAdaptertop5=new ProductAdapterTop5(productListtop5,getActivity());
                    rctop5.setHasFixedSize(true);
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager((getActivity()) , LinearLayoutManager.HORIZONTAL, false);

                    rctop5.setLayoutManager(layoutManager);
                    rctop5.setAdapter(productAdaptertop5);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void getCategory() {
        apiService= RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    List<Category> listcate=response.body();
                    categoryAdapter=new CategoryAdapter(listcate,getActivity());
                    rc_loai.setHasFixedSize(true);
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

                    rc_loai.setLayoutManager(layoutManager);
                    rc_loai.setAdapter(categoryAdapter);
                }else{
                    int statusCode=response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}