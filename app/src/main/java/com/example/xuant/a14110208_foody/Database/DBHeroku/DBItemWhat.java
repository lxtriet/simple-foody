package com.example.xuant.a14110208_foody.Database.DBHeroku;

import android.util.Log;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhat;
import com.example.xuant.a14110208_foody.Service.Service;
import com.example.xuant.a14110208_foody.Service.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuant on 14/05/2017.
 */

public class DBItemWhat {
    Service mService;

    ItemWhatListener itemWhatListener;

    public DBItemWhat(ItemWhatListener itemWhatListener ) {
        this.itemWhatListener = itemWhatListener;
    }
    // Hàm lấy list item what
    public void getList_ItemWhat(int category_id, int type_id, int district_id, int city_id){
        mService = Utils.getService();
        Call<ArrayList<ItemWhat>> call = mService.getItemWhat(category_id,type_id,district_id,city_id);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<ItemWhat>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemWhat>> call, Response<ArrayList<ItemWhat>> response) {
                if(response.isSuccessful()) {
                    itemWhatListener.getItemWhat(response.body());
                    Log.e("Response","lay duoc item what: "+response.body().size());
                }
                else
//                    Log.e("Response",response.message());
                    Log.e("Response","khong lay duoc item what ");
            }
            @Override
            public void onFailure(Call<ArrayList<ItemWhat>> call, Throwable t) {
                Log.e("Response","khong lay duoc item what ");
            }
        });
    }
    // Hàm thêm item what
    public void insertItemWhat(String address,String name,String foodname,String img,int district_id,
                               int type_id,String username,String useravatar,int city_id,int user_id){
        mService = Utils.getService();
        Call<ArrayList<ItemWhat>> call = mService.insertItemWhat(address,name,foodname,img,district_id,
                type_id,username,useravatar,city_id,user_id);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<ItemWhat>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemWhat>> call, Response<ArrayList<ItemWhat>> response) {
                if(response.isSuccessful()) {
                    itemWhatListener.getItemWhat(response.body());
                    Log.e("Response","lay duoc item what: "+response.body().size());
                }
                else
//                    Log.e("Response",response.message());
                    Log.e("Response","khong lay duoc item what ");
            }
            @Override
            public void onFailure(Call<ArrayList<ItemWhat>> call, Throwable t) {
                Log.e("Response",t.getMessage());
                Log.e("Response","khong lay duoc item what ");
            }
        });
    }
}
