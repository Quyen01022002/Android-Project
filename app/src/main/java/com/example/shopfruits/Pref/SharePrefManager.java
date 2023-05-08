package com.example.shopfruits.Pref;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.shopfruits.Activity.User.LoginActivity;
import com.example.shopfruits.Models.User;

public class SharePrefManager {
    private static final  String SHARED_PREF_NAME = "volleyregisterlogin";
    private static final String KEY_USERID = "keyuserid";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keygmail";
    private static final String KEY_PHONE= "keyphone";
    private static final String KEY_SALT = "keysalt";
    private static final String KEY_HASH_PASSWORD = "keyhashpassword";
    private static final String KEY_ROLE = "keyrole";
    private static final String KEY_AVATAR = "keyavatar";
    private static final String KEY_ponit = "keypoint";

    private static SharePrefManager mInstance;
    private static Context ctx;
    private SharePrefManager(Context context){
        ctx = context;
    }
    public static synchronized SharePrefManager getInstance(Context context){
        if(mInstance ==null){
            mInstance = new SharePrefManager(context);
        }
        return mInstance;
    }
    public void cartID(int cartid)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cart", cartid);
        editor.apply();
    }
    public int getcartID()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt("cart",0);
    }

    public void StoreID(int storeId)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("store", storeId);
        editor.apply();
    }
    public int getStoreID()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt("store",0);
    }
    public void userID(int cartid)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user", cartid);
        editor.apply();
    }
    public int getuserID()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt("user",0);
    }
    public void DHID(int cartid)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("dhid", cartid);
        editor.apply();
    }
    public int getDHID()
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt("dhid",0);
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USERID, user.getUserID());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.putString(KEY_SALT, user.getSalt());
        editor.putString(KEY_HASH_PASSWORD, user.getHash_password());
        editor.putString(KEY_AVATAR, user.getAvatar());
        editor.putString(KEY_ROLE, user.getRole());
        editor.putFloat(KEY_ponit, user.getPoint());
        editor.apply();
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null) !=null;
    }
    public User getUser(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_USERID,-1),
                sharedPreferences.getString(KEY_NAME,null),
                sharedPreferences.getString(KEY_EMAIL,null),
                sharedPreferences.getString(KEY_PHONE,null),
                sharedPreferences.getString(KEY_SALT,null),
                sharedPreferences.getString(KEY_HASH_PASSWORD,null),
                sharedPreferences.getString(KEY_AVATAR,null),
                sharedPreferences.getString(KEY_ROLE,null),
                sharedPreferences.getFloat(KEY_ponit,0)



        );
    }
    public void logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }
}
