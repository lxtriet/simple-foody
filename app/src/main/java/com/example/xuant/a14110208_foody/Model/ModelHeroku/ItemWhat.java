package com.example.xuant.a14110208_foody.Model.ModelHeroku;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xuant on 14/05/2017.
 */

public class ItemWhat {
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("ADDRESS")
    @Expose
    private String address;
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("FOODNAME")
    @Expose
    private String foodname;
    @SerializedName("IMG")
    @Expose
    private String img;
    @SerializedName("DISTRICTID")
    @Expose
    private Integer district_id;
    @SerializedName("CATEGORYID")
    @Expose
    private Integer category_id;
    @SerializedName("CITYID")
    @Expose
    private Integer city_id;
    @SerializedName("TYPEID")
    @Expose
    private Integer type_id;
    @SerializedName("USERNAME")
    @Expose
    private String username;
    @SerializedName("USERAVATAR")
    @Expose
    private String useravatar;
    @SerializedName("DATECREATED")
    @Expose
    private String datecreated;
    @SerializedName("USERID")
    @Expose
    private Integer user_id;

    public ItemWhat(){

    }

    public ItemWhat(Integer id, String address, String name, String foodname, String img, Integer district_id, Integer category_id, Integer city_id, Integer type_id, String username, String useravatar, String datecreated, Integer user_id) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.foodname = foodname;
        this.img = img;
        this.district_id = district_id;
        this.category_id = category_id;
        this.city_id = city_id;
        this.type_id = type_id;
        this.username = username;
        this.useravatar = useravatar;
        this.datecreated = datecreated;
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
