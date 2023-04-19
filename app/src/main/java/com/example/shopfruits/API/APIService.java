package com.example.shopfruits.API;

import android.os.Message;

import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.CartItem;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.DiaChi;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.OrderItemEnity;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.User;

import java.util.List;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String username, @Field("hash_password") String password);

    @FormUrlEncoded
    @POST("chitiet")
    Call<Product> chitiet(@Field("productID") int id);
    @FormUrlEncoded
    @POST("sanphamcate")
    Call<List<Product>> sanphamcate(@Field("categoryID") int id);
    @GET("sanpham")
    Call<List<Product>> getProductAll();
    @GET("category")
    Call<List<Category>> getCategoryAll();
    @GET("top5")
    Call<List<Product>> gettop5();
    @FormUrlEncoded
    @POST("cartitem")
    Call<List<Cart>> cartitem(@Field("userID") int id);

    @POST("save")
    Call<CartItem> save(@Body CartItem cartItem);
    @POST("saveorder")
    Call<OrderEnity> saveDonHang(@Body OrderEnity orderEnity);
    @POST("saveorderitem")
    Call<OrderItemEnity> saveCHiTietDH(@Body OrderItemEnity orderEnity);
    @FormUrlEncoded
    @POST("getidcart")
    Call<CartEnity> getidcart(@Field("userID") int id);
    @FormUrlEncoded
    @POST("diaChi")
    Call<DiaChi> getdiachi(@Field("userID") int id);


    @FormUrlEncoded
    @POST("getdonhang")
    Call<List<OrderEnity>>getDonHang(@Field("userID") int id);
    @FormUrlEncoded
    @POST("getitemdh")
    Call<List<DonHangModel>>getitemdh(@Field("orderID") int id);

    @POST("updateuser")
    Call<User>update(@Body User orderEnity);
}
