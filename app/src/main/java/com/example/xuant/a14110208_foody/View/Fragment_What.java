package com.example.xuant.a14110208_foody.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.Adapter.AdapterHeroku.ItemWhat_Adapter;
import com.example.xuant.a14110208_foody.Adapter.Category_Adapter;
import com.example.xuant.a14110208_foody.Adapter.DistrictWhat_Adapter;
import com.example.xuant.a14110208_foody.Adapter.Item_What_Adapter;
import com.example.xuant.a14110208_foody.Adapter.Type_Adapter;
import com.example.xuant.a14110208_foody.Database.DBHeroku.DBItemWhat;
import com.example.xuant.a14110208_foody.Database.DBHeroku.ItemWhatListener;
import com.example.xuant.a14110208_foody.Database.Database;
import com.example.xuant.a14110208_foody.MainActivity;
import com.example.xuant.a14110208_foody.Model.Category;
import com.example.xuant.a14110208_foody.Model.City;
import com.example.xuant.a14110208_foody.Model.District;
import com.example.xuant.a14110208_foody.Model.Item;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhat;
import com.example.xuant.a14110208_foody.Model.Type;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by xuant on 03/04/2017.
 */

public class Fragment_What extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener,ItemWhatListener {

    TabHost tabHost;
    TabWidget tabWidget;
    Button cancel_New, cancel_Category, cancel_Address, btnChangeCity;
    ListView rv_New, rv_Category, rv_Address;
    public static RecyclerView list_content;
    LinearLayout lnChangeCity;
    TextView CurrentCity, Category_Main;
    ImageView Choose_CategoryMain;
    TextView tv_Category, tv_Type, tv_Address;
    public static boolean isListOpen = false;
    public static int category_id=1, type_id=-1, district_id=-1,district_pos=-1;// biến giữ id hiện tại của category, type, district và city
    public static int city_id=1;


    private ArrayList<Category> categories;// list category
    private ArrayList<Type> types; // list type
    private ArrayList<District> districts;// list district
    private ArrayList<Item> items;// list item
    private City city;

