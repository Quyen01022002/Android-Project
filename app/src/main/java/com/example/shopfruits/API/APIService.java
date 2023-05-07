package com.example.shopfruits.API;

import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.CartItem;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.DiaChi;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.OrderItemEnity;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String username, @Field("hash_password") String password);

    @FormUrlEncoded
    @POST("chitiet")
    Call<Product> chitiet(@Field("productID") int id);
    @FormUrlEncoded
    @POST("sanphamstore")
    Call<List<Product>> sanphamstore(@Field("storeID") int id);
    @POST("savesanpham")
    Call<Product> savesp(@Body Product product);
    @FormUrlEncoded
    @POST("sanphamcate")
    Call<List<Product>> sanphamcate(@Field("categoryID") int id);
    @GET("sanpham")
    Call<List<Product>> getProductAll();
    @FormUrlEncoded
    @POST("kiemtraemail")
    Call<User> kiemtraEmail(@Field("email") String id);
    @FormUrlEncoded
    @POST("kiemtraphone")
    Call<User> kiemtraphone(@Field("phone") String id);
    @GET("category")
    Call<List<Category>> getCategoryAll();
    @GET("danhsachshipper")
    Call<List<User>> getdanhsachshipper();
    @GET("top5")
    Call<List<Product>> gettop5();
    @FormUrlEncoded
    @POST("cartitem")
    Call<List<Cart>> cartitem(@Field("userID") int id);

    @POST("save")
    Call<CartItem> save(@Body CartItem cartItem);
    @POST("saveorder")
    Call<OrderEnity> saveDonHang(@Body OrderEnity orderEnity);
    @POST("dangky")
    Call<User> dangky(@Body User user);
    @POST("taogiohang")
    Call<CartEnity> taogiohang(@Body CartEnity cartEnity);
    @FormUrlEncoded
    @POST("getorder")
    Call<OrderEnity> getorder(@Field("orderID") int id);
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
    @POST("donhangshop")
    Call<List<DonHang_Shop_Model>>getDHByShop(@Field("storeID") int id);
    @FormUrlEncoded
    @POST("trangthai")
    Call<List<DonHang_Shop_Model>>GetTrangThai(@Field("storeID") int id,@Field("status")  String status);
    @FormUrlEncoded
    @POST("TTDH_shipper")
    Call<List<DonHang_Shop_Model>>GetTrangThaishiper(@Field("shipperID") int id,@Field("status")  String status);
    @FormUrlEncoded
    @POST("getstore")
    Call<List<Stores>>getstore(@Field("ownerID") int id);
    @FormUrlEncoded
    @POST("getstorebyid")
    Call<Stores>getstorebyid(@Field("storeID") int id);
    @FormUrlEncoded
    @POST("getitemdh")
    Call<List<DonHangModel>>getitemdh(@Field("orderID") int id);

    @POST("updateuser")
    Call<User>update(@Body User orderEnity);
    @POST("capnhatdonhang")
    Call<OrderEnity>capnhat(@Body OrderEnity orderEnity);
    @POST("updateproduct")
    Call<Product>updateproduct(@Body Product product);
    @DELETE("/cart/{id}")
    Call<Void> deleteProduct(@Path("id") int id);
}
