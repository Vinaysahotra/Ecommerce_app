package com.example.ecommerce_app.models;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class flipkartviewmodel extends ViewModel {
    private MutableLiveData<ArrayList<flipkartproducts>> allflipkartproduct;

    public MutableLiveData<ArrayList<flipkartproducts>> getAllFlipkartproduct() {
        if (allflipkartproduct == null) {
            allflipkartproduct = new MutableLiveData<>();
            setallproducts("all products");

        }
        return allflipkartproduct;
    }

    public void setallproducts(String product) {
        if (allflipkartproduct == null) {
            allflipkartproduct = new MutableLiveData<>();
            setallproducts("all products");

        }
        Flipkart t1 = new Flipkart();
        t1.execute("https://affiliate-api.flipkart.net/affiliate/1.0/search.json?query=" + product + "&resultCount=10");
    }

    class Flipkart extends AsyncTask<String, Void, String> {


        final ArrayList<flipkartproducts> productsarray = new ArrayList<>();
        String jsonstr = "";
        String line = "";
        String resultSet = null;
        String[] specs = new String[5];

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Fk-Affiliate-Id", "akashdeveloper");
                connection.setRequestProperty("Fk-Affiliate-Token", "281eb157bf61470b91ba4fa9a2cdc98e");
                connection.connect();
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));


                while ((line = br.readLine()) != null) {
                    jsonstr += line + "\n";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (jsonstr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonstr);
                    JSONArray array = jsonObject.getJSONArray("products");

                    for (int i = 0; i < array.length(); i++) {

                        flipkartproducts products = new flipkartproducts();
                        JSONObject firstarray = array.getJSONObject(i);
                        JSONObject quote = firstarray.getJSONObject("productBaseInfoV1");
                        JSONObject specs1 = firstarray.getJSONObject("categorySpecificInfoV1");
                        JSONArray detailedspecs = specs1.getJSONArray("detailedSpecs");
                        int size = detailedspecs.length();
                        for (int s = 0; s < size && s < 5; s++) {
                            products.specs[s] = detailedspecs.getString(s);
                        }
                        JSONObject quote1 = quote.getJSONObject("imageUrls");
                        JSONObject quote2 = quote.getJSONObject("maximumRetailPrice");
                        JSONObject quote3 = quote.getJSONObject("flipkartSpecialPrice");

                        products.title = quote.getString("title");
                        products.produrl = quote.getString("productUrl");
                        products.desc = quote.getString("productDescription");
                        products.imgUrl = quote1.getString("400x400");
                        products.price = quote2.getString("amount");
                        products.flipkartSellingPrice = quote3.getString("amount");

                        productsarray.add(products);
//                   Log.d("flipkart response :....",products.title+" "+products.produrl);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
            return resultSet;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            allflipkartproduct.postValue(productsarray);

        }

    }
}
