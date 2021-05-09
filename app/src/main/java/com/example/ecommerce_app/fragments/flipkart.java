package com.example.ecommerce_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.adapters.flipkartAdapter;
import com.example.ecommerce_app.models.Ebaviewmodel;
import com.example.ecommerce_app.models.amazonviewmodel;
import com.example.ecommerce_app.models.flipkartproducts;
import com.example.ecommerce_app.models.flipkartviewmodel;
import com.stone.vega.library.VegaLayoutManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link flipkart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class flipkart extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    flipkartAdapter adapter;
    flipkartviewmodel flipkartviewmodel;
    RecyclerView recyclerView;
    public LottieAnimationView loading;
    MenuItem item;
    amazonviewmodel amazonviewmodel;

    private TextView responsetxt;
    private String mParam1;
    private String mParam2;
    private Ebaviewmodel Ebayviewmodel;

    public flipkart() {
        // Required empty public constructor
    }

    public static flipkart newInstance(String param1, String param2) {
        flipkart fragment = new flipkart();

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

        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        loading = view.findViewById(R.id.loader);

        loading.setVisibility(View.VISIBLE);
        setHasOptionsMenu(true);
        ArrayList<flipkartproducts> flipkartproducts = new ArrayList<>();
        adapter = new flipkartAdapter(getContext(), flipkartproducts);
        flipkartviewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(flipkartviewmodel.class);
        flipkartviewmodel.getAllFlipkartproduct().observe(getViewLifecycleOwner(), new Observer<ArrayList<com.example.ecommerce_app.models.flipkartproducts>>() {
            @Override
            public void onChanged(ArrayList<com.example.ecommerce_app.models.flipkartproducts> flipkartproducts) {
                adapter.setAllproducts(flipkartproducts);
                loading.setVisibility(View.GONE);
            }
        });
        recyclerView.setLayoutManager(new VegaLayoutManager());
        adapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        return view;

    }



}