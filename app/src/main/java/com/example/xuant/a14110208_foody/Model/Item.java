package com.example.xuant.a14110208_foody.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xuant on 14/04/2017.
 */

public class Item implements Serializable {
     Integer id, restaurant_id, category_id, district_id, total_pictures, total_Reviews, type_id;
     Double avgRating;
     String address, name, img;
     ArrayList<Review> reviews;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getTotal_Reviews() {
        return total_Reviews;
    }

    public void setTotal_Reviews(Integer total_Reviews) {
        this.total_Reviews = total_Reviews;
    }

    public Integer getTotal_pictures() {
        return total_pictures;
    }

    public void setTotal_pictures(Integer total_pictures) {
        this.total_pictures = total_pictures;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
