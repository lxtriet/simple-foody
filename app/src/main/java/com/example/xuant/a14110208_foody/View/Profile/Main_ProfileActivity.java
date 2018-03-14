package com.example.xuant.a14110208_foody.View.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuant.a14110208_foody.Database.DBHeroku.DBUser;
import com.example.xuant.a14110208_foody.Database.DBHeroku.UserListener;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.Session.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xuant on 03/04/2017.
 */
// Nội dung trang Profile của tabhost bottom
public class Main_ProfileActivity extends AppCompatActivity implements View.OnClickListener,UserListener{

    LinearLayout logout,login, profile_info, profile_setting_account;
    TextView activity, name_user;
    CircleImageView avatar_user;
    private int PROFILE_REQ = 1,PROFILE_REQ2 =2, PROFILE_REQ3=3;
    private static int PROFILE_REQ_CURRENT = -1;
    public static User userCurrent=null;
    public static Boolean isLogin = false;
    SessionManager session;
    DBUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        logout = (LinearLayout)findViewById(R.id.profile_logout);
        login = (LinearLayout)findViewById(R.id.profile_login);
        profile_info = (LinearLayout)findViewById(R.id.profile_info);
        activity = (TextView)findViewById(R.id.profile_activity);
        name_user = (TextView)findViewById(R.id.profile_name_user);
        avatar_user = (CircleImageView)findViewById(R.id.profile_avatar_user);
        profile_setting_account = (LinearLayout)findViewById(R.id.profile_setting_account);

        session = new SessionManager(getApplicationContext());
        dbUser = new DBUser(this);
        CheckLogin();



        login.setOnClickListener(this);

        profile_info.setOnClickListener(this);

        profile_setting_account.setOnClickListener(this);
        logout.setOnClickListener(this);
    }
    // Sự kiện kiểm tra session đang có đăng nhập hay không
    public void CheckLogin()
    {
        if(session.isLoggedIn())
        {  // nếu có đăng nhập hiển thị thanh logout và get lại user theo session email
            activity.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
            HashMap<String, String> user = session.getUserDetails();
            dbUser.getUser(user.get(SessionManager.KEY_EMAIL));
        }
        else
        {
            // nếu không thì ẩn thanh logout
            activity.setVisibility(View.INVISIBLE);
            logout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id){
            // Sự kiện khi click vào đăng nhập
            case R.id.profile_login:
                if(session.isLoggedIn())
                    Toast.makeText(Main_ProfileActivity.this,"Chức năng bất ổn !", Toast.LENGTH_LONG).show();
                else
                { // Mở Profile_LoginFoody và chờ bắt kết quả đăng nhập
                    intent = new Intent(getApplication(), Profile_LoginFoody.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                    startActivityForResult(intent, PROFILE_REQ);
                }
                break;
            // Sự kiện khi click vào thông tin tài khoản
            case R.id.profile_info:
                 if(session.isLoggedIn())
                 {// Nếu đang đăng nhập thì vào thẳng
                     intent = new Intent(getApplication(), Profile_Infomation.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                     startActivity(intent);
                 }
                 else
                 {  // Nếu chưa đăng nhập thì chuyển hướng tới trang đăng nhập
                     intent = new Intent(getApplication(), Profile_LoginFoody.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                     startActivityForResult(intent, PROFILE_REQ2);
                 }
                break;
            // Sự kiện khi click vào thiết lập tài khoản
            case R.id.profile_setting_account:
                if(session.isLoggedIn())
                {// Nếu đang đăng nhập thì vào thẳng
                    intent = new Intent(getApplication(), Profile_SettingAccount.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                    startActivity(intent);
                }
                else {
                    // Nếu chưa đăng nhập thì chuyển hướng tới trang đăng nhập
                    intent = new Intent(getApplication(), Profile_LoginFoody.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                    startActivityForResult(intent, PROFILE_REQ3);
                }
                break;
            // Sự kiện khi click vào đăng xuất
            case R.id.profile_logout:
                logOut(); break;

        }
    }

    // Hàm nhận kết quả trả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String email ="";
        String pass = "";
        if(resultCode==RESULT_OK){
            // Nếu tồn tại email trả về tức là đã đăng nhập thành công thì tạo session và checklogin để hiển thị thông tin
            email= data.getStringExtra("email");
            pass= data.getStringExtra("pass");
            session.createLoginSession(email);
            CheckLogin();
            if(requestCode==PROFILE_REQ){   // nhận request khi click vào đăng nhập
                    Toast.makeText(Main_ProfileActivity.this,email, Toast.LENGTH_LONG).show();

            }
            else if(requestCode==PROFILE_REQ2){   // nhận request khi click vào đăng nhập
                    Toast.makeText(Main_ProfileActivity.this,email+" vào thông tin tài khoản", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                // Nhận két quả trả về khi click vào thông tin tài khoản tới đăng nhập thì tiếp tục chuyển hướng tới thông tin tài khoản
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(Main_ProfileActivity.this, Profile_Infomation.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                            startActivity(intent);
                        }
                    }, 1500);

            }
            else if(requestCode==PROFILE_REQ3)   // nhận request khi click vào thông tin tài khoản
            {
                // Nhận két quả trả về khi click vào thiết lập tài khoản tới đăng nhập thì tiếp tục chuyển hướng tới thiết lập tài khoản
                    Toast.makeText(Main_ProfileActivity.this,email+" vào thiết lập tài khoản", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(Main_ProfileActivity.this, Profile_SettingAccount.class); // Chuyển tới activity Profile_LoginFoody khi click vào button
                            startActivity(intent);
                        }
                    }, 1500);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // Hàm đăng xuất
    public void logOut()
    { // Remove session và ẩn thanh đăng xuất
        session.logoutUser();
        name_user.setText("Đăng nhập");
        avatar_user.setImageResource(R.drawable.tn_ic_not_login_profile);
        activity.setVisibility(View.INVISIBLE);
        logout.setVisibility(View.GONE);
    }
        // Hàm kết thừa interface get User để hiển thị thông tin
    @Override
    public void getUser(ArrayList<User> userArrayList) {
        String url = "http://192.168.43.219:8000/getimg?nameimg=";
        String url_imgitem="https://foody-trietv2.herokuapp.com/getimg?nameimg=";
        userCurrent = userArrayList.get(0);
        if(userCurrent.getImg()!=null)
        {
            Picasso.with(this).load(url_imgitem+userCurrent.getImg()+".png")
                    .error(R.drawable.fdi1)
                    .into(avatar_user);
        }
        name_user.setText(userCurrent.getUsername());
        Log.e("","Anh avatar: "+url_imgitem+userCurrent.getImg()+".png");
    }
}
