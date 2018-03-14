package com.example.xuant.a14110208_foody.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.Review;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xuant on 19/04/2017.
 */

public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.ViewHolder> {

    private Activity activity;
    ArrayList<Review> arrayList=new ArrayList<>();
    // Định nghĩa Review_Adapter
    public Review_Adapter(Activity activity, ArrayList<Review> arrayList){
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
    public Review_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_review, parent, false);
        return new Review_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Review_Adapter.ViewHolder holder, int position) {
        try {       // Lấy ảnh avatar từ drawable
            int imageResource = activity.getResources().getIdentifier("ava" + arrayList.get(position).getAvatar(), "drawable", activity.getPackageName());
            holder.reviewAvatar.setImageResource(imageResource);
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }

       // int imageResource = activity.getResources().getIdentifier("ava"+arrayList.get(position).getAvatar(), "drawable", activity.getPackageName());
//        if(imageResource != 0){
//            Picasso.with(activity).load(imageResource).fit().centerInside().into(holder.reviewAvatar);
//        }else{
//            Picasso.with(activity).load(R.drawable.ava3).fit().centerInside().into(holder.reviewAvatar);
//        }
        // Lấy dữ liệu reviews
        holder.txtName.setText(arrayList.get(position).getName());
        holder.txtAvgRating.setText(String.format("%.1f",arrayList.get(position).getRating())+"");
        holder.txtCommentTrim.setText(arrayList.get(position).getComment_trim());
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
