package com.example.tranbuuquyen_tuan08;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://app.iotstar.vn/appfoods/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}*/
public class    RetrofitClient {

    public static RetrofitClient retrofitClient;
    private Retrofit retrofit = null;



    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }
    public Retrofit getRetrofit(String url){
        return changeUrl(url);
    }
    private Retrofit changeUrl(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}