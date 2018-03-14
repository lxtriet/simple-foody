package com.example.xuant.a14110208_foody.View.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
 * Created by xuant on 07/05/2017.
 */

public class LoginFoody_ByEmail extends AppCompatActivity implements View.OnClickListener,UserListener{
    Toolbar mToolbar;
    LinearLayout login;
    TextView login_byemail_login;
    EditText login_byemail_email,login_byemail_pass;
    DBUser dbUser;
    String email="",pass="";
    public static User user = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_foody_byemail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }

        login_byemail_login = (TextView) findViewById(R.id.login_byemail_login);
        login_byemail_pass = (EditText) findViewById(R.id.login_byemail_pass);
        login_byemail_email = (EditText) findViewById(R.id.login_byemail_email);

        login_byemail_login.setOnClickListener(this);
        dbUser = new DBUser(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            // Sự kiện khi click vào đăng nhập
            case R.id.login_byemail_login:
                 email = login_byemail_email.getText().toString();
                 pass = login_byemail_pass.getText().toString();
                // Kiểm tra chuỗi nhập vô trống hay không
                if(email.trim().length() > 0 && pass.trim().length() > 0)
                { // Nếu ok thì get user theo email
                    dbUser.getUser(email);
                }
                else
                {
                    Toast.makeText(LoginFoody_ByEmail.this,"Nhập đầy đủ email và pass", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    // Hàm get user theo email
    @Override
    public void getUser(ArrayList<User> userArrayList) {
        user = userArrayList.get(0);
        Log.e("","Đang kiểm tra pass "+user.getName());
        if(!user.getMail().equals(email) || !user.getPassword().equals(pass))
        {  // Kiểm tra email và pass có trùng khớp với tài khoản hiện tại
            Toast.makeText(LoginFoody_ByEmail.this,"Password hoặc mật khẩu không đúng !", Toast.LENGTH_LONG).show();
        }
        else
        {  // Nếu trùng thì truyền dữ liệu về trang trước là đóng activity
            this.user = user;
            Intent data = new Intent();
            data.putExtra("email",user.getMail());  // Set id của thành phố đã chọn vào para city_id
            data.putExtra("pass",user.getPassword());  // Set id của thành phố đã chọn vào para city_id
            setResult(RESULT_OK,data);
            Toast.makeText(LoginFoody_ByEmail.this,"Đăng nhập thành công \n đang chuyển hướng !", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 1500);
        }
    }

}