    private Category_Adapter category_adapter;
    private Type_Adapter type_adapter;
    private DistrictWhat_Adapter district_adapter;
    private Item_What_Adapter item_what_adapter;
    public static ItemWhat_Adapter itemWhat_adapter;
    private Database database;
    DBItemWhat dbItemWhat;
    int CurrentTab = -1;// Biến vị trí của tab hiện tại
    private int CHOOSECITY_REQ = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_what, container, false);
        tabHost = (TabHost) view.findViewById(R.id.tabhost);// Tab host new, type và address
        list_content = (RecyclerView)view.findViewById(R.id.list_content);// RecyclerView nội dung chính chứa item

        database = new Database(getContext()); // tạo database
        database.openDataBase();// mở database
        dbItemWhat = new DBItemWhat(this);

        list_content.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2); // tạo kiểu layoutt cho list nội dung. nội dung bên what là gird view vào có 2 cột
        list_content.setLayoutManager(layoutManager); // set kiểu layout cho list item
        list_content.setNestedScrollingEnabled(false);


        cancel_New = (Button)view.findViewById(R.id.btn_cancel_New);          // button hủy của list new
        cancel_Category = (Button)view.findViewById(R.id.btn_cancel_Category);// button hủy của list type
        cancel_Address = (Button)view.findViewById(R.id.btn_cancel_Address);// button hủy của list address

        rv_New = (ListView) view.findViewById(R.id.recycler_New);        // list new
        rv_Category = (ListView)view.findViewById(R.id.recycler_Category);   // list type
        rv_Address = (ListView)view.findViewById(R.id.recycler_Address);   // list address

        lnChangeCity = (LinearLayout)view.findViewById(R.id.btn_ChangeCity);    // button chuyển sang activity changecity
        CurrentCity = (TextView)view.findViewById(R.id.CurrentCity);       // thành phố hiện tại
        Category_Main = (TextView)view.findViewById(R.id.txt_Category_Main);        // Danh mục chính
        Choose_CategoryMain = (ImageView) view.findViewById(R.id.img_ChooseCategory_Main);

        tabHostSetup();      // Khởi tạo tabhost chính
        setDataToList();   // Khởi tạo dữ liệu cho 3 list new, type và address
        //setItemToContent();       // Khởi tạo dữ liệu cho list item



        // Tạo sự kiện click cho các button hủy
        cancel_New.setOnClickListener(this);
        cancel_Category.setOnClickListener(this);
        cancel_Address.setOnClickListener(this);
        lnChangeCity.setOnClickListener(this);
        // Tạo sự kiện click item cho các list
        rv_New.setOnItemClickListener(this);
        rv_Category.setOnItemClickListener(this);
        rv_Address.setOnItemClickListener(this);


        // gắn sự kiện click item cho tabwidget
        tabWidget.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(0);
            }
        });
        tabWidget.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(1);
            }
        });
        tabWidget.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTab(2);
            }
        });

        // Tạo sự kiện khi click vào thành phố hiện tại. Đổ dữ liệu item theo thành phố hiện tại và ẩn list address đi. Set chữ và đổi màu cho address hiện tại
        CurrentCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                district_id = -1;
                district_pos = -1;
                tv_Address.setText(city.getName());
                district_adapter.setPosition(-1);
                rv_Address.setAdapter(district_adapter);
                CurrentCity.setTextColor(getResources().getColor(R.color.colorFoody));
                tv_Address.setTextColor(getResources().getColor(R.color.colorFoody));
                dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
                hideList();
            }
        } );
        // Tạo sự kiện khi click vào danh mục ở type list. Lấy dữ liệu tất các các type và ẩn list đi. Set chữ và đổi màu cho type hiện tại
        Category_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type_id = -1;
                tv_Type.setText("Danh mục");
                Category_Main.setTextColor(getResources().getColor(R.color.colorFoody));
                tv_Type.setTextColor(getResources().getColor(R.color.colorFoody));
                Choose_CategoryMain.setVisibility(View.VISIBLE);
                dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
                type_adapter.setPosition(-1);
                rv_Category.setAdapter(type_adapter);
                hideList();
            }
        } );
        dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
        return view;
    }
    // Sự kiện kiểm tra khi fragment visible. kiểm tra các list và tadWidget bottom có hiển thị hợp lệ
    // tránh trường hợp list mở và vẫn còn tadWidget bottom và ngược lại
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser)
        {
//            Log.d("what_category:",category_id+"'");
//            Log.d("what_type:",type_id+"'");
//            Log.d("what_district:",district_id+"'");
//            Log.d("what_city:",city_id+"'");
//            if(setdata !=0)
//                setPosition();
            if(isListOpen)
                MainActivity.tabhost.getTabWidget().setVisibility(View.GONE);
            else
                MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
        }
    }



    // Hàm đổ dữ liệu lên list new, type và address
    private void setDataToList(){
        categories = database.getList_Category_What();
        category_adapter = new Category_Adapter(getActivity(),categories);
        category_adapter.setPosition(category_id-1);
        category_adapter.notifyDataSetChanged();
        rv_New.setAdapter(category_adapter);

        types = database.getList_Type();
        type_adapter = new Type_Adapter(getActivity(),types);
        type_adapter.setPosition(type_id-1);
        type_adapter.notifyDataSetChanged();
        rv_Category.setAdapter(type_adapter);

        city = database.getList_District_ByCity(city_id);
        districts = city.getDistricts();
        district_adapter = new DistrictWhat_Adapter(getActivity(),districts);
        district_adapter.setPosition(district_pos);
        district_adapter.notifyDataSetChanged();
        rv_Address.setAdapter(district_adapter);
        CurrentCity.setText(city.getName());

    }
    // Hàm đổ dữ liệu lên list item chính
