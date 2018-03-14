package com.example.xuant.a14110208_foody.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xuant.a14110208_foody.R;
import com.google.android.gms.maps.SupportMapFragment;

import static com.example.xuant.a14110208_foody.R.layout.add_location_map;

/**
 * Created by xuant on 12/05/2017.
 */

public class AddLocation_MapMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(add_location_map);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new SupportMapFragment()).commit();
    }
}
