package com.example.ecommerce_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.registeruser.login;
import com.example.ecommerce_app.userdata.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void run() {

                SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                if (user != null) {

                    DatabaseReference value = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("info");
                    value.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            user user1 = snapshot.getValue(com.example.ecommerce_app.userdata.user.class);
                            assert user1 != null;
                            String p = user1.getFingerprint();
                            if (p.equals("null")) {
                                startActivity(new Intent(splash.this, MainActivity.class));
                            } else {
                                startActivity(new Intent(splash.this, fingerprint.class));

                            }
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } else {
                    startActivity(new Intent(splash.this, login.class));
                    finish();
                }
            }
        }, 3000);

    }
}