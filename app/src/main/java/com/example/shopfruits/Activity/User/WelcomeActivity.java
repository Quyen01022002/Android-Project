package com.example.shopfruits.Activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopfruits.R;

public class WelcomeActivity extends AppCompatActivity {
    ImageView imgStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_activity);
        imgStart=findViewById(R.id.imgViewstart);
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });
        new Thread(new Runnable(){

            public void run() {
                int n = 0;
                try {
                    do {
                        if (n >= 2000) {
                            WelcomeActivity.this.finish();
                            Intent it = new Intent(WelcomeActivity.this.getApplicationContext(), (Class) LoginActivity.class);
                            WelcomeActivity.this.startActivity(it);
                            return;
                        }
                        Thread.sleep((long) 100);
                        n += 100;
                    }
                    while (true);
                } catch (InterruptedException interruptedException) {
                    WelcomeActivity.this.finish();
                    Intent it = new Intent(WelcomeActivity.this.getApplicationContext(), (Class) LoginActivity.class);
                    WelcomeActivity.this.startActivity(it);
                    return;
                } catch (Throwable throwable) {
                    WelcomeActivity.this.finish();
                    Intent it = new Intent(WelcomeActivity.this.getApplicationContext(), (Class) LoginActivity.class);
                    WelcomeActivity.this.startActivity(it);
                    throw throwable;
                }
            }
        }).start();

    }
}
