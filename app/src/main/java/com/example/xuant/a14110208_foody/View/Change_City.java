package com.example.xuant.a14110208_foody.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.xuant.a14110208_foody.Adapter.City_Adapter;
import com.example.xuant.a14110208_foody.Database.Database;
import com.example.xuant.a14110208_foody.Model.City;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

public class Change_City extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private Toolbar mToolbar;
    LinearLayout btn_ChangeCountry;
    ListView list_city;
    City_Adapter city_adapter;
    ArrayList<City> cities;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__city);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if(ab!=null)
        {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
//            ab.setTitle("Danh Mục");
        }
        btn_ChangeCountry = (LinearLayout) findViewById(R.id.btn_ChangeCountry);
        list_city = (ListView)findViewById(R.id.list_city);

        database = new Database(this.getBaseContext()); // Tạo database
        database.openDataBase();
        cities = database.getList_City();
        city_adapter = new City_Adapter(this.getBaseContext(),cities);
        city_adapter.setPosition(Fragment_Where.city_id-1);
        list_city.setAdapter(city_adapter);// đổ dữ liệu các thành phố lên list

        list_city.setOnItemClickListener(this);

    }

    public void onClickChangeCountry(View v)
    {
        Intent intent = new Intent(this, Change_Country.class); // Chuyển tới activity change_country khi click vào button
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent data = new Intent();
        data.putExtra("city_id",String.valueOf(position+1));  // Set id của thành phố đã chọn vào para city_id
        setResult(RESULT_OK,data);

//        City ct = (City)  parent.getAdapter().getItem(position);
//        Toast.makeText(getBaseContext(),ct.getName().toString(), Toast.LENGTH_LONG).show();
        finish();               // Đóng activity
    }
}
