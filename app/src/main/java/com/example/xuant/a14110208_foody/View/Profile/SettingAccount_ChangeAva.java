package com.example.xuant.a14110208_foody.View.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuant.a14110208_foody.Database.ConvertBitmap;
import com.example.xuant.a14110208_foody.Database.DBHeroku.DBUser;
import com.example.xuant.a14110208_foody.Database.DBHeroku.UserListener;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.Session.SessionManager;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.xuant.a14110208_foody.R.layout.profile_setting_account_changeavatar;

/**
 * Created by xuant on 19/05/2017.
 */

public class SettingAccount_ChangeAva extends AppCompatActivity implements View.OnClickListener,UserListener{
    CircleImageView setting_account_changeavatar_ava;
    LinearLayout settingaccount_changeavatar;
    TextView changeava_save;
    int CHOOSEIMG_REG = 1;
    String encodedString="";
    DBUser dbUser;
    SessionManager session;
    HashMap<String, String> user;
    Uri selectedImageURI;
    boolean isChoose = false;

    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(profile_setting_account_changeavatar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }

        setting_account_changeavatar_ava = (CircleImageView) findViewById(R.id.setting_account_changeavatar_ava);
        settingaccount_changeavatar = (LinearLayout) findViewById(R.id.settingaccount_changeavatar2);
        changeava_save = (TextView) findViewById(R.id.changeava_save);

        settingaccount_changeavatar.setOnClickListener(this);
        changeava_save.setOnClickListener(this);

        dbUser = new DBUser(this);
        session = new SessionManager(getBaseContext());

        user = session.getUserDetails();



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            // Click vào đổi ảnh đại điện
            case R.id.settingaccount_changeavatar2:
                // hàm chọn ảnh chạy
                pickGalleryImage();
                break;
            // Click vào save ảnh đại diện
            case R.id.changeava_save:
                if(isChoose)
                {  // Ảnh đã được chọn thì update
                    dbUser.updateImgUser(encodedString,user.get(SessionManager.KEY_EMAIL));
                    dbUser.getUser(user.get(SessionManager.KEY_EMAIL));
                    Toast.makeText(SettingAccount_ChangeAva.this,"Thay đổi ảnh đại diện thành công", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                        }
                    }, 1500);
                }
                else  // Ảnh không được chọn thì thoát ra bình thường
                    finish();
                break;
        }
    }
    // Lấy đường dẫn file ảnh
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor;
        if(Build.VERSION.SDK_INT >19)
        {
            // Will return "image:x*"
            String wholeID = DocumentsContract.getDocumentId(uri);
            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];
            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";
            cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    projection, sel, new String[]{ id }, null);
        }
        else
        {
            cursor = getContentResolver().query(uri, projection, null, null, null);
        }
        String path = null;
        try
        {
            int column_index = cursor
                    .getColumnIndex(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index).toString();
            cursor.close();
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }
        return path;
    }
        // Tạo itent hỗ trợ sẵn để get content là image
    private void pickGalleryImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CHOOSEIMG_REG);
    }

    // nhận kết quả trả về khi chọn ảnh
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSEIMG_REG) {

                 selectedImageURI = data.getData();

                Picasso.with(this.getApplication()).load(selectedImageURI).noPlaceholder()
                        .into(setting_account_changeavatar_ava);
                isChoose = true;
                ConvertBitmap myBitMap = new ConvertBitmap(this);
                Bitmap bitmap = null;
                try {
                    bitmap = myBitMap.decodeUri(selectedImageURI);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                encodedString = myBitMap.getStringFromBitmap(bitmap);
            }

        }
    }
    // Cập nhập lại ảnh cho user
    @Override
    public void getUser(ArrayList<User> userArrayList) {
        Main_ProfileActivity.userCurrent = userArrayList.get(0);
        if(Main_ProfileActivity.userCurrent.getImg()!=null)
        {
            String url_imgitem="https://foody-trietv2.herokuapp.com/getimg?nameimg=";
            Picasso.with(this).load(url_imgitem+Main_ProfileActivity.userCurrent.getImg()+".png")
                    .error(R.drawable.fdi1)
                    .into(setting_account_changeavatar_ava);
        }
    }
}
