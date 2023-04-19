package com.example.shopfruits.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopfruits.API.APIService;
import com.example.shopfruits.API.RetrofitClient;
import com.example.shopfruits.API.constants;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.until.MaHoa;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etName,etPassword;

    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView Login=findViewById(R.id.logIn);
        TextView Sigin=findViewById(R.id.singUp);
        LinearLayout signup=findViewById(R.id.singUpLayout);
        LinearLayout login=findViewById(R.id.logInLayout);
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        etName = findViewById(R.id.eMail);
        etPassword = findViewById(R.id.passwords);
        findViewById(R.id.singIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Login.setBackgroundResource(R.drawable.switch_trcks);
                Sigin.setBackground(null);
                signup.setVisibility(View.GONE);
                Sigin.setTextColor(Color.rgb(255,0,55));
                login.setVisibility(View.VISIBLE);
            }
        });
        Sigin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Sigin.setBackgroundResource(R.drawable.switch_trcks);
                Login.setBackground(null);
                signup.setVisibility(View.VISIBLE);
                Sigin.setTextColor(Color.rgb(255,255,255));
                login.setVisibility(View.GONE);
            }
        });
    }
    private void userLogin() {
        final String username = etName.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etName.setError("Please enter username");
            etName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter password");
            etPassword.requestFocus();
            return;
        }
        Log.d("loggs", "userLogin: " + constants.ROOT_URL+constants.LOGIN);
        apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
        //password= MaHoa.toSHA1(password);
        apiService.login(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.isSuccessful()) {
                        User userLogin = response.body();
                        User user = new User(
                                userLogin.getUserID(),
                                userLogin.getName(),
                                userLogin.getEmail(),
                                userLogin.getPhone(),
                                userLogin.getSalt(),
                                userLogin.getHash_password(),
                                userLogin.getAvatar(),
                                userLogin.getRole(),
                                userLogin.getPoint()
                        );
                            SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                            finish();
                          Intent it=new Intent(LoginActivity.this,MainActivity.class);
                          startActivity(it);
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
}
