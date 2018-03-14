package com.example.xuant.a14110208_foody.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.Item;
import com.example.xuant.a14110208_foody.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xuant on 19/04/2017.
 */

public class Item_What_Adapter extends RecyclerView.Adapter<Item_What_Adapter.ViewHolder> {


    private Activity activity;
    ArrayList<Item> arrayList=new ArrayList<>();
    // Định nghĩa Item_What_Adapter
    public Item_What_Adapter(Activity activity, ArrayList<Item> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
    }
    // Tạo ViewHolder chứa biến
    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtType, txtName, txtAddress, txtName_Comment, txtDate_Comment;
        ImageView imgItem;
        CircleImageView avatarComment;

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.item_what_name);
            txtType = (TextView) v.findViewById(R.id.item_what_type);
            txtAddress = (TextView) v.findViewById(R.id.item_what_adddress);
            txtName_Comment = (TextView) v.findViewById(R.id.item_what_name_comment);
            txtDate_Comment = (TextView) v.findViewById(R.id.item_what_date_comment);
            imgItem = (ImageView) v.findViewById(R.id.item_what_img);
            avatarComment = (CircleImageView) v.findViewById(R.id.item_what_avatar);
        }
    }

    @Override
    public Item_What_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_item_what, parent, false);
        return new Item_What_Adapter.ViewHolder(view);
    }

    public Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("drawable/" + filename + ".png")));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    @Override
    public void onBindViewHolder(Item_What_Adapter.ViewHolder holder, int position) {
        try {   // Lấy ảnh item và avatar comment từ drawable
            int imageResource = activity.getResources().getIdentifier("fdi" + arrayList.get(position).getImg(), "drawable", activity.getPackageName());
            if(imageResource != 0){
                Picasso.with(activity).load(imageResource).fit().centerInside().into(holder.imgItem);
            }else{
                Picasso.with(activity).load(R.drawable.fdi1).fit().centerInside().into(holder.imgItem);
            }
            if(!arrayList.get(position).getReviews().isEmpty())
            {
                imageResource = activity.getResources().getIdentifier("ava" + arrayList.get(position).getReviews().get(0).getAvatar(), "drawable", activity.getPackageName());
                if(imageResource != 0){
                    Picasso.with(activity).load(imageResource).fit().centerInside().into(holder.avatarComment);
                }else{
                    Picasso.with(activity).load(R.drawable.fdi1).fit().centerInside().into(holder.avatarComment);
                }
            }
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
//        try {
//            AssetManager assets = context.getResources().getAssets();
//            InputStream buffer = new BufferedInputStream((assets.open("drawable/" + "fdi" + arrayList.get(position).getImg().toString() + ".png")));
//            Bitmap bitmap = BitmapFactory.decodeStream(buffer);
//
//            int imageResource = activity.getResources().getIdentifier("fdi" + arrayList.get(position).getImg(), "drawable", activity.getPackageName());
//            Drawable image = getAssetImage(context,);
//            holder.imgItem.setImageDrawable(image);
//
//            imageResource = activity.getResources().getIdentifier("ava" + arrayList.get(position).getReviews().get(0).getAvatar(), "drawable", activity.getPackageName());
//            image = activity.getResources().getDrawable(imageResource);
//            holder.avatarComment.setImageDrawable(image);
//        }catch (OutOfMemoryError e){
//            e.printStackTrace();
//        }

//        Database database = new Database(activity);
//        database.openDataBase();
//        Type type = database.getType_ByItem(arrayList.get(position).getId());
//
//        holder.txtName.setText(arrayList.get(position).getName());
//        holder.txtType.setText(type.getName());
//        holder.txtAddress.setText(arrayList.get(position).getAddress());
//        if(!arrayList.get(position).getReviews().isEmpty())
//            holder.txtName_Comment.setText(arrayList.get(position).getReviews().get(0).getName());
//        else
//            holder.txtName_Comment.setText("Ronaldo");
//        holder.txtDate_Comment.setText("19/4/2017");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void clearData(){
        arrayList.clear();
    }
}
