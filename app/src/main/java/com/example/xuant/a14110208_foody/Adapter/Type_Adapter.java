package com.example.xuant.a14110208_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.Type;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

/**
 * Created by xuant on 18/04/2017.
 */

public class Type_Adapter extends BaseAdapter{

    Context context;
    ArrayList<Type> arrayList;
    LayoutInflater inflater;
    private int pos = -1;
    // Định nghĩa Type_Adapter
    public Type_Adapter(Context context, ArrayList<Type> arrayList) {
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

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtName;
        ImageView img,imgChoose;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_row_type, parent, false);

        txtName = (TextView) view.findViewById(R.id.item_row_type_txt);
        img = (ImageView) view.findViewById(R.id.item_row_type_img);
        imgChoose=(ImageView) view.findViewById(R.id.item_row_type_isselected);
        imgChoose.setVisibility(View.GONE);

        txtName.setText(arrayList.get(position).getName());

        try {       // Lấy ảnh của type từ drawable
            int imageResource = context.getResources().getIdentifier("fd" +arrayList.get(position).getImg(), "drawable", context.getPackageName());
            img.setImageResource(imageResource);
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        // Nếu type được chọn thì đổi màu chữ và hiển thị ảnh được chọn
        if(pos==position) {
            txtName.setTextColor(view.getResources().getColor(R.color.colorFoody));
            imgChoose.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
