package com.example.shopfruits.Activity.User;

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
import com.example.shopfruits.Activity.Shipper.ShipperHome;
import com.example.shopfruits.Models.CartEnity;
import com.example.shopfruits.Models.User;
import com.example.shopfruits.Pref.SharePrefManager;
import com.example.shopfruits.R;
import com.example.shopfruits.until.MaHoa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etName,etPassword;
    EditText email,sdt,pass,repass;
    APIService apiService;
    User checkemail,checkphone;
    TextView Login,Sigin,dangky;
    LinearLayout signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Login=findViewById(R.id.logIn);
        Sigin=findViewById(R.id.singUp);
        dangky=findViewById(R.id.singIn);
        signup=findViewById(R.id.singUpLayout);
        login=findViewById(R.id.logInLayout);
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            User user = SharePrefManager.getInstance(this).getUser();
            if(Integer.parseInt(user.getRole().trim())==1) {
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
            } else if (Integer.parseInt(user.getRole().trim())==2) {
                Intent it = new Intent(LoginActivity.this, ShipperHome.class);
                startActivity(it);

            }
        }
        etName = findViewById(R.id.eMail);
        etPassword = findViewById(R.id.passwords);
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dangky.getText().equals("Đăng Ký"))
                {
                    SignInTK();
                }
                else {
                    userLogin();
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Login.setBackgroundResource(R.drawable.switch_trcks);
                Sigin.setBackground(null);
                signup.setVisibility(View.GONE);
                Sigin.setTextColor(Color.rgb(255,0,55));
                login.setVisibility(View.VISIBLE);
                dangky.setText("Đăng Nhâp");
            }
        });
        Sigin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Sigin.setBackgroundResource(R.drawable.switch_trcks);
                Login.setBackground(null);
                signup.setVisibility(View.VISIBLE);
                Sigin.setTextColor(Color.rgb(255,255,255));
                login.setVisibility(View.GONE);
                dangky.setText("Đăng Ký");

            }
        });
    }
    public void SignInTK()
    {
        email=findViewById(R.id.eMails);
        sdt=findViewById(R.id.sdt);
        pass=findViewById(R.id.passwordss);
        repass=findViewById(R.id.passwords01);


        String Email = email.getText().toString();
        String Phone = sdt.getText().toString();
        String Pass = pass.getText().toString();
        String Repass = repass.getText().toString();
        if (TextUtils.isEmpty(Email)) {
            email.setError("Please enter username");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Phone)) {
            sdt.setError("Please enter password");
            sdt.requestFocus();
            return;
        }if (TextUtils.isEmpty(Pass)) {
        pass.setError("Please enter password");
        pass.requestFocus();
        return;
    }if (TextUtils.isEmpty(Repass)) {
        repass.setError("Please enter password");
        repass.requestFocus();
        return;
    }
        if(Pass.equals(Repass)) {

            apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
            Pass = MaHoa.toSHA1(Pass);
            User us = new User();
            us.setEmail(Email);
            us.setName(Email);
            us.setPhone(Phone);
            us.setHash_password(Pass);
            us.setRole("1");
            us.setSalt("asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn");

            apiService.kiemtraEmail(Email).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    checkemail=response.body();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
            apiService.kiemtraphone(Phone).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    checkphone=response.body();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

            if(checkemail==null) {
                if (checkphone == null) {
                    apiService.dangky(us).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User usersaved = new User();
                            usersaved = response.body();
                            CartEnity cart = new CartEnity();
                            cart.setUserID(usersaved.getUserID());
                            apiService = RetrofitClient.getInstance().getRetrofit(constants.ROOT_URL).create(APIService.class);
                            apiService.taogiohang(cart).enqueue(new Callback<CartEnity>() {
                                @Override
                                public void onResponse(Call<CartEnity> call, Response<CartEnity> response) {

                                    Login.setBackgroundResource(R.drawable.switch_trcks);
                                    Sigin.setBackground(null);
                                    signup.setVisibility(View.GONE);
                                    Sigin.setTextColor(Color.rgb(255, 0, 55));
                                    login.setVisibility(View.VISIBLE);
                                    dangky.setText("Đăng Nhâp");
                                    Toast.makeText(LoginActivity.this, "Đã đăng ký thành công", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<CartEnity> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
                else {
                    email.setError("Email Đã Tồn Tại");
                    email.requestFocus();
                }
            }
            else {
                sdt.setError("Số Điện Thoại Đã Tồn Tại");
                sdt.requestFocus();
            }
        }

        else
        {
            repass.setError("Mật khẩu không giống");
            repass.requestFocus();
        }

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
        password= MaHoa.toSHA1(password);
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
                        SharePrefManager.getInstance(getApplicationContext()).userID(user.getUserID());
                        SharePrefManager.getInstance(getApplicationContext()).userLogin(user);
                        finish();
                        if(Integer.parseInt(userLogin.getRole().trim())==1) {
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);
                        } else if (Integer.parseInt(userLogin.getRole().trim())==2) {
                            Intent it = new Intent(LoginActivity.this, ShipperHome.class);
                            startActivity(it);

                        }


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
