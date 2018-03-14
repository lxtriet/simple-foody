package com.example.xuant.a14110208_foody.View.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuant.a14110208_foody.R;

/**
 * Created by xuant on 05/05/2017.
 */

public class Profile_LoginFoody extends AppCompatActivity {

    Toolbar mToolbar;
    boolean isLogin = false;
    LinearLayout login;
    TextView loginbyemail, loginbyphone, login_signup;
    private int LOGIN_REQ = 1,LOGIN_REQ2 = 2,LOGIN_REQ3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_foody);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        loginbyemail = (TextView) findViewById(R.id.login_byemail);
        loginbyphone = (TextView) findViewById(R.id.login_byphone);
        login_signup = (TextView) findViewById(R.id.login_signup);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }
        // Sự kiện khi khi click vào đăng nhập = email
        loginbyemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LoginFoody_ByEmail.class); // Chuyển tới activity LoginFoody_ByEmail khi click vào button
                startActivityForResult(intent, LOGIN_REQ);
            }
        });
// Sự kiện khi khi click vào đăng ký
        login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LoginFoody_SignUp.class); // Chuyển tới activity LoginFoody_SignUp khi click vào button
                startActivityForResult(intent, LOGIN_REQ2);

            }
        });
    }
    // Hàm nhận kết quả trả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==LOGIN_REQ){
            if(resultCode==RESULT_OK){
                // Nhận thành công thì tiếp tục trả về trang trước
                Intent data1 = new Intent();
                data1.putExtra("email",data.getStringExtra("email"));  // Set id của thành phố đã chọn vào para city_id
                data1.putExtra("pass",data.getStringExtra("pass"));  // Set id của thành phố đã chọn vào para city_id
                setResult(RESULT_OK,data1);
                finish();
            }
        }
        if(requestCode==LOGIN_REQ2){
            if(resultCode==RESULT_OK){
                // Nhận thành công thì tiếp tục trả về trang trước
                Intent data1 = new Intent();
                data1.putExtra("email",data.getStringExtra("email"));  // Set id của thành phố đã chọn vào para city_id
                data1.putExtra("pass",data.getStringExtra("pass"));  // Set id của thành phố đã chọn vào para city_id
                setResult(RESULT_OK,data1);
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
