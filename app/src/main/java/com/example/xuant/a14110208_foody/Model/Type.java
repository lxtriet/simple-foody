package com.example.xuant.a14110208_foody.Model;

import java.io.Serializable;

/**
 * Created by xuant on 14/04/2017.
 */

public class Type implements Serializable {
     int id, category_id;
     String name, img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


}
