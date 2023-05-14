package com.example.shopfruits.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.shopfruits.databinding.ActivityAdminBinding;


public class AdminActivity extends AppCompatActivity {
private ActivityAdminBinding binding;
@SuppressLint({"SetJavaScriptEnabled","WebViewApiAvailability"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.getSettings().setBuiltInZoomControls(true);
        binding.webview.getSettings().setDomStorageEnabled(true);
        binding.webview.getSettings().setDatabaseEnabled(true);
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        binding.webview.loadUrl("http://192.168.213.92:14571/DOANCUOIKY/admin/login");

    }
}