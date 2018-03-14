package com.example.xuant.a14110208_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.R;

/**
 * Created by xuant on 18/04/2017.
 */

public class Sex_Adapter extends BaseAdapter{

    Context context;
    private  String[] sexs;
    LayoutInflater inflater;
    private static  int pos = -1;

    // Định nghĩa City_Adapter
    public Sex_Adapter(Context context, String[] sexs) {
        this.context = context;
        this.sexs = sexs;
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
        return sexs.length;
    }
    // Trả về object item

    @Override
    public Object getItem(int position) {
        return sexs[position];
    }
    // Trả về id item
    @Override
    public long getItemId(int position) {
        return 0;
    }
    // Gắn biến vào layout và set giá trị tương ứng với từng item list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView isChoose;
        TextView txtName;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list_dialog, parent, false);

        txtName = (TextView) view.findViewById(R.id.item_list_dialog_name);
        isChoose = (ImageView) view.findViewById(R.id.item_list_dialog_isstick);
        isChoose.setVisibility(View.INVISIBLE);

        txtName.setText(sexs[position]);
        // Khi city hiện tại được chọn thì đổi màu chữ và hiện ảnh được chon
        if(pos==position) {
            txtName.setTextColor(view.getResources().getColor(R.color.colorPrimary));
            isChoose.setVisibility(View.VISIBLE);
        }
        return  view;
    }
}
