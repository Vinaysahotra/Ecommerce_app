package com.example.ecommerce_app.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Ebaviewmodel extends ViewModel {
    String Appid = "AkashDha-edroid-PRD-110683bd3-0ab96331";
    private MutableLiveData<ArrayList<ProductsEba>> allproduct;

    public MutableLiveData<ArrayList<ProductsEba>> getAllproduct() {
        if (allproduct == null) {
            allproduct = new MutableLiveData<>();
            setallproducts("all products");

        }
        return allproduct;
    }


    public void setallproducts(final String keyword) {
        final ArrayList<ProductsEba> productsarray = new ArrayList<>();
        final DecimalFormat df = new DecimalFormat("0.00");
        if (allproduct == null) {
            allproduct = new MutableLiveData<>();
            setallproducts("all products");

        }
        try {

            OkHttpClient client = new OkHttpClient.Builder(networkclient.getUnsafeOkHttpClient().build())
                    .connectTimeout(10, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(10, TimeUnit.MINUTES) // write timeout
                    .readTimeout(10, TimeUnit.MINUTES) // read timeout
                    .build();
            final Request request = new Request.Builder()
                    .url("http://svcs.ebay.com/services/search/FindingService/v1?" +
                            "OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.0.0&SECURITY-APPNAME=" + Appid + "" +
                            "&GLOBAL-ID=EBAY-IN&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&sortOrder=BestMatch&keywords=" + keyword)

                    .build();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();

                    Log.d("error", e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                    String jsonstr = response.body().string();
                    Log.d("ebay response", jsonstr);

                    try {
                        JSONObject jsonObject = new JSONObject(jsonstr);
                        JSONArray fibkr = jsonObject.getJSONArray("findItemsByKeywordsResponse");
                        JSONObject f1 = fibkr.getJSONObject(0);
                        JSONArray search = f1.getJSONArray("searchResult");
                        JSONObject f2 = search.getJSONObject(0);
                        JSONArray item = f2.getJSONArray("item");

                        for (int i = 0; i < item.length(); i++) {
                            JSONObject f3 = item.getJSONObject(i);
                            JSONArray title = f3.getJSONArray("title");
                            JSONArray itemlink = f3.getJSONArray("viewItemURL");

                            JSONArray image = f3.getJSONArray("galleryURL");
                            JSONArray ss = f3.getJSONArray("sellingStatus");
                            JSONObject f4 = ss.getJSONObject(0);
                            JSONArray currentprice = f4.getJSONArray("currentPrice");
                            JSONObject pricejson = currentprice.getJSONObject(0);
                            ProductsEba products = new ProductsEba();

                            products.title = title.getString(0);
                            products.produrl = itemlink.getString(0);
                            products.imgurl = image.getString(0);
                            products.price = String.valueOf(df.format(Double.parseDouble(pricejson.getString("__value__")) * 74.57));

                            productsarray.add(products);
                        }

                        allproduct.postValue(productsarray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}


