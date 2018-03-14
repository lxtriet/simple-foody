package com.example.xuant.a14110208_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Model.Street;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

/**
 * Created by xuant on 15/05/2017.
 */

public class Street_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Street> arrayList;
    LayoutInflater inflater;
    private int pos = -1;
    // Định nghĩa Type_Adapter
    public Street_Adapter(Context context, ArrayList<Street> arrayList) {
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
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_row_street, parent, false);

        txtName = (TextView) view.findViewById(R.id.item_row_street_name);

        txtName.setText(arrayList.get(position).getName());

        // Nếu type được chọn thì đổi màu chữ và hiển thị ảnh được chọn
        if(pos==position) {
            txtName.setTextColor(view.getResources().getColor(R.color.colorFoody));
        }
        return view;
    }
}
