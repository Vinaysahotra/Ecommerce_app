package com.example.ecommerce_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.MainActivity;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.adapters.amazonadapter;
import com.example.ecommerce_app.models.amazonviewmodel;
import com.example.ecommerce_app.models.product;
import com.example.ecommerce_app.settings;

import java.util.ArrayList;


public class amazon extends Fragment {

    LottieAnimationView loader;
    amazonadapter amazonadapter;
    amazonviewmodel viewmodel;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public amazon() {

    }


    public static amazon newInstance(String param1, String param2) {
        amazon fragment = new amazon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.amazon, container, false);
        setHasOptionsMenu(true);


        RecyclerView recyclerView;
        ArrayList<product> productsarray = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview_amazon);
        loader = view.findViewById(R.id.loading_amazon);
        loader.setVisibility(View.VISIBLE);
        amazonadapter = new amazonadapter(productsarray, getContext());
        viewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(amazonviewmodel.class);
        viewmodel.getAllproduct().observe(getViewLifecycleOwner(), new Observer<ArrayList<product>>() {
            @Override
            public void onChanged(ArrayList<product> products) {
                if (products != null) {
                    amazonadapter.setallproducts(products);
                    loader.setVisibility(View.GONE);
                }

            }
        });


        amazonadapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(amazonadapter);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_icons, menu);
        MenuItem item = menu.findItem(R.id.search);
        MenuItem item1 = menu.findItem(R.id.settings);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getActivity(), settings.class));

                return false;
            }
        });
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        searchView.setQueryHint("search products");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loader.setVisibility(View.VISIBLE);
                viewmodel.setAllproducts(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );
    }

}