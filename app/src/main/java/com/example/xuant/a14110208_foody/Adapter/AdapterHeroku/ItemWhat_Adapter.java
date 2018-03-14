package com.example.xuant.a14110208_foody.Adapter.AdapterHeroku;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhat;
import com.example.xuant.a14110208_foody.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xuant on 19/04/2017.
 */

public class ItemWhat_Adapter extends RecyclerView.Adapter<ItemWhat_Adapter.ViewHolder> {


    private Activity activity;
    ArrayList<ItemWhat> arrayList=new ArrayList<>();
    // Định nghĩa Item_What_Adapter
    public ItemWhat_Adapter(Activity activity, ArrayList<ItemWhat> arrayList){
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
    public ItemWhat_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_item_what, parent, false);
        return new ItemWhat_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemWhat_Adapter.ViewHolder holder, int position) {

        String url = "http://192.168.43.219:8000/getimg?nameimg=";
        String urlOnl = "https://foody-trietv2.herokuapp.com/getimg?nameimg=";
        String img = arrayList.get(position).getImg();
        String avatar = arrayList.get(position).getUseravatar();
        if(img.contains("upload_") || avatar.contains("avau"))
        {
            Log.e("","avatar ======= "+urlOnl+arrayList.get(position).getUseravatar()+".png");
            Log.e("","image ======= "+urlOnl+arrayList.get(position).getImg()+".png");
            Picasso.with(activity).load(urlOnl+arrayList.get(position).getImg()+".png").into(holder.imgItem);
            Picasso.with(activity).load(urlOnl+arrayList.get(position).getUseravatar()+".png").into(holder.avatarComment);
        }
        else
        {
            Picasso.with(activity).load(arrayList.get(position).getImg()).into(holder.imgItem);
            Picasso.with(activity).load(arrayList.get(position).getUseravatar()).into(holder.avatarComment);
        }


        holder.txtName.setText(arrayList.get(position).getName());
        holder.txtType.setText(arrayList.get(position).getFoodname());
        holder.txtAddress.setText(arrayList.get(position).getAddress());
        holder.txtName_Comment.setText(arrayList.get(position).getUsername());
        holder.txtDate_Comment.setText(arrayList.get(position).getDatecreated());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void clearData(){
        arrayList.clear();
    }
}
