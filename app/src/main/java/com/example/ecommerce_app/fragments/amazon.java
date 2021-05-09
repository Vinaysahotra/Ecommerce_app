package com.example.ecommerce_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.adapters.amazonadapter;
import com.example.ecommerce_app.models.Ebaviewmodel;
import com.example.ecommerce_app.models.amazonviewmodel;
import com.example.ecommerce_app.models.flipkartviewmodel;
import com.example.ecommerce_app.models.product;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;


public class amazon extends Fragment {

    LottieAnimationView loader;
    amazonadapter amazonadapter;
    MenuItem item;
    amazonviewmodel viewmodel;
    private Ebaviewmodel Ebayviewmodel;
    flipkartviewmodel flipkartviewmodel;
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

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        setHasOptionsMenu(true);


        RecyclerView recyclerView;
        ArrayList<product> productsarray = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        loader = view.findViewById(R.id.loader);
        amazonadapter = new amazonadapter(productsarray, getContext());
        loader.setVisibility(View.VISIBLE);
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
        recyclerView.setLayoutManager(new VegaLayoutManager());
        recyclerView.setAdapter(amazonadapter);

        return view;
    }


}