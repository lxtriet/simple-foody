package com.example.xuant.a14110208_foody.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.xuant.a14110208_foody.Model.Item;
import com.example.xuant.a14110208_foody.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by xuant on 18/04/2017.
 */

public class Item_Where_Adapter extends RecyclerView.Adapter<Item_Where_Adapter.ViewHolder>{

    private Activity activity;
    ArrayList<Item> arrayList=new ArrayList<>();
    File destFile;
    // Định nghĩa Item_What_Adapter
    public Item_Where_Adapter(Activity activity, ArrayList<Item> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
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
    public Item_Where_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_item_where, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Item_Where_Adapter.ViewHolder holder, int position) {
        try {  // Lấy ảnh từ drawable
            int imageResource = activity.getResources().getIdentifier("fdi" + arrayList.get(position).getImg(), "drawable", activity.getPackageName());
            if(imageResource != 0){
                Picasso.with(activity).load(imageResource).fit().centerInside().into(holder.imgItem);
            }else{
                Picasso.with(activity).load(R.drawable.fdi1).fit().centerInside().into(holder.imgItem);
            }
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }

        // Lấy dữ liệu item
        holder.txtName.setText(arrayList.get(position).getName());
        holder.txtAvgRating.setText(String.format("%.1f",arrayList.get(position).getAvgRating())+"");
        holder.txtAddress.setText(arrayList.get(position).getAddress());
        holder.btnCountComment.setText(String.valueOf(arrayList.get(position).getTotal_Reviews()));
        holder.btnCountPicture.setText(String.valueOf(arrayList.get(position).getTotal_pictures()));

        // Lấy dữ liệu reviews
//        Database database = new Database(activity);
//        database.openDataBase();
//        Review_Adapter review_adapter = new Review_Adapter(activity,arrayList.get(position).getReviews());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
//        holder.recyclerReviews.setLayoutManager(layoutManager);
//        holder.recyclerReviews.setAdapter(review_adapter);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void clearData(){
        arrayList.clear();
    }

    public static Bitmap resizeBitMapImage1(String filePath, int targetWidth,
                                            int targetHeight) {
        Bitmap bitMapImage = null;
        // First, get the dimensions of the image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        double sampleSize = 0;
        // Only scale if we need to
        // (16384 buffer for img processing)
        Boolean scaleByHeight = Math.abs(options.outHeight - targetHeight) >= Math
                .abs(options.outWidth - targetWidth);

        if (options.outHeight * options.outWidth * 2 >= 1638) {
            // Load, scaling to smallest power of 2 that'll get it <= desired
            // dimensions
            sampleSize = scaleByHeight ? options.outHeight / targetHeight
                    : options.outWidth / targetWidth;
            sampleSize = (int) Math.pow(2d,
                    Math.floor(Math.log(sampleSize) / Math.log(2d)));
        }

        // Do the actual decoding
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[128];
        while (true) {
            try {
                options.inSampleSize = (int) sampleSize;
                bitMapImage = BitmapFactory.decodeFile(filePath, options);

                break;
            } catch (Exception ex) {
                try {
                    sampleSize = sampleSize * 2;
                } catch (Exception ex1) {

                }
            }
        }

        return bitMapImage;
    }
}
