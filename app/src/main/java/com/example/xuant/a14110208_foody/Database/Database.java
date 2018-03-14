package com.example.xuant.a14110208_foody.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xuant.a14110208_foody.Model.Category;
import com.example.xuant.a14110208_foody.Model.City;
import com.example.xuant.a14110208_foody.Model.District;
import com.example.xuant.a14110208_foody.Model.ModelHeroku.ItemWhere;
import com.example.xuant.a14110208_foody.Model.Review;
import com.example.xuant.a14110208_foody.Model.Street;
import com.example.xuant.a14110208_foody.Model.Type;
import com.example.xuant.a14110208_foody.Service.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuant on 12/04/2017.
 */

public class Database extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodyDB.sqlite";  // tên database
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context context;
    private static  Service mService;
    public  interface getItemWhereListener{
        void getItemWhere(List<ItemWhere> itemWhereList);// Model j vayaj ItemWhere
    }
    private getItemWhereListener getItemWhereListener;
    ArrayList<ItemWhere> itemWheres = new ArrayList<>();

    // Định nghĩa database
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    // Truy vấn trả về list category
    public ArrayList<Category> getList_Category(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Category> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CATEGORY", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Category c = new Category();
            c.setId(cursor.getInt(0));
            c.setName(cursor.getString(1));
            c.setImg_unselected(cursor.getString(2));
            c.setImg_selected(cursor.getString(3));
            c.setIsNew(0);
            cursor.moveToNext();
            list.add(c);
        }
        cursor.close();
        db.close();
        return list;
    }
    // Truy vấn trả về type theo id của item
//    public Type getType_ByItem(int item_id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Type type = new Type();
//        Cursor cursor = db.rawQuery("SELECT * FROM TYPE,ITEM WHERE ITEM.TYPEID=TYPE.ID AND ITEM.ID="+item_id, null);
//        cursor.moveToFirst();
//        type.setId(cursor.getInt(0));
//        type.setName(cursor.getString(1));
//        type.setImg(cursor.getString(2));
//        type.setCategory_id(cursor.getInt(3));
//        cursor.close();
//        db.close();
//        return type;
//    }
    // Truy vấn trả về list category của fragment what
    public ArrayList<Category> getList_Category_What(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Category> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CATEGORY LIMIT 4", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Category c = new Category();
            c.setId(cursor.getInt(0));
            c.setName(cursor.getString(1));
            c.setImg_unselected(cursor.getString(2));
            c.setImg_selected(cursor.getString(3));
            c.setIsNew(0);
            cursor.moveToNext();
            list.add(c);
        }
        cursor.close();
        db.close();
        return list;
    }
    // Truy vấn trả về list type
    public ArrayList<Type> getList_Type(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Type> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TYPE LIMIT 15", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Type c = new Type();
            c.setId(cursor.getInt(0));
            c.setName(cursor.getString(1));
            c.setImg(cursor.getString(2));
            c.setCategory_id(cursor.getInt(3));
            cursor.moveToNext();
            list.add(c);
        }
        cursor.close();
        db.close();
        return list;
    }
    // Truy vấn trả về city bằng mã city
    public City getList_District_ByCity(int city_id){
        SQLiteDatabase db = this.getReadableDatabase();
        City city = new City();
        Cursor cursor = db.rawQuery("SELECT * FROM CITY WHERE ID="+city_id, null);
        cursor.moveToFirst();
        city.setId(cursor.getInt(0));
        city.setName(cursor.getString(1));
        cursor.close();

        ArrayList<District> list=new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM DISTRICT WHERE CITYID="+city_id+" ORDER BY(NAME)", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            District c = new District();
            c.setId(cursor.getInt(0));
            c.setCity_id(cursor.getInt(1));
            c.setName(cursor.getString(2));
            c.setCout_street(20 + (int)(Math.random() * 80));
            cursor.moveToNext();
            list.add(c);
        }
        cursor.close();
        city.setDistricts(list);


        db.close();
        return city;
    }

    // Truy vấn trả về city bằng mã city
    public ArrayList<Street> getList_Street_ByDistrict(int district_id){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Street> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM STREET WHERE DISTRICTID="+district_id+" ORDER BY(NAME)", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Street c = new Street();
            c.setId(cursor.getInt(0));
            c.setDistrict_id(cursor.getInt(1));
            c.setName(cursor.getString(2));
            cursor.moveToNext();
            list.add(c);
        }
        cursor.close();

        db.close();
        return list;
    }

    // Truy vấn trả về list city
    public ArrayList<City> getList_City(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<City> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM CITY", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            City c = new City();
            c.setId(cursor.getInt(0));
            c.setName(cursor.getString(1));
            cursor.moveToNext();
            list.add(c);
        }
        cursor.close();
        db.close();
        return list;
    }
    // Truy vấn trả về city bằng tên thành phố
    public City getCity_ByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        City city = new City();
        Cursor cursor = db.rawQuery("SELECT * FROM CITY WHERE NAME LIKE '%"+name+"%' LIMIT 1", null);
        cursor.moveToFirst();
        city.setId(cursor.getInt(0));
        city.setName(cursor.getString(1));
        cursor.close();
        db.close();
        return city;
    }

