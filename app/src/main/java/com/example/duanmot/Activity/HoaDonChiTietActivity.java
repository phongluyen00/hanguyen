package com.example.duanmot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.duanmot.R;

import com.example.duanmot.R;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edtTenSp, edtSoLuong, edtGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        edtTenSp = findViewById(R.id.edtTenSp);
        edtSoLuong = findViewById(R.id.edtSoluong);
        edtGia = findViewById(R.id.edtGia);
        String tenSp = getIntent().getExtras().getString("tenSp");
        double gia = getIntent().getExtras().getDouble("gia");
        int soLuong = getIntent().getExtras().getInt("soLuong");
        edtTenSp.setText(tenSp);
        edtGia.setText(Double.toString(gia));
        edtSoLuong.setText(Integer.toString(soLuong));
    }
}