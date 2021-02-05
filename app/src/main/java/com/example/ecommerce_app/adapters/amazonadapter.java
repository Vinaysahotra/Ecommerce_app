package com.example.ecommerce_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.amazon_product_page;
import com.example.ecommerce_app.models.product;

import java.util.ArrayList;

public class amazonadapter extends RecyclerView.Adapter<amazonadapter.ViewHolder> {
    private ArrayList<product> products;
    private Context context;

    public amazonadapter(ArrayList<product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public amazonadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_amazon, parent, false);

        return new ViewHolder(view);
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
    public void onBindViewHolder(@NonNull amazonadapter.ViewHolder holder, final int position) {
        holder.title.setText(products.get(position).getTitle());
        Glide.with(context).load(products.get(position).getThumbnail()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        if (products.get(position).getRating() != null) {
            holder.ratingBar.setRating(Float.parseFloat(products.get(position).getRating()));
        }
        if (!products.get(position).getDiscount().equals("0")) {
            holder.dis.setText("Discount:" + products.get(position).getDiscount() + "%");
        }
        if (products.get(position).getPrice() > 0) {
            holder.price.setText(R.string.Rs);
            holder.price.append(" " + products.get(position).getPrice());
        } else {
            holder.price.setText("no options");
        }
        if (products.get(position).isAmazonChoice()) {
            holder.choice.setImageResource(R.drawable.choice);
            Log.d("choice", products.get(position).getTitle());
        }

        if (products.get(position).isAmazonPrime()) {
            holder.prime.setImageResource(R.drawable.prime);
        }
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, amazon_product_page.class);
                intent.putExtra("url", products.get(position).getUrl());
                context.startActivity(intent);
            }
        });

    }

    public void setallproducts(ArrayList<product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button buy;
        TextView title;
        ImageView prime;
        ImageView choice;
        RatingBar ratingBar;
        TextView price;
        TextView dis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail);
            buy = itemView.findViewById(R.id.buy_now);
            ratingBar = itemView.findViewById(R.id.rating);
            title = itemView.findViewById(R.id.title);
            choice = itemView.findViewById(R.id.choice);
            dis = itemView.findViewById(R.id.discount);
            price = itemView.findViewById(R.id.price);
            prime = itemView.findViewById(R.id.prime);

        }
    }
}
