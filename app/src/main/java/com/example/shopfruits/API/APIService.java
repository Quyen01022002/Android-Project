package com.example.shopfruits.API;

import com.example.shopfruits.Models.Cart;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.CartItem;
import com.example.shopfruits.Models.Category;
import com.example.shopfruits.Models.DiaChi;
import com.example.shopfruits.Models.DonHangModel;
import com.example.shopfruits.Models.DonHang_Shop_Model;
import com.example.shopfruits.Models.FollowUsserModel;
import com.example.shopfruits.Models.OrderEnity;
import com.example.shopfruits.Models.OrderItemEnity;
import com.example.shopfruits.Models.Product;
import com.example.shopfruits.Models.ProductOnTop5;
import com.example.shopfruits.Models.Review;
import com.example.shopfruits.Models.ReviewModel;
import com.example.shopfruits.Models.Stores;
import com.example.shopfruits.Models.ThongKeModel;
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
    @POST("theodoi")
    Call<FollowUsserModel> follow(@Body FollowUsserModel fl);

    @POST("dkshop")
    Call<Stores> dkshop(@Body Stores st);
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
    @POST("listdanhgia")
    Call<List<ReviewModel>> getlistdanhgia(@Field("productID") int id);
    @POST("danhgia")
    Call<Review> Danhgia(@Body Review review);
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
    @POST("getTTdh")
    Call<List<OrderEnity>>getDonHangbyTT(@Field("userID") int id,@Field("status") String TT);
    @FormUrlEncoded
    @POST("donhangshop")
    Call<List<DonHang_Shop_Model>>getDHByShop(@Field("storeID") int id);
    @FormUrlEncoded
    @POST("trangthai")
    Call<List<DonHang_Shop_Model>>GetTrangThai(@Field("storeID") int id,@Field("status")  String status);
    @FormUrlEncoded
    @POST("checkdanhgia")
    Call<List<Review>> checkdanhgia(@Field("orderID") int id,@Field("userID")  int userID);
    @FormUrlEncoded
    @POST("tinhratingsp")
    Call<Review> tinhrating(@Field("productID") int id);
    @FormUrlEncoded
    @POST("DHshipper")
    Call<List<DonHang_Shop_Model>>GetTrangThaishiper(@Field("shipperID") int id,@Field("status")  String status);
    @FormUrlEncoded
    @POST("timkiem")
    Call<List<Product>>timkiem(@Field("key")  String key);
    @FormUrlEncoded
    @POST("followed")
    Call<List<FollowUsserModel>> CheckFollow(@Field("userID") int id, @Field("storeID")  int storeID);
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
    @DELETE("/follow/{id}")
    Call<Void> unfollow(@Path("id") int id);
    @FormUrlEncoded
    @POST("thongke")
    Call<List<ThongKeModel>> getThongKeTheoThang(@Field("year") int year, @Field("storeID") int id);

    @FormUrlEncoded
    @POST("gettop5productofstore")
    Call<List<ProductOnTop5>>getTop5ProOfStore(@Field("storeID") int storeID, @Field("year") int year);



    //API Chưa viết

    @FormUrlEncoded
    @POST("getfollowing")
    Call <List<User>> getKhachHangTheoDoiShop(@Field("storeID") int storeID);


    @FormUrlEncoded
    @POST("getcustomerpaid")
    Call <List<User>> getKhachHangDaMuaHang(@Field("storeID") int storeID);


    @FormUrlEncoded
    @POST("getshipperofstore")
    Call <List<User>> getShipperOfStore(@Field("storeID") int storeID);

    @FormUrlEncoded
    @POST("getorderofshipperonstore")
    Call <List<DonHang_Shop_Model>> getOrderOfShipperOnStore(@Field("shipperID") int shipperID,@Field("storeID") int storeID);

    @FormUrlEncoded
    @POST("getshipper")
    Call <User> getShipper(@Field("shipperID") int shipperID);



}



