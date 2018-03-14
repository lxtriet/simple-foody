package com.example.xuant.a14110208_foody.View;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xuant.a14110208_foody.Adapter.AdapterHeroku.ItemWhat_Adapter;
import com.example.xuant.a14110208_foody.Adapter.City_Addlocation_Adapter;
import com.example.xuant.a14110208_foody.Adapter.District_Addlocation_Adapter;
import com.example.xuant.a14110208_foody.Adapter.Type_Addlocation_Adapter;
import com.example.xuant.a14110208_foody.Database.ConvertBitmap;
import com.example.xuant.a14110208_foody.Database.DBHeroku.DBItemWhat;
import com.example.xuant.a14110208_foody.Database.DBHeroku.ItemWhatListener;
import com.example.xuant.a14110208_foody.Database.Database;
import com.example.xuant.a14110208_foody.Model.City;
import com.example.xuant.a14110208_foody.Model.District;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhat;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.User;
import com.example.xuant.a14110208_foody.Model.Type;
import com.example.xuant.a14110208_foody.R;
import com.example.xuant.a14110208_foody.View.Profile.Main_ProfileActivity;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.xuant.a14110208_foody.R.layout.home_add_location;

/**
 * Created by xuant on 11/05/2017.
 */

public class Main_Home_AddLocation extends AppCompatActivity implements View.OnClickListener, LocationListener,ItemWhatListener {
    Toolbar mToolbar;
    LinearLayout add_location_lnphone2, add_location_lnphone3, add_location_btnType, add_location_map;
    ImageView add_location_addphone1, add_location_addphone2, add_location_addphone3,add_location_img,add_location_choosephoto;
    Button add_location_btnCity, add_location_btnDistrict, add_location_timeOpen, add_location_timeClose, dialog_dismiss;
    boolean addphone1 = false, addphone2 = false, firstchoose_city = true, isChooseImg = false;

    AlertDialog.Builder dialog;
    AlertDialog alertDialog;
    LayoutInflater layoutInflater;
    TextView title_dialog, add_location_nameType, add_location_gps,add_location_save;

    EditText add_location_name, add_location_address, add_location_foodname;

    TimePickerDialog mTimePicker;
    Calendar calendar_open, calendar_close;
    int currentHour_open = 0, currentMinute_open = 0, currentHour_close = 0, currentMinute_close = 0;


    Database db;
    DBItemWhat dbItemWhat;

    private int type_id = -1, district_id = -1, city_id = 1;
    private static final int CHOOSEIMG_REG = 1;
    String imgURL, encodedString;

    ArrayList<City> cityArrayList;
    ArrayList<District> districtArrayList;
    ArrayList<Type> typeArrayList;

    private LocationManager locationManager;
    private LocationListener locationListener;

