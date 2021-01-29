package com.example.ecommerce_app.models;

public class product {
    public boolean amazonChoice;
    public boolean amazonPrime;
    public String asin;
    public boolean bestSeller;
    private String thumbnail;
    private String url;
    private String title;

    public product(boolean amazonChoice, boolean amazonPrime, String asin, boolean bestSeller, String thumbnail, String url, String title) {
        this.amazonChoice = amazonChoice;
        this.amazonPrime = amazonPrime;
        this.asin = asin;
        this.bestSeller = bestSeller;
        this.thumbnail = thumbnail;
        this.url = url;
        this.title = title;
    }

    public product(Object amazonChoice, Object amazonPrime, Object asin, Object bestSeller, Object thumbnail, Object url, Object title) {
        this.amazonChoice = (boolean) amazonChoice;
        this.amazonPrime = (boolean) amazonPrime;
        this.asin = (String) asin;
        this.bestSeller = (boolean) bestSeller;
        this.thumbnail = (String) thumbnail;
        this.url = (String) url;
        this.title = (String) title;
    }


    public boolean isAmazonChoice() {
        return amazonChoice;
    }

    public void setAmazonChoice(boolean amazonChoice) {
        this.amazonChoice = amazonChoice;
    }

    public boolean isAmazonPrime() {
        return amazonPrime;
    }

    public void setAmazonPrime(boolean amazonPrime) {
        this.amazonPrime = amazonPrime;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public boolean getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
