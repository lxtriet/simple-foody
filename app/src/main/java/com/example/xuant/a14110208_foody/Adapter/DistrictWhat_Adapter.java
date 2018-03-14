package com.example.xuant.a14110208_foody.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuant.a14110208_foody.Database.Database;
import com.example.xuant.a14110208_foody.Model.District;
import com.example.xuant.a14110208_foody.Model.Street;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.View.Fragment_Where;

import java.util.ArrayList;

/**
 * Created by xuant on 18/04/2017.
 */

public class DistrictWhat_Adapter extends BaseAdapter{

    String [] notthing={"Không có đường nào"};
    Context context;
    LayoutInflater inflater;
    ArrayList<District> arrayList;
    private int pos=-1;

    // Định nghĩa City_Adapter

    public DistrictWhat_Adapter(Context context, ArrayList<District> arrayList) {
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

    public class ViewHolder
    {
        TextView txtName;
        Button btnStreet;
        ListView listStreet;
    }
        // Hàm hiện thì list đường khi click vào đường của mỗi district
    public static void showListStreet(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) return;
        ViewGroup viewGroup = listView;
        int height = 0;
        for(int i=0; i< listAdapter.getCount();i++){
            View view = listAdapter.getView(i,null,viewGroup);
            view.measure(0,0);
            height += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (listAdapter.getCount()-1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }
    // Gắn biến vào layout và set giá trị tương ứng với từng item list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtName,txtStreet;
        LinearLayout btnStreet;
        final ListView listStreet;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_row_address, parent, false);

        txtName = (TextView) view.findViewById(R.id.item_row_address_name);
        txtStreet =(TextView) view.findViewById(R.id.item_row_address_txt_street);
        btnStreet =(LinearLayout) view.findViewById(R.id.item_row_address_btn_street);
        listStreet =(ListView) view.findViewById(R.id.item_row_address_liststreet);

        txtName.setText(arrayList.get(position).getName());


        Database database = new Database(context);
        database.openDataBase();
        ArrayList<Street> rv = new ArrayList<>();

        rv = database.getList_Street_ByDistrict(arrayList.get(position).getId());
        txtStreet.setText(rv.size()+" đường");

        final Street_Adapter street_adapter = new Street_Adapter(context,rv);

        listStreet.setAdapter(street_adapter);
        listStreet.setVisibility(View.GONE);
        listStreet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Street st = (Street) parent.getItemAtPosition(position);
                Street_Adapter streetAdapter = (Street_Adapter)parent.getAdapter();
                streetAdapter.setPosition(position);
                Fragment_Where.street_id = st.getId();
                Toast.makeText(context, st.getName(), Toast.LENGTH_LONG).show();
                Fragment_Where.district_adapter.setPosition(-1);
                Fragment_Where.district_id = -1;
                Fragment_Where.tv_Address.setText(st.getName());
                Fragment_Where.tv_Address.setTextColor(context.getResources().getColor(R.color.colorFoody));
                Fragment_Where.dbItemWhere.getList_ItemWhere(Fragment_Where.category_id,Fragment_Where.type_id
                        ,Fragment_Where.district_id
                        ,Fragment_Where.city_id,
                        Fragment_Where.street_id);
                Fragment_Where.hideList();
            }
        });
        showListStreet(listStreet);
        if(listStreet.isShown())
            listStreet.setVisibility(View.VISIBLE);
        // Gắn sự kiện click vào btn đường khi click vào. Hiện ra danh sách các đường
        btnStreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listStreet.setVisibility(listStreet.isShown() ? View.GONE : View.VISIBLE);
            }
        });
        // Đổi màu district khi được chọn
        if(pos==position)
            txtName.setTextColor(view.getResources().getColor(R.color.colorFoody));
        return view;
    }
}
