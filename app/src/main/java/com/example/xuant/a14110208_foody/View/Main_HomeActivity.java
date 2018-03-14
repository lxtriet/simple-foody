package com.example.xuant.a14110208_foody.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xuant.a14110208_foody.Adapter.Category_Adapter;
import com.example.xuant.a14110208_foody.Database.DBHeroku.DBUser;
import com.example.xuant.a14110208_foody.Database.DBHeroku.UserListener;
import com.example.xuant.a14110208_foody.Database.Database;
import com.example.xuant.a14110208_foody.Model.Category;
import com.example.xuant.a14110208_foody.Model.City;
import com.example.xuant.a14110208_foody.Model.District;
import com.example.xuant.a14110208_foody.Model.Item;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.Model.Type;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.Session.SessionManager;
import com.example.xuant.a14110208_foody.View.Profile.Main_ProfileActivity;
import com.example.xuant.a14110208_foody.View.Profile.Profile_LoginFoody;

import java.util.ArrayList;

/**
 * Created by xuant on 03/04/2017.
 */

public class Main_HomeActivity extends AppCompatActivity implements UserListener{

    ArrayList<Category> categories;
    ArrayList<Type> types;
    ArrayList<District> districts;
    ArrayList<Item> items;
    ArrayList<City> cities;

    Category_Adapter category_adapter;
    Database database;
    private int PROFILE_REQ =1;
    SessionManager session;
    DBUser dbUser;
    public static View bottomSheetView;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        database = new Database(this.getBaseContext());
        database.openDataBase();

        session  = new SessionManager(getBaseContext());
        dbUser = new DBUser(this);
//        categories = database.getList_Category();
//        cities = database.getList_City();
//        if(cities.isEmpty())
//          Toast.makeText(this,"Empty", Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(this,cities.size(), Toast.LENGTH_LONG).show();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);    // tabLayout ăn gì ở đâu
        // Thêm phần tử vào tabLayout ở home
        tabLayout.addTab(tabLayout.newTab().setText("Ở đâu"));      // Thêm 2 tab ăn gì, ở đâu vào
        tabLayout.addTab(tabLayout.newTab().setText("Ăn gì"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());    // Tạo kiểu pager

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);      // Khởi tạo viewpager cho tablayout


        // Tạo dialog khi click vào button + ở góc trên màn hình
        bottomSheetView = getLayoutInflater().inflate(R.layout.list_item_special, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Main_HomeActivity.this);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.setCancelable (true);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);

        LinearLayout Coupon = (LinearLayout) bottomSheetView.findViewById(R.id.item1);
        LinearLayout UploadImg = (LinearLayout) bottomSheetView.findViewById(R.id.item2);
        LinearLayout CheckIn = (LinearLayout) bottomSheetView.findViewById(R.id.item3);
        LinearLayout Review = (LinearLayout) bottomSheetView.findViewById(R.id.item4);
        LinearLayout AddPlace = (LinearLayout) bottomSheetView.findViewById(R.id.item5);



        // Sư kiện click vào button + ở góc trên màn hình
        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                bottomSheetDialog.show();
            }
        });
        // Khi click vào thêm địa điểm
        AddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(session.isLoggedIn())
                {  // Nếu đã đăng nhập thì chuyển thẳng tới thêm địa điểm
                    Intent intent = new Intent(getApplication(), Main_Home_AddLocation.class); // Chuyển tới activity Main_Home_AddLocation khi click vào button
                    startActivity(intent);
                }
                else
                { // Nếu chưa đăng nhập thì chuyển thẳng tới trang đăng nhập
                    Intent intent = new Intent(getApplication(), Profile_LoginFoody.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                    startActivityForResult(intent, PROFILE_REQ);
                }
            }
        });

//        findViewById(R.id.btnFoody).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String email ="";
        String pass = "";
        if(resultCode==RESULT_OK) {
            if(requestCode==PROFILE_REQ) {
                // Sau khi đăng nhập xong thì chuyển tới thêm địa điểm
                email = data.getStringExtra("email");  // Set id của thành phố đã chọn vào para city_id
                pass = data.getStringExtra("pass");
                session.createLoginSession(email);
                dbUser.getUser(email);
            }

        }
    }

    public void OnClickFoody(View v)
    {
        Intent intent = new Intent(this, Main_Home_IconFoody.class);    // Đi đến activity Main_Home_IconFoody khi click vào biểu tưởng foody
        startActivity(intent);
    }


    BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    public void getUser(ArrayList<User> userArrayList) {
        Main_ProfileActivity.userCurrent = userArrayList.get(0);
        Intent intent = new Intent(getApplication(), Main_Home_AddLocation.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
        startActivity(intent);
    }

    // Khởi tạo class pager
    public class Pager extends FragmentStatePagerAdapter {
        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
        }

        //Overriding method getItem
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    Fragment_Where tab1 = new Fragment_Where();
                    return tab1;

                case 1:
                    Fragment_What tab2 = new Fragment_What();
                    return tab2;
                default:
                    return null;
            }
        }

        //Overriden method getCount to get the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Ở đâu";
                default:
                    return "Ăn gì";
            }
        }
    }


}
