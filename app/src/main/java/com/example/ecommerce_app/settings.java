package com.example.ecommerce_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommerce_app.registeruser.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class settings extends AppCompatActivity {
    public static String resultinfo = "";
    static String userID;
    static FirebaseAuth mauth;
    Button signout;
    Button addfingerprint;
    FirebaseUser user;
    TextView email;
    Button remove_fingerprint;
    DatabaseReference firebaseDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        signout = findViewById(R.id.signout);
        addfingerprint = findViewById(R.id.addfingerprint);
        email = findViewById(R.id.email);
        remove_fingerprint = findViewById(R.id.remove);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mauth = FirebaseAuth.getInstance();
        userID = mauth.getCurrentUser().getUid();

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("users").child(userID).child("info");
        //email
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                com.example.ecommerce_app.userdata.user user1 = snapshot.getValue(com.example.ecommerce_app.userdata.user.class);
                email.setText(user1.getUseremail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//signout
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(settings.this, login.class));
            }
        });

//fingerprint add
        addfingerprint.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View v) {

                Executor executor = Executors.newSingleThreadExecutor();
                mauth = FirebaseAuth.getInstance();
                userID = mauth.getCurrentUser().getUid();


                BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(settings.this).setTitle("Add fingerprint authentication").setSubtitle("move your finger ..").setNegativeButton("cancel", executor, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).build();
                biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {


                    @Override
                    public void onAuthenticationSucceeded(final BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        settings.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                resultinfo = result.toString();
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("fingerprint", resultinfo);
                                firebaseDatabase.updateChildren(map);
                                Toast.makeText(settings.this, "done", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                });


            }
        });
//remove fingerprint
        remove_fingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("fingerprint", "null");
                firebaseDatabase.updateChildren(map);
                Toast.makeText(settings.this, "done", Toast.LENGTH_LONG).show();
            }
        });
    }


}