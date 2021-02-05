package com.example.ecommerce_app.models;

public class reviews {
    private long total_reviews;
    private double rating;

    public reviews(long total_reviews, double rating) {
        this.total_reviews = total_reviews;
        this.rating = rating;
    }

    public long getTotal_reviews() {
        return total_reviews;
    }

    public void setTotal_reviews(long total_reviews) {
        this.total_reviews = total_reviews;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
