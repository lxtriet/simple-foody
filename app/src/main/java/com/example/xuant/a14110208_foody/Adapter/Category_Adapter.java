package com.example.xuant.a14110208_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.Category;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

/**
 * Created by xuant on 17/04/2017.
 */

public class Category_Adapter extends BaseAdapter {

    Context context;
    ArrayList<Category> arrayList;
    LayoutInflater inflater;
    private int pos = -1;

    // Định nghĩa Category_Adapter
    public Category_Adapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    // Lấy vị trí hiện tại
    public int getPosition() {
        return pos;
    }
    // Khởi tạo vị trí hiện tại
    public void setPosition(int pos) {
        this.pos = pos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
    // Trả về object item
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }
    // Trả về id item
    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }
    // Gắn biến vào layout và set giá trị tương ứng với từng item list
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView txtName,txtIsNew;
        ImageView img,imgChoose;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_row_category, parent, false);

        txtName = (TextView) view.findViewById(R.id.item_row_category_txt);
        txtIsNew = (TextView) view.findViewById(R.id.item_row_category_isNew);
        img = (ImageView) view.findViewById(R.id.item_row_category_img);
        imgChoose=(ImageView) view.findViewById(R.id.item_row_category_isselected);
        imgChoose.setVisibility(View.GONE);
//        if(arrayList.get(position).getIsNew() == 1)
//            txtIsNew.setVisibility(View.VISIBLE);
//        else
            txtIsNew.setVisibility(View.GONE);

        txtName.setText(arrayList.get(position).getName());
        // Lấy ảnh của category
        try {
            int imageResource = context.getResources().getIdentifier(arrayList.get(position).getImg_unselected(), "drawable", context.getPackageName());
            img.setImageResource(imageResource);
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        // Nếu category được chọn. thì đổi màu chữ mà đổi image của category
        if(pos==position) {
            txtName.setTextColor(view.getResources().getColor(R.color.colorFoody));
            imgChoose.setVisibility(View.VISIBLE);
            int imageResource = context.getResources().getIdentifier(arrayList.get(position).getImg_selected(), "drawable", context.getPackageName());
            img.setImageResource(imageResource);
        }
        return view;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = convertView;
//        if(view == null){
//            viewHolder = new ViewHolderShowListCategory();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.item_row_category,parent,false);
//
//            viewHolder.imgUnselected = (ImageView) view.findViewById(R.id.item_row_category_img);
//            viewHolder.txtName = (TextView) view.findViewById(R.id.item_row_category_txt);
//            viewHolder.ln_one_row = (LinearLayout) view.findViewById(R.id.lnOneCategory);
//
//            view.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolderShowListCategory) view.getTag();
//        }
//        Category category = cateogories.get(position);
//        String img = category.getImg_unselected().toString();
//        if(img == null || img.equals("")){
//            int imageResource = context.getResources().getIdentifier(category.getImg_unselected(), "drawable", context.getPackageName());
//            viewHolder.imgUnselected.setImageResource(imageResource);
//        }else{
//            Uri uri = Uri.parse(img);
//            viewHolder.imgUnselected.setImageURI(uri);
//        }
//
//        viewHolder.txtName.setText(category.getName());
//
//        if(pos == position){
//            viewHolder.txtName.setTextColor(context.getResources().getColor(R.color.colorFoody));
//        }else{
//            viewHolder.txtName.setTextColor(context.getResources().getColor(R.color.colorBlack));
//        }
//
//        return view;
//    }

}