//    public void getList_ItemWhere(int category_id, int type_id, int district_id, int city_id){
//        mService = Utils.getService();
//        Call<ArrayList<ItemWhere>> call = mService.getItemWhere(category_id,type_id,district_id,city_id);
//        Log.e("Response",call.request().url().toString());
//        call.enqueue(new Callback<ArrayList<ItemWhere>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ItemWhere>> call, Response<ArrayList<ItemWhere>> response) {
//                if(response.isSuccessful()) {
//                    getItemWhereListener.getItemWhere(response.body());
//                    Log.e("Response","lay duoc");
//                }
//                else
////                    Log.e("Response",response.message());
//                    Log.e("Response","khong lay duoc");
//            }
//            @Override
//            public void onFailure(Call<ArrayList<ItemWhere>> call, Throwable t) {
//                Log.e("Response",t.getMessage());
//            }
//        });
//    }

    // Truy vấn trả về list item dựa theo 4 dữ liệu là id category, type, district và city

//    public ArrayList<Item> getList_Item(int category_id, int type_id, int district_id, int city_id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Item> list=new ArrayList<>();
//        Cursor cursor,cursor_review;
//        // Lúc nào cũng có category_id với city_id
//        if(district_id != -1){
//            if(type_id == -1)
//                 cursor = db.rawQuery("SELECT * FROM ITEM WHERE CATEGORYID="+category_id+
//                         " and DISTRICTID="+district_id+"  LIMIT 40", null);
//            else
//                cursor = db.rawQuery("SELECT * FROM ITEM WHERE CATEGORYID="+category_id+
//                        " and DISTRICTID="+district_id+" and TYPEID="+type_id+"  LIMIT 40", null);
//        }
//        else
//        {
//            if(type_id == -1)
//                cursor = db.rawQuery("SELECT * FROM ITEM,DISTRICT,CITY WHERE ITEM.DISTRICTID=DISTRICT.ID AND"
//                        +" DISTRICT.CITYID=CITY.ID AND CITY.ID="+city_id+" AND CATEGORYID="+category_id+
//                        "  LIMIT 40", null);
//            else
//                cursor = db.rawQuery("SELECT * FROM ITEM,DISTRICT,CITY WHERE ITEM.DISTRICTID=DISTRICT.ID AND"
//                        +" DISTRICT.CITYID=CITY.ID AND CITY.ID="+city_id+" AND CATEGORYID="+category_id+
//                        " AND TYPEID="+type_id+" LIMIT 40", null);
//        }
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Item item = new Item();
//            item.setId(cursor.getInt(0));
//            item.setAddress(cursor.getString(1));
//            item.setName(cursor.getString(2));
//            item.setTotal_Reviews(cursor.getInt(3));
//            item.setImg(cursor.getString(4));
//            item.setDistrict_id(cursor.getInt(5));
//            item.setAvgRating(cursor.getDouble(6));
//            item.setCategory_id(cursor.getInt(7));
//            item.setType_id(cursor.getInt(8));
//            item.setTotal_pictures(20 + (int)(Math.random() * 80));
//            item.setRestaurant_id(20 + (int)(Math.random() * 80));
//
//            ArrayList<Review> reviews=new ArrayList<>();
//            cursor_review = db.rawQuery("SELECT * FROM REVIEW WHERE ITEMID="+cursor.getInt(0)+" LIMIT 2", null);
//            cursor_review.moveToFirst();
//            while (!cursor_review.isAfterLast()){
//                Review review = new Review();
//                review.setId(cursor_review.getInt(0));
//                review.setName(cursor_review.getString(1));
//                review.setRating(cursor_review.getInt(2));
//                review.setComment(cursor_review.getString(3));
//                review.setComment_trim(cursor_review.getString(4));
//                review.setAvatar(cursor_review.getString(5));
//                review.setItem_id(cursor_review.getInt(6));
//                review.setReview_url(cursor_review.getString(7));
//                cursor_review.moveToNext();
//                reviews.add(review);
//            }
//            cursor_review.close();
//            item.setReviews(reviews);
//
//            cursor.moveToNext();
//            list.add(item);
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
//


