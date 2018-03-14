package com.example.xuant.a14110208_foody.Model.ModelHeroku;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xuant on 16/05/2017.
 */

public class User {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("pass")
    @Expose
    private String password;
    @SerializedName("dateparticipation")
    @Expose
    private String datejoin;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("status")
    @Expose
    private String status;

    public User()
    {}

    public User(Integer id, String name, String lastname, String mail, String username, String password, String datejoin, String sex, String birthday, String img, String status) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.datejoin = datejoin;
        this.sex = sex;
        this.birthday = birthday;
        this.img = img;
        this.status = status;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatejoin() {
        return datejoin;
    }

    public void setDatejoin(String datejoin) {
        this.datejoin = datejoin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
