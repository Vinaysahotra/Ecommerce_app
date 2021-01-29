package com.example.ecommerce_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ecommerce_app.adapters.amazonadapter;
import com.example.ecommerce_app.models.product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<product> productsarray=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom);
        NavController navController= Navigation.findNavController(this,R.id.fragment2);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadjson(productsarray);
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();



    }
    private void loadjson(final ArrayList <product>productsarray) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://amazon-product-reviews-keywords.p.rapidapi.com/product/search?keyword=all&country=IN&category=aps")
                .get()
                .addHeader("x-rapidapi-key", "a9c0fc1617msh954090b3400ff9cp1fd0c2jsn4c34ae2dfc8d")
                .addHeader("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray products = jsonObject.getJSONArray("products");

                        for (int i = 0; i < products.length(); i++) {
                            JSONObject product = products.getJSONObject(i);
                            product product1 = new product(product.get("amazonChoice"), product.get("amazonPrime"), product.get("asin"), product.get("bestSeller"), product.get("thumbnail"), product.get("url"), product.get("title"));
                            productsarray.add(product1);

                        }
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview_amazon);
                                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                amazonadapter amazonadapter=new amazonadapter(productsarray,MainActivity.this);
                                recyclerView.setAdapter(amazonadapter);

                            }
                        });
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }



                }
            }
        });

    }

}