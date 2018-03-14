package com.example.xuant.a14110208_foody.Database.DBHeroku;

import android.util.Log;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.Service.Service;
import com.example.xuant.a14110208_foody.Service.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuant on 16/05/2017.
 */

public class DBUser {
    Service mService;

    UserListener userListener;

    public DBUser(UserListener userListener ) {

        this.userListener = userListener;
    }
    // Hàm lấy user theo mail
    public void getUser(String mail){
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.getUser(mail);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userListener.getUser(response.body());
                Log.e("","Lấy user thành công");
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("","Lấy user thất bại "+t.getMessage());
            }
        });
    }
    // Hàm thêm tài khoản mới
    public void insertUser(String mail,String username, String pass){
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.insertUser(mail,username,pass);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userListener.getUser(response.body());
                Log.e("","Lấy user thành công");
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("","Lấy user thất bại "+t.getMessage());
            }
        });
    }
    // Hàm lấy list user
    public void getListUser(){
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.getListUser();
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userListener.getUser(response.body());
                Log.e("","Lấy list user thành công");
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("","Lấy list user thất bại "+t.getMessage());
            }
        });
    }
    // Hàm update user
    public void updateUser(String username, String name,
                          String lastname,String status,
                            String sex, String birthday,
                            String mail) {
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.updateUser(username, name, lastname, status, sex, birthday, mail);
        Log.e("Response", call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                // userListener.getUser(response.body());
                Log.e("", "Update user thành công");
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("", "Update user thất bại " + t.getMessage());
            }
        });
    }
    // Hàm update image user
    public void updateImgUser(String img,
                           String mail){
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.updateImgUser(img,mail);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                // userListener.getUser(response.body());
                Log.e("","Update user thành công");
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("","Update user thất bại "+t.getMessage());
            }
        });
    }

    // Hàm update pass user
    public void updatePassUser(String pass,
                              String mail){
        mService = Utils.getService();
        Call<ArrayList<User>> call = mService.updatePassUser(pass,mail);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                // userListener.getUser(response.body());
                Log.e("","Update user thành công");
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("","Update user thất bại "+t.getMessage());
            }
        });
    }
}
