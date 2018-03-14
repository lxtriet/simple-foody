package com.example.xuant.a14110208_foody.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.xuant.a14110208_foody.MainActivity;
import com.example.xuant.a14110208_foody.R;

/**
 * Created by xuant on 06/05/2017.
 */

public class ScreenActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        progressBar = (ProgressBar) findViewById(R.id.pg_flash_screen);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.MULTIPLY);
        //tạo 1 luồng thread
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(4000);
                }catch (Exception e){

                }finally{
                    Intent intent=new Intent(ScreenActivity.this,MainActivity.class);
                    startActivity(intent); finish();
                }
            }
        });
        thread.start();
    }
}
