package com.example.ecommerce_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        NavController navController = Navigation.findNavController(this, R.id.fragment2);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final MenuItem item = menu.findItem(R.id.search);
        final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setQueryHint("Search products");
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        item.getIcon().setVisible(false, false);
        return true;
    }


}