//    private void setItemToContent(){
//        items = database.getList_Item(category_id,type_id,district_id,city_id);
//        dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
//        item_what_adapter = new Item_What_Adapter(getActivity(), items);
//        itemWhat_adapter = new ItemWhat_Adapter(getActivity(),)
//        item_what_adapter.notifyDataSetChanged();
//        list_content.setAdapter(item_what_adapter);
//        hideList();
//    }

    // Hàm đổ dữ liệu lên lại list address sau khi đổi thành phố
    private void setDataToAddress(){
        city = database.getList_District_ByCity(city_id);
        districts = city.getDistricts();
        district_adapter = new DistrictWhat_Adapter(getActivity(),districts);
        rv_Address.setAdapter(district_adapter);
        CurrentCity.setText(city.getName());
        rv_Address.setOnItemClickListener(this);
        ChangeTextTabMain(tv_Address,city.getName());
    }
    // Hàm set chữ cho text view và đổi màu chữ
    private void ChangeTextTabMain(TextView textView, String txt){
        textView.setText(txt);
        textView.setTextColor(getResources().getColor(R.color.colorFoody));
    }
    // Hàm ẩn list new hoặc list address hoặc list type
    private void hideList(){
        // tabWidget.getChildAt(CurrentTab).setBackgroundResource(R.color.colorWhite);
        isListOpen = false;
        CurrentTab = 3;
        tabHost.setCurrentTab(CurrentTab);
        MainActivity.tabhost.getTabWidget().setVisibility(View.VISIBLE);
    }
    // Hàm hiện list new hoặc list address hoặc list type
    private void showList(int tab){
        isListOpen = true;
        CurrentTab = tab;
        // tabWidget.getChildAt(tab).setBackgroundResource(R.color.colorGray);
        tabHost.setCurrentTab(tab);
        MainActivity.tabhost.getTabWidget().setVisibility(View.GONE);
    }
    // Hàm chuyển tab
    private void changeTab(int tab) {
        if (CurrentTab == tab) {
            hideList();
            //tabWidget.getChildAt(tab).setBackgroundResource(R.color.colorWhite);
        } else {
            showList(tab);
        }
    }
    // Sự kiện click
    @Override
    public  void onClick(View v){
        int id = v.getId();
        switch (id) {
            //Khi click vào các button hủy thì ẩn đi list
            case R.id.btn_cancel_New:
            case R.id.btn_cancel_Category:
            case R.id.btn_cancel_Address:
                hideList();
                break;
            // Click vào button changecity thì mở activity changecity
            case R.id.btn_ChangeCity:
                Intent i = new Intent(getContext(), Change_City_What.class);
                startActivityForResult(i, CHOOSECITY_REQ);

                break;
        }
    }

    // Hàm nhận kết quả trả về khi change city
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CHOOSECITY_REQ){
            if(resultCode==RESULT_OK){
                city_id = Integer.parseInt(data.getStringExtra("city_id"));
                setDataToAddress();
                dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
                itemWhat_adapter.notifyDataSetChanged();
                district_id = -1;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // Hàm khởi tạo tabhost chính
    public void tabHostSetup() {

        tabHost.setup();
        TabHost.TabSpec tabCategory = tabHost.newTabSpec("Moi nhat");
        tabCategory.setContent(R.id.tab_New);
        tabCategory.setIndicator(getTabIndicator(tabHost.getContext(), "Mới nhất", R.drawable.ic_arrow_down));
        tabHost.addTab(tabCategory);

        TabHost.TabSpec tabCategory_Type = tabHost.newTabSpec("Danh muc");
        tabCategory_Type.setIndicator(getTabIndicator(tabHost.getContext(), "Danh mục", R.drawable.ic_arrow_down));
        tabCategory_Type.setContent(R.id.tab_Category);
        tabHost.addTab(tabCategory_Type);

        TabHost.TabSpec tabAddress = tabHost.newTabSpec("TPHCM");
        tabAddress.setIndicator(getTabIndicator(tabHost.getContext(), "TP.HCM", R.drawable.ic_arrow_down));
        tabAddress.setContent(R.id.tab_Address);
        tabHost.addTab(tabAddress);

        TabHost.TabSpec tabMain = tabHost.newTabSpec("Fake");
        tabMain.setIndicator("Fake");
        tabMain.setContent(R.id.tab_Fake);
        tabHost.addTab(tabMain);

        tabWidget = tabHost.getTabWidget();

        tv_Category = (TextView) tabWidget.getChildTabViewAt(0).findViewById(R.id.textView);
        tv_Type = (TextView) tabWidget.getChildTabViewAt(1).findViewById(R.id.textView);
        tv_Address = (TextView) tabWidget.getChildTabViewAt(2).findViewById(R.id.textView);

//        tvMoiNhat.setText("Test");
//        tvMoiNhat.setTextColor(getResources().getColor(R.color.colorFoody));   // còn xài

//        tvMoiNhat.setTextColor(getContext().getResources().getColor(R.color.colorBlack));
//        tvDanhMuc.setTextColor(getContext().getResources().getColor(R.color.colorBlack));
//        tvTPHCM.setTextColor(getContext().getResources().getColor(R.color.colorBlack));

//        tvMoiNhat.setAllCaps(false);
//        tvDanhMuc.setAllCaps(false);
//        tvTPHCM.setAllCaps(false);
        tabWidget.getChildAt(3).setVisibility(View.GONE);

        tabWidget.getChildAt(0).setBackgroundResource(R.drawable.tab_selector);
        tabWidget.getChildAt(1).setBackgroundResource(R.drawable.tab_selector);
        tabWidget.getChildAt(2).setBackgroundResource(R.drawable.tab_selector);


        tabHost.setCurrentTab(3);
    }
    // Hàm customize nội dụng cho tabwidget new, type và address
    private View getTabIndicator(Context context, String title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tabwidget_design, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }
    // Sự kiện click vào item list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getAdapter() == category_adapter){
            onItemCategoryClick(position);   // Khi click vào item new
        } else if(parent.getAdapter() == type_adapter){
            onItemTypeClick(position);  // Khi click vào item type
        } else if(parent.getAdapter() == district_adapter ){
            onItemDistrictClick(position);   // khi click vào item address
        }
    }

    // Xử lý sự kiện khi click vào item new
    private void onItemCategoryClick(int position) {
        ChangeTextTabMain(tv_Category,categories.get(position).getName());   // Đổi category trên tabwidget hiện tại
        category_id = categories.get(position).getId();  // Cập nhật lại list new và list nội dung theo category đã chọn
        category_adapter.setPosition(category_id-1);
        rv_New.setAdapter(category_adapter);

        itemWhat_adapter.clearData();
        itemWhat_adapter.notifyDataSetChanged();
        dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
        hideList();
    }

    // Xử lý sự kiện khi click vào item type
    private void onItemTypeClick(int position) {
        ChangeTextTabMain(tv_Type,types.get(position).getName()); // Đổi category trên tabwidget hiện tại
        type_id = types.get(position).getId();
        type_adapter.setPosition(type_id-1);        // Cập nhật lại list type và list nội dung theo category đã chọn
        rv_Category.setAdapter(type_adapter);

        itemWhat_adapter.clearData();
        itemWhat_adapter.notifyDataSetChanged();

        Category_Main.setTextColor(getResources().getColor(R.color.colorBlack));
        Choose_CategoryMain.setVisibility(View.GONE);
        dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
        hideList();
    }

    // Xử lý sự kiện khi click vào item address
    private void onItemDistrictClick(int position) {
        ChangeTextTabMain(tv_Address,districts.get(position).getName());    // Đổi category trên tabwidget hiện tại
        district_id = districts.get(position).getId();
        district_pos = position;
        district_adapter.setPosition(position);             // Cập nhật lại list address và list nội dung theo category đã chọn
        rv_Address.setAdapter(district_adapter);

        itemWhat_adapter.clearData();
        itemWhat_adapter.notifyDataSetChanged();
        dbItemWhat.getList_ItemWhat(category_id,type_id,district_id,city_id);
        CurrentCity.setTextColor(getResources().getColor(R.color.colorBlack));
        hideList();
    }

    @Override
    public void getItemWhat(ArrayList<ItemWhat> itemWhats) {
        Log.e("Số item what: ",itemWhats.size()+" item");
        itemWhat_adapter = new ItemWhat_Adapter(getActivity(),itemWhats);
        itemWhat_adapter.notifyDataSetChanged();
        list_content.setAdapter(itemWhat_adapter);
    }
}
