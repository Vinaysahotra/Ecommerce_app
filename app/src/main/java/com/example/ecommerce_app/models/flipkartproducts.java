package com.example.ecommerce_app.models;


public class flipkartproducts {

    public String title;
    public String imgUrl;
    public String desc;
    public String produrl;
    public String price;
    public String[] specs = new String[5];
    public String flipkartSellingPrice;
    public String inStock;

    public flipkartproducts() {

    }

    public flipkartproducts(String title, String imgUrl, String desc, String price, String flipkartSellingPrice, String specs[]) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.desc = desc;
        this.price = price;
        this.flipkartSellingPrice = flipkartSellingPrice;
        this.inStock = inStock;
        this.specs = specs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String[] getSpecs() {
        return specs;
    }


    public String getProdUrl() {
        return produrl;
    }

    public void setProdUrl(String produrl) {
        this.produrl = produrl;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlipkartSellingPrice() {
        return flipkartSellingPrice;
    }

    public void setFlipkartSellingPrice(String flipkartSellingPrice) {
        this.flipkartSellingPrice = flipkartSellingPrice;
    }

}
