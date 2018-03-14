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

public class Status_Adapter extends BaseAdapter{

    Context context;
    private  String[] status;
    LayoutInflater inflater;
    private static  int pos = -1;

    // Định nghĩa City_Adapter
    public Status_Adapter(Context context, String[] status) {
        this.context = context;
        this.status = status;
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
        return status.length;
    }
    // Trả về object item

    @Override
    public Object getItem(int position) {
        return status[position];
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

        txtName.setText(status[position]);
        // Khi city hiện tại được chọn thì đổi màu chữ và hiện ảnh được chon
        if(pos==position) {
            txtName.setTextColor(view.getResources().getColor(R.color.colorPrimary));
            isChoose.setVisibility(View.VISIBLE);
        }
        return  view;
    }
}
