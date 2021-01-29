package com.example.ecommerce_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce_app.R;
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
    public void onBindViewHolder(@NonNull amazonadapter.ViewHolder holder, int position) {
        holder.title.setText(products.get(position).getTitle());
        holder.url.setText(products.get(position).getUrl());
        Glide.with(context).load(products.get(position).getThumbnail()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return products.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView url;
        TextView title;
        TextView prime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.thumbnail);
            url = itemView.findViewById(R.id.url);
            title = itemView.findViewById(R.id.title);
            prime = itemView.findViewById(R.id.prime);

        }
    }
}
