package com.example.xuant.a14110208_foody.Database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhere;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ReviewWhere;
import com.example.xuant.a14110208_foody.Service.Service;
import com.example.xuant.a14110208_foody.Service.Utils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuant on 16/05/2017.
 */

public class GetReview extends AsyncTask<ItemWhere, Integer, ArrayList<ReviewWhere>> {

    Service mService;
    ArrayList<ReviewWhere> listArrayList = null;
    ArrayList<ReviewWhere> reviewWheres;
    int temp=0;

//    public interface getReviewsAsync {
//        void getReviews(ArrayList<ReviewWhere> arrayLists);
//    }
//
//    public getReviewsAsync delegate = null;
//
//    public GetReview(getReviewsAsync delegate){
//        this.delegate = delegate;
//    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<ReviewWhere> doInBackground(ItemWhere... params) {
        reviewWheres = new ArrayList<>();
        ItemWhere itemWheres = params[0];
            mService = Utils.getService();
            Call<ArrayList<ReviewWhere>> call = mService.getReview_ByItem(itemWheres.getId());
            Log.e("Response",call.request().url().toString());
            call.enqueue(new Callback<ArrayList<ReviewWhere>>() {
                @Override
                public void onResponse(Call<ArrayList<ReviewWhere>> call, Response<ArrayList<ReviewWhere>> response) {
                    reviewWheres = response.body();
                }

                @Override
                public void onFailure(Call<ArrayList<ReviewWhere>> call, Throwable t) {

                }
            });
        return reviewWheres;
    }

    @Override
    protected void onPostExecute(ArrayList<ReviewWhere> reviewWheres) {
        super.onPostExecute(reviewWheres);
    }
}
