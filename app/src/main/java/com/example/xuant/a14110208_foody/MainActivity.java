package com.example.xuant.a14110208_foody;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.xuant.a14110208_foody.View.Main_CollectionActivity;
import com.example.xuant.a14110208_foody.View.Main_HomeActivity;
import com.example.xuant.a14110208_foody.View.Main_NotificationActivity;
import com.example.xuant.a14110208_foody.View.Profile.Main_ProfileActivity;
import com.example.xuant.a14110208_foody.View.Main_SearchActivity;

public class MainActivity extends TabActivity {

    public static TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Tạo tabhost chứa các tabs bottom


        tabhost = (TabHost) findViewById(android.R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec tab_home = tabhost.newTabSpec("Home");
        TabHost.TabSpec tab_collection = tabhost.newTabSpec("Collection");
        TabHost.TabSpec tab_search = tabhost.newTabSpec("Search");
        TabHost.TabSpec tab_notification = tabhost.newTabSpec("Notification");
        TabHost.TabSpec tab_profile = tabhost.newTabSpec("Profile");

        //Set Tab name và content của HomeActivity cho tab_home. Content sẽ được hiển thị khi tab_home được chọn
        tab_home.setContent(new Intent(this, Main_HomeActivity.class));
        tab_home.setIndicator("", getResources().getDrawable(R.drawable.tab_home));

        //Set Tab name và content của CollectionActivity cho tab_collection
        tab_collection.setContent(new Intent(this, Main_CollectionActivity.class));
        tab_collection.setIndicator("", getResources().getDrawable(R.drawable.tab_collection));

        //Set Tab name và content của SearchActivity cho tab_search
        tab_search.setContent(new Intent(this, Main_SearchActivity.class));
        tab_search.setIndicator("", getResources().getDrawable(R.drawable.tab_search));

        //Set Tab name và content của NotificationActivity cho tab_notification
        tab_notification.setContent(new Intent(this, Main_NotificationActivity.class));
        tab_notification.setIndicator("", getResources().getDrawable(R.drawable.tab_notification));

        //Set Tab name và content của ProfileActivity cho tab_profile
        tab_profile.setContent(new Intent(this, Main_ProfileActivity.class));
        tab_profile.setIndicator("", getResources().getDrawable(R.drawable.tab_polife));

        //Add các tabs vào tabhost để hiển thị
        tabhost.addTab(tab_home);
        tabhost.addTab(tab_collection);
        tabhost.addTab(tab_search);
        tabhost.addTab(tab_notification);
        tabhost.addTab(tab_profile);


    }

}
