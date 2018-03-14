package com.example.xuant.a14110208_foody.View.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xuant.a14110208_foody.Database.DBHeroku.DBUser;
import com.example.xuant.a14110208_foody.Database.DBHeroku.UserListener;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.Session.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.xuant.a14110208_foody.R.layout.profile_setting_account;

/**
 * Created by xuant on 10/05/2017.
 */

public class Profile_SettingAccount extends AppCompatActivity implements View.OnClickListener,UserListener{

    LinearLayout  setting_account_changepass,setting_account_changeavatar;
    CircleImageView setting_account_avatar;

    DBUser dbUser;
    SessionManager session;
    HashMap<String, String> user;
    boolean isChoose = false;

    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(profile_setting_account);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }
        setting_account_changepass = (LinearLayout)findViewById(R.id.setting_account_changepass);
        // Sự kiện thay đổi mật khẩu
        setting_account_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SettingAccount_ChangePass.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                startActivityForResult(intent,2);
            }
        });

        setting_account_changeavatar = (LinearLayout)findViewById(R.id.setting_account_changeavatar);
        setting_account_avatar = (CircleImageView)findViewById(R.id.setting_account_avatar);
      // Sự kiện thay đổi ảnh đại diện
        setting_account_changeavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SettingAccount_ChangeAva.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                startActivityForResult(intent,1);
            }
        });
        // Hiển thị ảnh đại hiện
        if(Main_ProfileActivity.userCurrent.getImg()!=null)
        {
            String url_imgitem="https://foody-trietv2.herokuapp.com/getimg?nameimg=";
            Picasso.with(this).load(url_imgitem+Main_ProfileActivity.userCurrent.getImg()+".png")
                    .placeholder(R.drawable.fdi1)
                    .error(R.drawable.fdi1)
                    .into(setting_account_avatar);
        }
    }

    @Override
    public void onClick(View v) {

    }
    // Hàm nhận kết quả trả về khi thay đổi ảnh đại diện
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                // Cập nhập lại user với thông tin mới
                dbUser = new DBUser(this);
                session = new SessionManager(getBaseContext());
                user = session.getUserDetails();
                dbUser.getUser(user.get(SessionManager.KEY_EMAIL));
            }
        }
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                // Cập nhập lại user với thông tin mới
                dbUser = new DBUser(this);
                session = new SessionManager(getBaseContext());
                user = session.getUserDetails();
                dbUser.getUser(user.get(SessionManager.KEY_EMAIL));
            }
        }
    }
        // Hàm cập nhập lại user với ảnh đại diện
    @Override
    public void getUser(ArrayList<User> userArrayList) {
        Main_ProfileActivity.userCurrent = userArrayList.get(0);
        if(Main_ProfileActivity.userCurrent.getImg()!=null)
        {
            String url_imgitem="https://foody-trietv2.herokuapp.com/getimg?nameimg=";
            Picasso.with(this).load(url_imgitem+Main_ProfileActivity.userCurrent.getImg()+".png")
                    .placeholder(R.drawable.fdi1)
                    .error(R.drawable.fdi1)
                    .into(setting_account_avatar);
        }
    }
}
