package com.example.xuant.a14110208_foody.Adapter.AdapterHeroku;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ReviewWhere;
import com.example.xuant.a14110208_foody.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xuant on 19/04/2017.
 */

public class ReviewWhere_Adapter extends RecyclerView.Adapter<ReviewWhere_Adapter.ViewHolder>  {

    private Activity activity;
    List<ReviewWhere> arrayList;

    // Định nghĩa Review_Adapter
    public ReviewWhere_Adapter(Activity activity, List<ReviewWhere> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;

    }

    // Tạo ViewHolder chứa biến của view
    protected class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView reviewAvatar;
        TextView txtName,txtAvgRating, txtCommentTrim;


        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.review_name);
            txtAvgRating = (TextView) v.findViewById(R.id.review_rating);
            txtCommentTrim = (TextView) v.findViewById(R.id.review_commenttrim);
            reviewAvatar = (CircleImageView) v.findViewById(R.id.review_avatar);
        }
    }
    @Override
    public ReviewWhere_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_review, parent, false);
        return new ReviewWhere_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewWhere_Adapter.ViewHolder holder, int position) {
        arrayList=new ArrayList<>();
        String avatar="https://foody-trietv2.herokuapp.com/getimg?nameimg=ava";
        if(arrayList.isEmpty())
            Log.e("Response","Rong~ danh sach");
        else
            Log.e("Response","so phan tu :" +arrayList.size());

        if(position>0){
            Picasso.with(activity).load(avatar+arrayList.get(position).getAvatar()+".png")//download URL
                    .placeholder(R.drawable.ava0)//use defaul image
                    .error(R.drawable.ava0)//if failed
                    .into(holder.reviewAvatar);

            // Lấy dữ liệu reviews
            Log.e("Response","Ảnh review : ---- "+arrayList.get(position).getAvatar());
            holder.txtName.setText(arrayList.get(position).getName());
            holder.txtAvgRating.setText(String.format("%.1f",arrayList.get(position).getRating())+"");
            holder.txtCommentTrim.setText(arrayList.get(position).getCommenttrim());
        }

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void clearData(){
        arrayList.clear();
    }
}
