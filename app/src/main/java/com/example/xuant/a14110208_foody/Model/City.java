package com.example.xuant.a14110208_foody.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by xuant on 14/04/2017.
 */

public class City implements Serializable {
    int id;
    String name;
    ArrayList<District> districts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
}
