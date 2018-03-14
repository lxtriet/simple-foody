package com.example.xuant.a14110208_foody.View.Profile;

import android.content.Intent;
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
 * Created by xuant on 07/05/2017.
 */

public class LoginFoody_SignUp extends AppCompatActivity implements View.OnClickListener,UserListener{
    Toolbar mToolbar;
    LinearLayout login;
    DBUser dbUser;
    String  pass="",repass="",email="",name="";

    EditText login_signup_email,login_signup_pass,login_signup_repass,login_signup_name;
    TextView login_signup_signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_foody_signup);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }
        dbUser = new DBUser(this);

        login_signup_email = (EditText) findViewById(R.id.login_signup_email);
        login_signup_pass = (EditText) findViewById(R.id.login_signup_pass);
        login_signup_repass = (EditText) findViewById(R.id.login_signup_repass);
        login_signup_name = (EditText) findViewById(R.id.login_signup_name);
        login_signup_signup = (TextView) findViewById(R.id.login_signup_signup);

        login_signup_signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            // Sự kiện khi click vào đăng ký tài khoản
            case R.id.login_signup_signup:
                pass = login_signup_pass.getText().toString();
                repass = login_signup_repass.getText().toString();
                email = login_signup_email.getText().toString();
                name = login_signup_name.getText().toString();
                // Kiểm tra dữ liệu nhập vào đầy đủ hay chưa
                if(pass.trim().length() > 0 && repass.trim().length() > 0 && email.trim().length() > 0 && name.trim().length() > 0){
                    if(pass.equals(repass))   // Nếu pass trùng nhau
                    {  // Thì get list user để kiểm tra email có tồn tại hay chưa
                        dbUser.getListUser();
                    }
                    else  // nếu pass không trùng
                        Toast.makeText(LoginFoody_SignUp.this,"Mật khẩu không trùng!!!", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(LoginFoody_SignUp.this,"Nhập đầy đủ thông tin!!!", Toast.LENGTH_LONG).show();
                break;

        }
    }
        // Hàm trả về list user
    @Override
    public void getUser(ArrayList<User> userArrayList) {
        boolean isSame = false;
        // Kiểm tra email có tồn tại hay chưa
        if(userArrayList!=null)
        {
            for(int i=0;i<userArrayList.size();i++)
            {
                if(userArrayList.get(i).getMail().equals(email))
                {
                    isSame=true;  // Khi email tồn tại thì xuất thông báo
                    break;
                }
            }
        }
        if(isSame)
            Toast.makeText(LoginFoody_SignUp.this,"Tài khoản đã tồn tại!!!", Toast.LENGTH_LONG).show();
        else
        {  // Nếu email chưa tồn tại thì tạo tài khoản và trả dữ liệu về trang trước
            dbUser.insertUser(email,name,pass);
            Intent data = new Intent();
            data.putExtra("email",email);  // Set id của thành phố đã chọn vào para city_id
            data.putExtra("pass",pass);  // Set id của thành phố đã chọn vào para city_id
            setResult(RESULT_OK,data);
            Toast.makeText(LoginFoody_SignUp.this,"Đăng ký thành công \n đang chuyển hướng !\n", Toast.LENGTH_LONG).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                }
            }, 1500);
        }

    }
}
