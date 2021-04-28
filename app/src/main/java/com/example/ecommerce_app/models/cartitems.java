package com.example.ecommerce_app.models;

public class cartitems {
    String url;
    String image;
    String title;
    String website;
    String price;

    public cartitems() {
    }

    public cartitems(String url, String image, String title, String website, String price) {
        this.url = url;
        this.image = image;
        this.title = title;
        this.website = website;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitleproduct() {
        return title;
    }

    public void setTitleproduct(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
