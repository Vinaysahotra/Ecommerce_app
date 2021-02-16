package com.example.ecommerce_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.adapters.amazonadapter;
import com.example.ecommerce_app.models.amazonviewmodel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    amazonviewmodel viewmodel;
    int count = 1;
    LottieAnimationView loader;
    amazonadapter amazonadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        NavController navController = Navigation.findNavController(this, R.id.fragment2);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }


}


