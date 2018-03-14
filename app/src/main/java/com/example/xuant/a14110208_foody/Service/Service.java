package com.example.xuant.a14110208_foody.Service;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhat;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhere;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ReviewWhere;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by xuant on 13/05/2017.
 */

public interface Service { //Định nghĩa các REST API (Api Services) cho Retrofit
   // Hàm  lấy item where
    @GET("/getitemwhere")
    Call<ArrayList<ItemWhere>> getItemWhere(@Query("categoryid") int categoryID, @Query("typeid") int typeID, @Query("districtid") int districtID, @Query("cityid") int cityid,@Query("streetid") int streetid);
    // Hàm  lấy review theo itemid
    @GET("/getreview")
    Call<ArrayList<ReviewWhere>> getReview_ByItem(@Query("itemid") int itemid);
    // Hàm  lấy list user
    @GET("/getlistuser")
    Call<ArrayList<User>> getListUser();
    // Hàm  lấy user theo mail
    @GET("/getuser")
    Call<ArrayList<User>> getUser(@Query("mail") String mail);
    // Hàm  lấy item what
    @FormUrlEncoded
    @POST("/insertitemwhat")
    Call<ArrayList<ItemWhat>> insertItemWhat(@Field("address") String address,@Field("name") String name,
                                             @Field("foodname") String foodname,@Field("img") String img,
                                             @Field("districtid") int districtid,@Field("typeid") int typeid,
                                             @Field("username") String username,@Field("useravatar") String useravatar,
                                             @Field("cityid") int cityid,@Field("userid") int userid);
    // Cập nhật lại ảnh user
    @FormUrlEncoded
    @POST("/updatetimguser")
    Call<ArrayList<User>> updateImgUser(@Field("img") String img,
                                     @Field("mail") String mail);
   // Thay đổi pass user
    @FormUrlEncoded
    @POST("/updatetpassuser")
    Call<ArrayList<User>> updatePassUser(@Field("pass") String pass,
                                        @Field("mail") String mail);


    // Hàm  update thông tin user
    @FormUrlEncoded
    @POST("/updatetuser")
    Call<ArrayList<User>> updateUser(@Field("username") String username,@Field("name") String name,
                                     @Field("lastname") String lastname,@Field("status") String status,
                                     @Field("sex") String sex,@Field("birthday") String birthday,
                                     @Field("mail") String mail);
    // thêm user mới
    @FormUrlEncoded
    @POST("/insertuser")
    Call<ArrayList<User>> insertUser(@Field("mail") String mail,@Field("username") String username,
                                     @Field("pass") String pass);
    // Hàm  lấy item what
    @GET("/getitemwhat")
    Call<ArrayList<ItemWhat>> getItemWhat(@Query("categoryid") int categoryID, @Query("typeid") int typeID, @Query("districtid") int districtID, @Query("cityid") int cityid);

}
