package com.example.xuant.a14110208_foody.Database.DBHeroku;

import android.util.Log;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ReviewWhere;
import com.example.xuant.a14110208_foody.Service.Service;
import com.example.xuant.a14110208_foody.Service.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuant on 14/05/2017.
 */

public class DBReviewWhere {
    Service mService;

    ReviewWhereListener reviewWhereListener;

    public DBReviewWhere(ReviewWhereListener reviewWhereListener ) {
        this.reviewWhereListener = reviewWhereListener;
    }
    // Hàm lấy review theo item id
    public void getList_ReviewWhere(int item_id){
        mService = Utils.getService();
        Call<ArrayList<ReviewWhere>> call = mService.getReview_ByItem(item_id);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<ReviewWhere>>() {
            @Override
            public void onResponse(Call<ArrayList<ReviewWhere>> call, Response<ArrayList<ReviewWhere>> response) {
                reviewWhereListener.getReviewWhere(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ReviewWhere>> call, Throwable t) {

            }
        });
    }
}
