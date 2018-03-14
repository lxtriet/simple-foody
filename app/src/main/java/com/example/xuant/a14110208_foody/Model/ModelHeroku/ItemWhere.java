package com.example.xuant.a14110208_foody.Model.ModelHeroku;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by xuant on 13/05/2017.
 */

public class ItemWhere {

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("RESTAURANTID")
    @Expose
    private Integer restaurant_id;
    @SerializedName("CATEGORYID")
    @Expose
    private Integer category_id;
    @SerializedName("DISTRICTID")
    @Expose
    private Integer district_id;
    @SerializedName("TOTALPICTURES")
    @Expose
    private Integer total_pictures;
    @SerializedName("TOTALREVIEWS")
    @Expose
    private Integer total_Reviews;
    @SerializedName("TYPEID")
    @Expose
    private Integer type_id;
    @SerializedName("CITYID")
    @Expose
    private Integer city_id;
    @SerializedName("STREETID")
    @Expose
    private Integer street_id;
    @SerializedName("AVGRATING")
    @Expose
    private Double avgRating;
    @SerializedName("ADDRESS")
    @Expose
    private String address;
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("IMG")
    @Expose
    private String img;

    private ArrayList<ReviewWhere> reviewWheres;

    public ItemWhere(){

    }

    public ItemWhere(Integer id, Integer restaurant_id, Integer category_id, Integer district_id, Integer total_pictures, Integer total_Reviews, Integer type_id, Integer city_id, Integer street_id, Double avgRating, String address, String name, String img, ArrayList<ReviewWhere> reviewWheres) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.category_id = category_id;
        this.district_id = district_id;
        this.total_pictures = total_pictures;
        this.total_Reviews = total_Reviews;
        this.type_id = type_id;
        this.city_id = city_id;
        this.street_id = street_id;
        this.avgRating = avgRating;
        this.address = address;
        this.name = name;
        this.img = img;
        this.reviewWheres = reviewWheres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public Integer getTotal_pictures() {
        return total_pictures;
    }

    public void setTotal_pictures(Integer total_pictures) {
        this.total_pictures = total_pictures;
    }

    public Integer getTotal_Reviews() {
        return total_Reviews;
    }

    public void setTotal_Reviews(Integer total_Reviews) {
        this.total_Reviews = total_Reviews;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getStreet_id() {
        return street_id;
    }

    public void setStreet_id(Integer street_id) {
        this.street_id = street_id;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<ReviewWhere> getReviewWheres() {
        return reviewWheres;
    }

    public void setReviewWheres(ArrayList<ReviewWhere> reviewWheres) {
        this.reviewWheres = reviewWheres;
    }
}
