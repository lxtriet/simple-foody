package com.example.xuant.a14110208_foody.Model;

import java.io.Serializable;

/**
 * Created by xuant on 14/04/2017.
 */

public class Category implements Serializable {
     Integer id,isNew;
     String name, img_selected, img_unselected;
     boolean isSeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_selected() {
        return img_selected;
    }

    public void setImg_selected(String img_selected) {
        this.img_selected = img_selected;
    }

    public String getImg_unselected() {
        return img_unselected;
    }

    public void setImg_unselected(String img_unselected) {
        this.img_unselected = img_unselected;
    }

    public boolean isSeleted() {
        return isSeleted;
    }

    public void setSeleted(boolean seleted) {
        isSeleted = seleted;
    }

    public Category(Integer id, Integer isNew, String name, String img_selected, String img_unselected, boolean isSeleted) {

        this.id = id;
        this.isNew = isNew;
        this.name = name;
        this.img_selected = img_selected;
        this.img_unselected = img_unselected;
        this.isSeleted = isSeleted;
    }

    public Category() {

    }




}
