package com.example.ecommerce_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.adapters.EbayAdapter;
import com.example.ecommerce_app.models.Ebaviewmodel;
import com.example.ecommerce_app.models.ProductsEba;
import com.example.ecommerce_app.models.amazonviewmodel;
import com.example.ecommerce_app.models.flipkartviewmodel;

import java.util.ArrayList;

public class Ebay extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Ebaviewmodel Ebayviewmodel;
    EbayAdapter adapter;
    RecyclerView recyclerView;
    LottieAnimationView loader;
    MenuItem item;
    private String mParam1;
    private String mParam2;
    private flipkartviewmodel flipkartviewmodel;
    private amazonviewmodel amazonviewmodel;

    public Ebay() {
        // Required empty public constructor
    }


    public static Ebay newInstance(String param1, String param2) {
        Ebay fragment = new Ebay();
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

        final View view = inflater.inflate(R.layout.ebay, container, false);
        ArrayList<ProductsEba> productsEbas = new ArrayList<>();
        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new EbayAdapter(productsEbas, getContext());
        loader = view.findViewById(R.id.loader);
        loader.setVisibility(View.VISIBLE);
        flipkartviewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(flipkartviewmodel.class);
        amazonviewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(amazonviewmodel.class);
        Ebayviewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(Ebaviewmodel.class);
        Ebayviewmodel.getAllproduct().observe(getViewLifecycleOwner(), new Observer<ArrayList<ProductsEba>>() {
            @Override
            public void onChanged(ArrayList<ProductsEba> products) {
                adapter.setAllproducts(products);
                loader.setVisibility(View.GONE);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }


}