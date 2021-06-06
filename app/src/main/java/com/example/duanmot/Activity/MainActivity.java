package com.example.duanmot.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.duanmot.Entity.HoaDon;
import com.example.duanmot.R;

import maes.tech.intentanim.CustomIntent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
                CustomIntent.customType(MainActivity.this,"bottom-to-up");
                finish();
            }
        }, 1800);
    }
}




