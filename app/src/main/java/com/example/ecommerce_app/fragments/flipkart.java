package com.example.ecommerce_app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ecommerce_app.MainActivity;
import com.example.ecommerce_app.R;
import com.example.ecommerce_app.adapters.flipkartAdapter;
import com.example.ecommerce_app.models.amazonviewmodel;
import com.example.ecommerce_app.models.flipkartproducts;
import com.example.ecommerce_app.models.flipkartviewmodel;
import com.example.ecommerce_app.settings;

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
    LottieAnimationView loading;
    MenuItem item;
    amazonviewmodel amazonviewmodel;
    LottieAnimationView loader;
    private TextView responsetxt;
    private String mParam1;
    private String mParam2;

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

        final View view = inflater.inflate(R.layout.flipkart, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_flipkart);
        responsetxt = view.findViewById(R.id.flip);

        loading = view.findViewById(R.id.loading_flip);
        amazonviewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(amazonviewmodel.class);
        setHasOptionsMenu(true);
        ArrayList<flipkartproducts> flipkartproducts = new ArrayList<>();
        loading.setVisibility(View.VISIBLE);
        adapter = new flipkartAdapter(getContext(), flipkartproducts);
        flipkartviewmodel = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(flipkartviewmodel.class);
        flipkartviewmodel.getAllFlipkartproduct().observe(getViewLifecycleOwner(), new Observer<ArrayList<com.example.ecommerce_app.models.flipkartproducts>>() {
            @Override
            public void onChanged(ArrayList<com.example.ecommerce_app.models.flipkartproducts> flipkartproducts) {
                adapter.setAllproducts(flipkartproducts);
                loading.setVisibility(View.GONE);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        return view;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.toolbar_icons, menu);
        item = menu.findItem(R.id.search);
        MenuItem item1 = menu.findItem(R.id.settings);

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(getContext(), settings.class));

                return false;
            }
        });
        final SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        searchView.setQueryHint("search products");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query == "" || query == null)
                    item.collapseActionView();
                loading.setVisibility(View.VISIBLE);
                searchView.setIconified(true);
                amazonviewmodel.setAmazonproducts(query);
                flipkartviewmodel.setallproducts(query);
                item.collapseActionView();

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
        searchView.setIconified(true);
        item.collapseActionView();

    }


}