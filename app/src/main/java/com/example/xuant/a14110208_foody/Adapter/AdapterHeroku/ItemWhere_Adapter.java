package com.example.xuant.a14110208_foody.Adapter.AdapterHeroku;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.xuant.a14110208_foody.Adapter.Review_Adapter;
import com.example.xuant.a14110208_foody.Database.DBHeroku.DBReviewWhere;
import com.example.xuant.a14110208_foody.Database.DBHeroku.ReviewWhereListener;
import com.example.xuant.a14110208_foody.Database.Database;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhere;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ReviewWhere;
import com.example.xuant.a14110208_foody.Model.Review;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.Service.Service;
import com.example.xuant.a14110208_foody.Service.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuant on 18/04/2017.
 */

public class ItemWhere_Adapter extends RecyclerView.Adapter<ItemWhere_Adapter.ViewHolder> implements ReviewWhereListener {

    private Activity activity;
    ArrayList<ItemWhere> arrayList=new ArrayList<>();
    File destFile;
    DBReviewWhere dbReviewWhere;
    ArrayList<ReviewWhere> reviews;

    // Định nghĩa Item_What_Adapter
    public ItemWhere_Adapter(Activity activity, ArrayList<ItemWhere> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
        reviews = new ArrayList<>();
    }

    @Override
    public void getReviewWhere(ArrayList<ReviewWhere> reviewWheres) {
//        reviews = reviewWheres;
//        Log.e("aa", reviews.size()+" "+reviewWheres.size());
//        reviewWhere_adapter = new ReviewWhere_Adapter(activity,reviews);
//        reviewWhere_adapter.notifyDataSetChanged();
    }

    // Tạo viewholder chứa biến
    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtAvgRating, txtName, txtAddress, txtState;
        ImageView imgItem;
        VideoView videoItem;
         RecyclerView recyclerImg, recyclerReviews;
        Button btnCountComment, btnCountPicture, btnOrder;

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.item_where_name);
            txtAvgRating = (TextView) v.findViewById(R.id.item_where_rating);
            txtAddress = (TextView) v.findViewById(R.id.item_where_address);
            txtState = (TextView) v.findViewById(R.id.item_where_state);
            imgItem = (ImageView) v.findViewById(R.id.item_where_img);
            videoItem = (VideoView) v.findViewById(R.id.item_where_video);
            recyclerImg = (RecyclerView) v.findViewById(R.id.item_where_recycler_img);
            recyclerReviews = (RecyclerView) v.findViewById(R.id.item_where_recycler_reviews);
            btnCountComment = (Button) v.findViewById(R.id.item_where_btn_count_comment);
            btnCountPicture = (Button) v.findViewById(R.id.item_where_btn_count_picture);
            btnOrder = (Button) v.findViewById(R.id.item_where_btn_order);
        }
    }

    @Override
    public ItemWhere_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_item_where, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemWhere_Adapter.ViewHolder holder, int position) {

        String url_imgitem="https://foody-trietv2.herokuapp.com/getimg?nameimg=fdi";
        Picasso.with(activity).load(url_imgitem+arrayList.get(position).getImg()+".png")
                .placeholder(R.drawable.fdi1)
                .error(R.drawable.fdi1)
                .into(holder.imgItem);

        // Lấy dữ liệu item
        holder.txtName.setText(arrayList.get(position).getName());
        holder.txtAvgRating.setText(String.format("%.1f",arrayList.get(position).getAvgRating())+"");
        holder.txtAddress.setText(arrayList.get(position).getAddress());
        holder.btnCountComment.setText(String.valueOf(arrayList.get(position).getTotal_Reviews()));
        holder.btnCountPicture.setText(String.valueOf(arrayList.get(position).getTotal_pictures()));

        // Lấy dữ liệu reviews
//        Log.e("Lấy được ","review: "+reviews.size());
//        dbReviewWhere.getList_ReviewWhere(arrayList.get(position).getId()); // lay dc no chuyen len getData
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
//        holder.recyclerReviews.setLayoutManager(layoutManager);
//        holder.recyclerReviews.setAdapter(reviewWhere_adapter);
//        ReviewWhere_Adapter reviewWhere_adapter = new ReviewWhere_Adapter(activity,arrayList.get(position).getReviewWheres());
//        reviewWhere_adapter.notifyDataSetChanged();
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
//        holder.recyclerReviews.setLayoutManager(layoutManager);
//        holder.recyclerReviews.setAdapter(reviewWhere_adapter);

        // Lấy dữ liệu reviews offline
        Database database = new Database(activity);
        database.openDataBase();
        ArrayList<Review> rv = new ArrayList<>();
        rv = database.getList_Reviews(arrayList.get(position).getId());
        Review_Adapter review_adapter = new Review_Adapter(activity,rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
        holder.recyclerReviews.setLayoutManager(layoutManager);
        holder.recyclerReviews.setAdapter(review_adapter);
    }

    public void getList_ReviewWhere(int item_id,final ItemWhere_Adapter.ViewHolder holder){
        Service mService;
        mService = Utils.getService();
        Call<ArrayList<ReviewWhere>> call = mService.getReview_ByItem(item_id);
        Log.e("Response",call.request().url().toString());
        call.enqueue(new Callback<ArrayList<ReviewWhere>>() {
            @Override
            public void onResponse(Call<ArrayList<ReviewWhere>> call, Response<ArrayList<ReviewWhere>> response) {
                reviews = response.body();

                ReviewWhere_Adapter review_adapter = new ReviewWhere_Adapter(activity,reviews);
                Log.e("Response","Lấy review ok : ---- "+reviews.size());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
                holder.recyclerReviews.setLayoutManager(layoutManager);
                holder.recyclerReviews.setAdapter(review_adapter);
                review_adapter.clearData();
               // reviewWhere_adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<ArrayList<ReviewWhere>> call, Throwable t) {
                Log.e("Response","Lấy review fail");
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void clearData(){
        arrayList.clear();
    }

}
