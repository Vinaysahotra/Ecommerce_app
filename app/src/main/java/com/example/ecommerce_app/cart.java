package com.example.ecommerce_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce_app.adapters.cartadapter;
import com.example.ecommerce_app.models.cartitems;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cart extends AppCompatActivity {
    DatabaseReference reference;
    FirebaseUser user;
    RecyclerView recyclerView;
    cartadapter cartadapter;
    double total = 0;
    TextView totaltext;
    ArrayList<cartitems> cartitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        cartitems = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = findViewById(R.id.cartrecycler);
        totaltext = findViewById(R.id.total);
        reference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid()).child("cart");
        reference.addValueEventListener(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cartitems.clear();
                total = 0;
                for (DataSnapshot users : snapshot.getChildren()) {
                    cartitems item = users.getValue(cartitems.class);
                    cartitems.add(item);
                    assert item != null;
                    total = total + Double.parseDouble(item.getPrice());
                    totaltext.setText("Total : ");
                    Log.d("total", String.valueOf(total));
                    cartadapter = new cartadapter(cartitems, cart.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(cart.this));
                    recyclerView.setAdapter(cartadapter);
                }
                totaltext.append(String.valueOf(total));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}