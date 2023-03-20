package com.example.shopfruits;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        TextView Login=findViewById(R.id.logIn);
        TextView Sigin=findViewById(R.id.singUp);
        LinearLayout signup=findViewById(R.id.singUpLayout);
        LinearLayout login=findViewById(R.id.logInLayout);
        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Login.setBackgroundResource(R.drawable.switch_trcks);
                Sigin.setBackground(null);
                signup.setVisibility(View.GONE);
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
}
