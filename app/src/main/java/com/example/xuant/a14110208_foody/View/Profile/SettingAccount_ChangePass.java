package com.example.xuant.a14110208_foody.View.Profile;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuant.a14110208_foody.Database.DBHeroku.DBUser;
import com.example.xuant.a14110208_foody.Database.DBHeroku.UserListener;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.R;

import java.util.ArrayList;

/**
 * Created by xuant on 10/05/2017.
 */

public class SettingAccount_ChangePass extends AppCompatActivity implements  View.OnFocusChangeListener,View.OnClickListener,UserListener {
    Toolbar mToolbar;

    LinearLayout login, changepass_ln1, changepass_ln2, changepass_ln3;
    EditText changepass_oldpass, changepass_newpass, changepass_newpass2;
    TextView changepass_save;
    String oldpass="", newpass="",newpass2="";
    DBUser dbUser;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting_accout_changepass);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        changepass_ln1 = (LinearLayout) findViewById(R.id.changepass_ln1);
        changepass_ln2 = (LinearLayout) findViewById(R.id.changepass_ln2);
        changepass_ln3 = (LinearLayout) findViewById(R.id.changepass_ln3);
        changepass_oldpass = (EditText) findViewById(R.id.changepass_oldpass);
        changepass_newpass = (EditText) findViewById(R.id.changepass_newpass);
        changepass_newpass2 = (EditText) findViewById(R.id.changepass_newpass2);
        changepass_save = (TextView) findViewById(R.id.changepass_save);
        dbUser = new DBUser(this);


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }
        changepass_oldpass.setOnFocusChangeListener(this);
        changepass_newpass.setOnFocusChangeListener(this);
        changepass_newpass2.setOnFocusChangeListener(this);
        changepass_save.setOnClickListener(this);


    }
    public void SetBackground(View v, boolean hasFocus){
        if(!hasFocus)
            v.setBackgroundResource(R.drawable.border_gray);
        else
            v.setBackgroundResource(R.drawable.border_red);
    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id=v.getId();
        switch (id){
            case R.id.changepass_oldpass: SetBackground(changepass_ln1,hasFocus); break;
            case R.id.changepass_newpass: SetBackground(changepass_ln2,hasFocus); break;
            case R.id.changepass_newpass2: SetBackground(changepass_ln3,hasFocus); break;
            // Click vào lưu thay đổi mật khẩu

        }
    }

    @Override
    public void getUser(ArrayList<User> userArrayList) {

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.changepass_save:
                oldpass = changepass_oldpass.getText().toString();
                newpass = changepass_newpass.getText().toString();
                newpass2 = changepass_newpass2.getText().toString();
                // Kiểm tra dữ liệu nhập vào có rỗng
                if(oldpass.trim().length() > 0 && newpass.trim().length() > 0 && newpass2.trim().length() > 0)
                {   // Kiểm tra pass mới có trùng nhau
                    if(newpass.equals(newpass2))
                    {  // Kiểm tra pass cũ có đúng không
                        if(oldpass.equals(Main_ProfileActivity.userCurrent.getPassword()))
                        {  // Hàm update pass
                            dbUser.updatePassUser(newpass,Main_ProfileActivity.userCurrent.getMail());
                            Main_ProfileActivity.userCurrent.setPassword(newpass);
                            Toast.makeText(SettingAccount_ChangePass.this,"Đổi mật khẩu thành công \n đang chuyển hướng !", Toast.LENGTH_LONG).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    finish();
                                }
                            }, 1500);
                        }
                        else
                            Toast.makeText(SettingAccount_ChangePass.this,"Mật khẩu cũ không đúng", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(SettingAccount_ChangePass.this,"Mật khẩu mới không trùng nhau", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SettingAccount_ChangePass.this,"Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }
}
