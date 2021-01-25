package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.registeruser.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash extends AppCompatActivity {
    LottieAnimationView view;
    LottieAnimationView spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        final FirebaseUser user=mauth.getCurrentUser();
view=findViewById(R.id.lottieAnimationView);
spinner=findViewById(R.id.spinner);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                if(pref.getBoolean("activity_executed",false)&&user!=null){
                    startActivity(new Intent(splash.this, MainActivity.class));
                    finish();

                }
                else {
                    startActivity(new Intent(splash.this, login.class));
                    finish();
                }
            }
        },3000);

    }
}