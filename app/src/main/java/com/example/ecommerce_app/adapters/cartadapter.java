package com.example.ecommerce_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.models.cartitems;
import com.example.ecommerce_app.webpage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cartadapter extends RecyclerView.Adapter<cartadapter.ViewHolder> {
    ArrayList<cartitems> products;
    Context context;
    FirebaseUser user;


    public cartadapter(ArrayList<cartitems> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public cartadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public void onBindViewHolder(@NonNull cartadapter.ViewHolder holder, final int position) {
        holder.productname.setText(products.get(position).getTitleproduct());
        holder.web.setText(products.get(position).getWebsite());
        user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        if (!products.get(position).getPrice().equals("0")) {
            holder.price.setText(R.string.Rs);
            holder.price.append(" " + products.get(position).getPrice());
        } else {
            holder.price.setText("no options");
        }
        Glide.with(context).load(products.get(position).getImage()).transform(new RoundedCorners(25)).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, webpage.class);
                intent.putExtra("url", products.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (position > 0) {
                    Query Query = ref.child(user.getUid()).child("cart").orderByChild("titleproduct").equalTo(products.get(position).getTitleproduct());

                    Query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productname;
        ImageView image;
        TextView price;
        Button buy;
        ImageView delete;
        TextView web;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.productname);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.product_img);
            buy = itemView.findViewById(R.id.cartout);
            delete = itemView.findViewById(R.id.remove_item);
            web = itemView.findViewById(R.id.web);


        }
    }
}
