package com.example.xuant.a14110208_foody.Model.ModelHeroku;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xuant on 14/05/2017.
 */

public class ReviewWhere {
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("RATING")
    @Expose
    private double rating;
    @SerializedName("COMMENT")
    @Expose
    private String comment;
    @SerializedName("COMMENTTRIM")
    @Expose
    private String commenttrim;
    @SerializedName("AVATAR")
    @Expose
    private String avatar;
    @SerializedName("ITEMID")
    @Expose
    private Integer item_id;
    @SerializedName("REVIEWURL")
    @Expose
    private String reviewurl;

    public ReviewWhere(){

    }

    public ReviewWhere(Integer id, String name, double rating, String comment, String commenttrim, String avatar, Integer item_id, String reviewurl) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
        this.commenttrim = commenttrim;
        this.avatar = avatar;
        this.item_id = item_id;
        this.reviewurl = reviewurl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommenttrim() {
        return commenttrim;
    }

    public void setCommenttrim(String commenttrim) {
        this.commenttrim = commenttrim;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getReviewurl() {
        return reviewurl;
    }

    public void setReviewurl(String reviewurl) {
        this.reviewurl = reviewurl;
    }
}