//    public ArrayList<Item> getList_ItemMore(int category_id, int type_id, int district_id, int city_id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Item> list=new ArrayList<>();
//        Cursor cursor,cursor_review;
//        // Lúc nào cũng có category_id với city_id
//        if(district_id != -1){
//            if(type_id == -1)
//                cursor = db.rawQuery("SELECT * FROM ITEM WHERE CATEGORYID="+category_id+
//                        " and DISTRICTID="+district_id+"  LIMIT 32", null);
//            else
//                cursor = db.rawQuery("SELECT * FROM ITEM WHERE CATEGORYID="+category_id+
//                        " and DISTRICTID="+district_id+" and TYPEID="+type_id+"  LIMIT 32", null);
//        }
//        else
//        {
//            if(type_id == -1)
//                cursor = db.rawQuery("SELECT * FROM ITEM,DISTRICT,CITY WHERE ITEM.DISTRICTID=DISTRICT.ID AND"
//                        +" DISTRICT.CITYID=CITY.ID AND CITY.ID="+city_id+" AND CATEGORYID="+category_id+
//                        "  LIMIT 32", null);
//            else
//                cursor = db.rawQuery("SELECT * FROM ITEM,DISTRICT,CITY WHERE ITEM.DISTRICTID=DISTRICT.ID AND"
//                        +" DISTRICT.CITYID=CITY.ID AND CITY.ID="+city_id+" AND CATEGORYID="+category_id+
//                        " AND TYPEID="+type_id+" LIMIT 32", null);
//        }
//
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Item item = new Item();
//            item.setId(cursor.getInt(0));
//            item.setAddress(cursor.getString(1));
//            item.setName(cursor.getString(2));
//            item.setTotal_Reviews(cursor.getInt(3));
//            item.setImg(cursor.getString(4));
//            item.setDistrict_id(cursor.getInt(5));
//            item.setAvgRating(cursor.getDouble(6));
//            item.setCategory_id(cursor.getInt(7));
//            item.setType_id(cursor.getInt(8));
//            item.setTotal_pictures(20 + (int)(Math.random() * 80));
//            item.setRestaurant_id(20 + (int)(Math.random() * 80));
//
//            ArrayList<Review> reviews=new ArrayList<>();
//            cursor_review = db.rawQuery("SELECT * FROM REVIEW WHERE ITEMID="+cursor.getInt(0)+" LIMIT 2", null);
//            cursor_review.moveToFirst();
//            while (!cursor_review.isAfterLast()){
//                Review review = new Review();
//                review.setId(cursor_review.getInt(0));
//                review.setName(cursor_review.getString(1));
//                review.setRating(cursor_review.getInt(2));
//                review.setComment(cursor_review.getString(3));
//                review.setComment_trim(cursor_review.getString(4));
//                review.setAvatar(cursor_review.getString(5));
//                review.setItem_id(cursor_review.getInt(6));
//                review.setReview_url(cursor_review.getString(7));
//                cursor_review.moveToNext();
//                reviews.add(review);
//            }
//            cursor_review.close();
//            item.setReviews(reviews);
//
//            cursor.moveToNext();
//            list.add(item);
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
    // Truy vấn trả về list review dựa vào id item
    public ArrayList<Review> getList_Reviews(int item_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_review;
        ArrayList<Review> reviews=new ArrayList<>();
        cursor_review = db.rawQuery("SELECT * FROM REVIEW WHERE ITEMID="+item_id+" LIMIT 2", null);
        cursor_review.moveToFirst();
        while (!cursor_review.isAfterLast()){
            Review review = new Review();
            review.setId(cursor_review.getInt(0));
            review.setName(cursor_review.getString(1));
            review.setRating(cursor_review.getInt(2));
            review.setComment(cursor_review.getString(3));
            review.setComment_trim(cursor_review.getString(4));
            review.setAvatar(cursor_review.getString(5));
            review.setItem_id(cursor_review.getInt(6));
            review.setReview_url(cursor_review.getString(7));
            cursor_review.moveToNext();
            reviews.add(review);
        }
        cursor_review.close();
        db.close();
        return reviews;
    }
// Lấy cơ sở dữ liệu từ Asset
    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = getDatabasePath();

        File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }
    // Lấy đường dẫn database
    private static String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }
    // Mở database
    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = context.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