    GPSTracker gps;
    private String provider;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(home_add_location);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);// Tạo nút trở về activity trước
        }
        dbItemWhat = new DBItemWhat(this);

        add_location_lnphone2 = (LinearLayout) findViewById(R.id.add_location_lnphone2);
        add_location_lnphone3 = (LinearLayout) findViewById(R.id.add_location_lnphone3);
        add_location_btnType = (LinearLayout) findViewById(R.id.add_location_btnType);
        add_location_map = (LinearLayout) findViewById(R.id.add_location_map);

        // chuyển tới map google khi click vào
        add_location_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Mở map", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Main_Home_AddLocation.this, AddLocation_MapMain.class));
            }
        });

        add_location_addphone1 = (ImageView) findViewById(R.id.add_location_addphone1);
        add_location_addphone2 = (ImageView) findViewById(R.id.add_location_addphone2);
        add_location_addphone3 = (ImageView) findViewById(R.id.add_location_addphone3);
        add_location_img = (ImageView) findViewById(R.id.add_location_img);
        add_location_choosephoto = (ImageView) findViewById(R.id.add_location_choosephoto);

        add_location_btnCity = (Button) findViewById(R.id.add_location_btnCity);
        add_location_btnDistrict = (Button) findViewById(R.id.add_location_btnDistrict);
        add_location_timeOpen = (Button) findViewById(R.id.add_location_timeOpen);
        add_location_timeClose = (Button) findViewById(R.id.add_location_timeClose);
        add_location_nameType = (TextView) findViewById(R.id.add_location_nameType);
        add_location_gps = (TextView) findViewById(R.id.add_location_gps);
        add_location_save = (TextView) findViewById(R.id.add_location_save);

        add_location_name = (EditText) findViewById(R.id.add_location_name);
        add_location_address = (EditText) findViewById(R.id.add_location_address);
        add_location_foodname = (EditText) findViewById(R.id.add_location_foodname);


        add_location_lnphone2.setVisibility(View.GONE);
        add_location_lnphone3.setVisibility(View.GONE);

        add_location_addphone1.setOnClickListener(this);
        add_location_addphone2.setOnClickListener(this);
        add_location_addphone3.setOnClickListener(this);

        add_location_btnCity.setOnClickListener(this);
        add_location_btnDistrict.setOnClickListener(this);
        add_location_timeOpen.setOnClickListener(this);
        add_location_timeClose.setOnClickListener(this);
        add_location_btnType.setOnClickListener(this);

        // Lấy vị trí hiện tại của bạn
        gps = new GPSTracker(Main_Home_AddLocation.this);
        if(gps.canGetLocation()){
            double longitude = gps.getLongitude();
            double latitude = gps .getLatitude();
            add_location_gps.setText("Lat: "+(double)Math.round(latitude*10000)/10000+" - Long: "+(double)Math.round(longitude*10000)/10000);
        }
        else
            gps.showSettingsAlert();
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        String provider;
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//        Location location = locationManager.getLastKnownLocation(provider);
//
//        if (location != null) {
//            onLocationChanged(location);
//        } else {
//            Toast.makeText(getApplicationContext(), "Chưa bật GPS", Toast.LENGTH_LONG).show();
//        }

        add_location_save.setOnClickListener(this);
        add_location_choosephoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            // Sự kiện khi click vào button cộng ở số điện thoại để thêm số điện thoại
            case R.id.add_location_addphone1:
                if(!addphone1)
                {
                    add_location_addphone1.setImageResource(R.drawable.ic_minus_gray);
                    add_location_lnphone2.setVisibility(View.VISIBLE);
                    addphone1 = true;
                }
                else
                {
                    addphone1 = false;
                    if(add_location_lnphone2.getVisibility()==View.GONE)
                    {
                        add_location_lnphone3.setVisibility(View.GONE);
                    }
                    else
                    {
                        add_location_lnphone2.setVisibility(View.GONE);
                        add_location_addphone1.setImageResource(R.drawable.ic_plus_green);
                        add_location_addphone2.setImageResource(R.drawable.ic_plus_green);
                    }
                }
                break;
            // Sự kiện khi click vào button cộng ở số điện thoại để thêm số điện thoại
            case R.id.add_location_addphone2:
                if(!addphone2)
                {
                    add_location_addphone2.setImageResource(R.drawable.ic_minus_gray);
                    add_location_lnphone3.setVisibility(View.VISIBLE);
                    addphone2 = true;
                }
                else
                {
                    addphone2 = false;
                    add_location_lnphone3.setVisibility(View.GONE);
                    add_location_addphone2.setImageResource(R.drawable.ic_plus_green);
                }
                break;
            // Sự kiện khi click vào button cộng ở số điện thoại để thêm số điện thoại
            case R.id.add_location_addphone3:
                Toast.makeText(getApplicationContext(), "Tối đa 3 số điện thoại", Toast.LENGTH_LONG).show();
                break;
            // Sự kiện khi chọn city khi theo item
            case R.id.add_location_btnCity:
                dialog = new AlertDialog.Builder(Main_Home_AddLocation.this);
                dialog.setCancelable(true);
                layoutInflater = LayoutInflater.from((getBaseContext()));
                v = layoutInflater.inflate(R.layout.list_content_dialog, null);

                db = new Database(getBaseContext());
                db.openDataBase();
                cityArrayList = new ArrayList<>();
                cityArrayList = db.getList_City();
                db.close();

                final ListView listcity = (ListView) v.findViewById(R.id.list_content_dialog_lv);
                final City_Addlocation_Adapter city_addlocation_adapter = new City_Addlocation_Adapter(Main_Home_AddLocation.this, cityArrayList);
                if(firstchoose_city)
                {
                    city_addlocation_adapter.setPosition(0);
                    firstchoose_city = false;
                }

                title_dialog = (TextView) v.findViewById(R.id.list_content_dialog_titlename);
                title_dialog.setText("Chọn thành phố");

                listcity.setAdapter(city_addlocation_adapter);

                dialog.setView(v);

                alertDialog = dialog.create();
                // button đóng dialog
                dialog_dismiss = (Button) v.findViewById(R.id.list_content_dialog_dismiss);
                dialog_dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                // set id hiện tại của city khi item được chọn trong listview
                listcity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            City ct = (City) parent.getItemAtPosition(position);
                            city_id = ct.getId();
                            add_location_btnCity.setText(ct.getName());
                            city_addlocation_adapter.setPosition(position);
                            add_location_btnDistrict.setText("Quận/Huyện");
                            district_id = -1;
                            alertDialog.dismiss();
                        }

                    });
                alertDialog.show();
                break;
            // sự kiện click chọn district
            case R.id.add_location_btnDistrict:
                dialog = new AlertDialog.Builder(Main_Home_AddLocation.this);
                dialog.setCancelable(true);
                layoutInflater = LayoutInflater.from((getBaseContext()));
                v = layoutInflater.inflate(R.layout.list_content_dialog, null);

                db = new Database(getBaseContext());
                db.openDataBase();
                districtArrayList = new ArrayList<>();
                districtArrayList = db.getList_District_ByCity(city_id).getDistricts();
                db.close();

                ListView listdistrict = (ListView) v.findViewById(R.id.list_content_dialog_lv);
                final District_Addlocation_Adapter district_addlocation_adapter = new District_Addlocation_Adapter(Main_Home_AddLocation.this, districtArrayList);

                title_dialog = (TextView) v.findViewById(R.id.list_content_dialog_titlename);
                title_dialog.setText("Chọn quận/huyện");

                listdistrict.setAdapter(district_addlocation_adapter);

                dialog.setView(v);

                alertDialog = dialog.create();

                // button đóng dialog
                dialog_dismiss = (Button) v.findViewById(R.id.list_content_dialog_dismiss);
                dialog_dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });
                // set id hiện tại của district khi click vào chọn district
                listdistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view,
                                                            int position, long id) {
                                        District dt = (District) parent.getItemAtPosition(position);
                                        district_id = dt.getId();
                                        Log.e("district id ====",""+district_id);
                                        add_location_btnDistrict.setText(dt.getName());
                                        district_addlocation_adapter.setPosition(position);
                                        alertDialog.dismiss();
                                    }

                                });
                alertDialog.show();
                break;
            // Sự kiện hiển thị dialog time mở cửa
            case R.id.add_location_timeOpen:
                calendar_open = Calendar.getInstance();
                calendar_open.set(0,0,0,currentHour_open,currentMinute_open);
                final int hour = calendar_open.get(Calendar.HOUR_OF_DAY);
                final int minute = calendar_open.get(Calendar.MINUTE);
                // custom lại thời gian
                mTimePicker = new TimePickerDialog(Main_Home_AddLocation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hourTam = selectedHour;
                        if (hourTam > 12) hourTam = hourTam - 12;
                        currentHour_open = selectedHour;
                        currentMinute_open = selectedMinute;
                        add_location_timeOpen.setText(hourTam + ":" + selectedMinute + (selectedHour>12?" PM":" AM"));
                        calendar_open.set(Calendar.HOUR_OF_DAY, hour);
                        calendar_open.set(Calendar.MINUTE, minute);
                    }
                }, hour, minute, true);

                mTimePicker.setTitle("Giờ mở cửa");
                mTimePicker.show();
                break;
            // Sự kiện hiển thị dialog time đóng cửa
            case R.id.add_location_timeClose:
                calendar_close = Calendar.getInstance();
                calendar_close.set(0,0,0,currentHour_close,currentMinute_close);
                final int hour_close = calendar_close.get(Calendar.HOUR_OF_DAY);
                final int minute_close = calendar_close.get(Calendar.MINUTE);
                // custom thời gian
                mTimePicker = new TimePickerDialog(Main_Home_AddLocation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hourTam = selectedHour;
                        if (hourTam > 12) hourTam = hourTam - 12;
                        currentHour_close = selectedHour;
                        currentMinute_close = selectedMinute;
                        add_location_timeClose.setText(hourTam + ":" + selectedMinute + (selectedHour>12?" PM":" AM"));
                        calendar_close.set(Calendar.HOUR_OF_DAY, hour_close);
                        calendar_close.set(Calendar.MINUTE, minute_close);
                    }
                }, hour_close, minute_close, true);

                mTimePicker.setTitle("Giờ đóng cửa");
                mTimePicker.show();
                break;
            // Sự kiện chọn loại item
            case R.id.add_location_btnType:
                dialog = new AlertDialog.Builder(Main_Home_AddLocation.this);
                dialog.setCancelable(true);
                layoutInflater = LayoutInflater.from((getBaseContext()));
                v = layoutInflater.inflate(R.layout.list_content_dialog, null);

                db = new Database(getBaseContext());
                db.openDataBase();
                typeArrayList = new ArrayList<>();
                typeArrayList = db.getList_Type();
                db.close();

                ListView listtype = (ListView) v.findViewById(R.id.list_content_dialog_lv);
                final Type_Addlocation_Adapter type_addlocation_adapter = new Type_Addlocation_Adapter(Main_Home_AddLocation.this, typeArrayList);

                title_dialog = (TextView) v.findViewById(R.id.list_content_dialog_titlename);
                title_dialog.setText("Chọn loại hình");

                listtype.setAdapter(type_addlocation_adapter);

                dialog.setView(v);

                alertDialog = dialog.create();
                // Button đóng dialog
                dialog_dismiss = (Button) v.findViewById(R.id.list_content_dialog_dismiss);
                dialog_dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });
                // set type hiện tại khi click vào chọn item
                listtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Type type = (Type) parent.getItemAtPosition(position);
                        type_id = type.getId();
                        add_location_nameType.setText(type.getName());
                        type_addlocation_adapter.setPosition(position);
                        alertDialog.dismiss();
                    }

                });
                alertDialog.show();
                break;
