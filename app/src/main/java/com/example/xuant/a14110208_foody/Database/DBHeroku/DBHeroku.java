package com.example.xuant.a14110208_foody.Database.DBHeroku;

import android.util.Log;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhat;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhere;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ReviewWhere;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.Service.Service;
import com.example.xuant.a14110208_foody.Service.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuant on 17/05/2017.
 */

public class DBHeroku {

    Service mService;

    DBReviewWhere dbReviewWhere;
    ArrayList<ArrayList<ReviewWhere>> reviewWheres;
    ArrayList<ItemWhere> itemWheress;

    ItemWhereListener itemWhereListener;
    ItemWhatListener itemWhatListener;
    UserListener userListener;

    public void getList_ItemWhere(int category_id, int type_id, int district_id, int city_id,int street_id){
        mService = Utils.getService();
        Call<ArrayList<ItemWhere>> call = mService.getItemWhere(category_id,type_id,district_id,city_id,street_id);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<ItemWhere>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemWhere>> call, Response<ArrayList<ItemWhere>> response) {
                if(response.isSuccessful()) {
                        itemWhereListener.getItemWhere(response.body());
                    Log.e("Response","lay duoc "+response.body().size());
                }
                else
//                    Log.e("Response",response.message());
                    Log.e("Response","khong lay duoc");
            }
            @Override
            public void onFailure(Call<ArrayList<ItemWhere>> call, Throwable t) {
                Log.e("Response",t.getMessage());
            }
        });
    }

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
                Log.e("Response",t.getMessage());
                Log.e("Response","khong lay duoc item what ");
            }
        });
    }

    public void getUser(String mail){
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.getUser(mail);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userListener.getUser(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }

}
