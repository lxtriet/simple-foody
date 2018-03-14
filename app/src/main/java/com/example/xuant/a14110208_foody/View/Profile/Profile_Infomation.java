package com.example.xuant.a14110208_foody.View.Profile;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuant.a14110208_foody.Adapter.Status_Adapter;
import com.example.xuant.a14110208_foody.Database.DBHeroku.DBUser;
import com.example.xuant.a14110208_foody.Database.DBHeroku.UserListener;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.Session.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by xuant on 08/05/2017.
 */

public class Profile_Infomation  extends AppCompatActivity implements View.OnClickListener,UserListener{
    Toolbar mToolbar;
    boolean isLogin = false;
    LinearLayout profile_info_changesex,profile_info_changestatus;
    TextView profile_info_email,profile_info_date,profile_info_status,profile_info_sex,
            profile_info_birthday,profile_info_save,list_content_profile_dismiss;
    EditText profile_info_nameuser,profile_info_firstname,profile_info_lastname;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    AlertDialog.Builder dialog;
    AlertDialog alertDialog;
    LayoutInflater layoutInflater;
    DBUser dbUser;
    SessionManager session;
    String[] status = new String[]{
            "Độc thân",
            "Đã cưới",
            "Phức tạp",
            "Đang hẹn hò",
            "Đã đính hôn",
            "Quan hệ mở",
            "Góa",
            "Ly dị",
            "Ly thân"
    };

    String[] sexs = new String[]{
            "Nam",
            "Nữ",

    };


    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_infomation);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        calendar=Calendar.getInstance();
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }
        profile_info_nameuser = (EditText)findViewById(R.id.profile_info_nameuser);
        profile_info_firstname = (EditText)findViewById(R.id.profile_info_firstname);
        profile_info_lastname = (EditText)findViewById(R.id.profile_info_lastname);
        profile_info_email = (TextView)findViewById(R.id.profile_info_email);
        profile_info_date = (TextView)findViewById(R.id.profile_info_date);
        profile_info_status = (TextView)findViewById(R.id.profile_info_status);
        profile_info_sex = (TextView)findViewById(R.id.profile_info_sex);
        profile_info_birthday = (TextView)findViewById(R.id.profile_info_birthday);
        profile_info_save = (TextView)findViewById(R.id.profile_info_save);
        profile_info_changesex = (LinearLayout) findViewById(R.id.profile_info_changesex);
        profile_info_changestatus = (LinearLayout)findViewById(R.id.profile_info_changestatus);

        profile_info_save.setOnClickListener(this);
        profile_info_birthday.setOnClickListener(this);
        profile_info_changestatus.setOnClickListener(this);
        profile_info_changesex.setOnClickListener(this);

        session = new SessionManager(getBaseContext());

        dbUser = new DBUser(this);
        // Cập nhập user mỗi khi vào lại trang
        HashMap<String, String> user = session.getUserDetails();
        dbUser.getUser(user.get(SessionManager.KEY_EMAIL));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            // Sự kiện khi click vào lưuu thông tin tài khoản
            case R.id.profile_info_save:
                String username="",firstname="",lastname="",birthday="",statususer="",sex="";
                username = profile_info_nameuser.getText().toString();
                firstname = profile_info_firstname.getText().toString();
                lastname = profile_info_lastname.getText().toString();
                birthday = profile_info_birthday.getText().toString();
                statususer = profile_info_status.getText().toString();
                sex = profile_info_sex.getText().toString();
                // Hàm cập nhập thông tin tài khoản
                dbUser.updateUser(username,firstname,lastname,statususer,sex,birthday,Main_ProfileActivity.userCurrent.getMail());
                Toast.makeText(Profile_Infomation.this,"Thay đổi thông tin thành công", Toast.LENGTH_LONG).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        finish();
                    }
                }, 1500);

                break;
            // Sự kiện hiển thi date dialog để chọn ngày sinh
            case R.id.profile_info_birthday:
                showDatePickerDialog();
                break;
            // Sự kiện khi click vào thay đổi trang thái hiện tại
            case R.id.profile_info_changestatus:
                dialog = new AlertDialog.Builder(Profile_Infomation.this);
                dialog.setCancelable(true);
                layoutInflater = LayoutInflater.from((getBaseContext()));
                v = layoutInflater.inflate(R.layout.list_content_profile_infomation, null);

                ListView liststatus = (ListView) v.findViewById(R.id.list_content_profile_lv);
                final Status_Adapter district_addlocation_adapter = new Status_Adapter(
                        Profile_Infomation.this, status);

                liststatus.setAdapter(district_addlocation_adapter);

                dialog.setView(v);

                alertDialog = dialog.create();
                // Đóng dialog
                list_content_profile_dismiss = (TextView) v.findViewById(R.id.list_content_profile_dismiss);
                list_content_profile_dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });
                // Set trạng thái hiện tại khi click vào item list
                liststatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        profile_info_status.setText(status[position]);
                        alertDialog.dismiss();
                    }

                });
                alertDialog.show();
                break;
            // Sự kiện thay đổi giới tính
            case R.id.profile_info_changesex:
                dialog = new AlertDialog.Builder(Profile_Infomation.this);
                dialog.setCancelable(true);
                layoutInflater = LayoutInflater.from((getBaseContext()));
                v = layoutInflater.inflate(R.layout.list_content_profile_infomation, null);

                ListView listsex = (ListView) v.findViewById(R.id.list_content_profile_lv);

                Status_Adapter sexAdapter=new Status_Adapter(Profile_Infomation.this,sexs);
                listsex.setAdapter(sexAdapter);

                dialog.setView(v);

                alertDialog = dialog.create();
                list_content_profile_dismiss = (TextView) v.findViewById(R.id.list_content_profile_dismiss);
                list_content_profile_dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                // Cập nhập giới tính hiện tại theo item click
                listsex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        profile_info_sex.setText(sexs[position]);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
        }
    }
    // Hàm hiển thị dialog date
    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                profile_info_birthday.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                calendar.set(year, monthOfYear, dayOfMonth);
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=profile_info_birthday.getText().toString()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                Profile_Infomation.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày sinh");
        pic.show();
    }

    // Hàm cập nhập lại user mỗi khi vào lại trang
    @Override
    public void getUser(ArrayList<User> userArrayList) {
        Main_ProfileActivity.userCurrent = userArrayList.get(0);
        // Hiển thị thông tin user lên các field
        user = Main_ProfileActivity.userCurrent;
        profile_info_nameuser.setText(user.getUsername());
        profile_info_firstname.setText(user.getName());
        profile_info_lastname.setText(user.getLastname());
        profile_info_email.setText(user.getMail());
        profile_info_date.setText(user.getDatejoin());
        profile_info_status.setText(user.getStatus());
        profile_info_sex.setText(user.getSex());
        profile_info_birthday.setText(user.getBirthday());
    }
}