//            case R.id.add_location_map:
//                startActivity(new Intent(Main_Home_AddLocation.this, AddLocation_MapMain.class));
//                break;
            // sự kiện chọn ảnh vào location
            case R.id.add_location_choosephoto:
                Log.e("Vao"," choose img click");
                pickGalleryImage(); break;
            // sự kiện save location
            case R.id.add_location_save:
                // Kiểm tra dữ liệu nhập vào đã đủ chưa
                if(city_id == -1 || district_id==-1 || type_id ==-1 || add_location_name.getText().toString().trim().length() <= 0
                        || add_location_address.getText().toString().trim().length() <= 0 ||
                        add_location_foodname.getText().toString().trim().length() <= 0 || !isChooseImg)
                    Toast.makeText(Main_Home_AddLocation.this,"Nhập đầy đủ thông tin !!!", Toast.LENGTH_LONG).show();
                else  // Khi dữ liệu nhập vào đủ
                {  // Lấy thông tin user từ Main_ProfileActivity
                    User user = Main_ProfileActivity.userCurrent;
                    String address = add_location_address.getText().toString();
                    String name = add_location_name.getText().toString();
                    String foodname = add_location_foodname.getText().toString();
                    // Hàm insert item
                    dbItemWhat.insertItemWhat(address,name,foodname,encodedString,district_id,
                            type_id,user.getUsername(),user.getImg(),city_id,user.getId());
                    // Insert xong thì load lại list item ngoài trang chủ
                    dbItemWhat.getList_ItemWhat(Fragment_What.category_id,Fragment_What.type_id,Fragment_What.district_id,Fragment_What.city_id);
                    Toast.makeText(Main_Home_AddLocation.this,"Thêm thành công \n đang chuyển hướng !", Toast.LENGTH_LONG).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            finish();
                            Main_HomeActivity.bottomSheetView.setVisibility(View.GONE);
                        }
                    }, 2000);
                }
        }
    }
    // Hàm lấy đường dẫn file ảnh
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
    // Hàm tạo Intent android hỗ trợ để lấy content ở đây set là image
    private void pickGalleryImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), CHOOSEIMG_REG);
    }

    // Hàm nhận kết quả trả về khi chọn ảnh
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSEIMG_REG) {

                Uri selectedImageURI = data.getData();
                // Gắn ảnh vào imageview
                Picasso.with(this.getApplication()).load(selectedImageURI).noPlaceholder()
                        .into(add_location_img);
                isChooseImg = true;
                // Chuyển ảnh thành chuỗi mã hóa để lưu vào cơ sở dữ liệu
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

    @Override
    public void onLocationChanged(Location location) {
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());
        add_location_gps.setText("Lat: "+(double)Math.round(lat*10000)/10000+" - Long: "+(double)Math.round(lng*10000)/10000);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    // Hàm kế thừa interface thực hiện đổ item lên list chính
    @Override
    public void getItemWhat(ArrayList<ItemWhat> itemWhats) {
        Fragment_What.itemWhat_adapter = new ItemWhat_Adapter(this,itemWhats);
        Fragment_What.itemWhat_adapter.notifyDataSetChanged();
        Fragment_What.list_content.setAdapter(Fragment_What.itemWhat_adapter);
    }
}
