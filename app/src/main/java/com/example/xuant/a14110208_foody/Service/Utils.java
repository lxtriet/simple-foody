package com.example.xuant.a14110208_foody.Service;

/**
 * Created by xuant on 13/05/2017.
 */

public class Utils {
    public static final String BASE_URL = "https://foody-trietv2.herokuapp.com/";
//    public static final String BASE_URL = "http://192.168.43.219:8000/";
    // đường dẫn tới webservice
    public static Service getService() {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
