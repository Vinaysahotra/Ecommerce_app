package com.example.ecommerce_app.models;

public class product {
    public boolean amazonChoice;
    public boolean amazonPrime;
    public String asin;
    public boolean bestSeller;
    private String thumbnail;
    private String url;
    private String title;
    private String price;
    private String rating;
    private String discount;

    public product(String discount, String rating, String price, Object amazonChoice, Object amazonPrime, Object asin, Object bestSeller, Object thumbnail, Object url, Object title) {
        this.amazonChoice = (boolean) amazonChoice;
        this.amazonPrime = (boolean) amazonPrime;
        this.asin = (String) asin;
        this.bestSeller = (boolean) bestSeller;
        this.thumbnail = (String) thumbnail;
        this.url = (String) url;
        this.discount = discount;
        this.title = (String) title;
        this.price = (String) price;
        this.rating = rating;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isAmazonChoice() {
        return amazonChoice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
