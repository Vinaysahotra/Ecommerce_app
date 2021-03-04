package com.example.ecommerce_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class fingerprint extends AppCompatActivity {
    public static String resultinfo = "";
    static String userID;
    static FirebaseAuth mauth;
    DatabaseReference firebaseDatabase;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        mauth = FirebaseAuth.getInstance();
        userID = mauth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("users").child(userID).child("info");
        showBiometric();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void showBiometric() {
        Executor executor = Executors.newSingleThreadExecutor();


        BiometricPrompt biometricPrompt2 = new BiometricPrompt.Builder(fingerprint.this).setTitle("fingerprint authentication").setSubtitle("place finger on sensor").setNegativeButton("retry", executor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showBiometric();
            }
        }).build();

        biometricPrompt2.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                showBiometric();
            }

            @Override
            public void onAuthenticationSucceeded(final BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                fingerprint.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(fingerprint.this, "authentication successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(fingerprint.this, MainActivity.class));
                        finish();


                    }
                });

            }
        });


    }


}