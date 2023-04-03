package com.example.tranbuuquyen_tuan08;

import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoryAll();

    @GET("category.php")
    Call<Category> getCategory();

    @GET("lastproduct.php")
    Call<List<Product>> getProductAll();
    @FormUrlEncoded
    @POST("getcategory.php")
    Call<List<Product>> getCategorybyid(
            @Field("idcategory") int idcategory
    );
    @FormUrlEncoded
    @POST("registrationapi.php?apicall=login")
    Call<UserLogin> login(@Field("username") String username,@Field("password") String password);

    @Multipart
    @POST("updateimages.php")
    Call<result> upload(@Part("id") RequestBody id,
        @Part MultipartBody.Part images);
    @Multipart
    @POST("upload1.php")
    Call<Message> upload1(@Part(constants.MY_IMAGES)RequestBody username,
                         @Part MultipartBody.Part avatar);

}
