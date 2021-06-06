package com.example.duanmot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmot.DAO.DAOTaiKhoan;
import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;

import maes.tech.intentanim.CustomIntent;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangNhap, btnDangKy;
    EditText edtTenDn, edtMatKhau;
    TextView tvQmk;
    CheckBox remember;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        // ánh xạ
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy1);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        edtTenDn = findViewById(R.id.edtTenDn);
        remember = findViewById(R.id.remember);
        tvQmk = findViewById(R.id.tvQuenMk);
        //luu mat tk mk theo key vao sharedPreferences
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String ten = sharedPreferences.getString("ten", "");
        String matKhau = sharedPreferences.getString("mk", "");
        edtTenDn.setText(ten);
        edtMatKhau.setText(matKhau);
        //sukien
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tenDn = edtTenDn.getText().toString();
                final String matKhau = edtMatKhau.getText().toString();
                if (tenDn.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Sai Mat Khau Hoac Ten Dang Nhap", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getApplicationContext());
                    DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(tenDn, matKhau);
                            if (remember.isChecked()) {
                                editor.putString("ten", edtTenDn.getText().toString());
                                editor.putString("mk", edtMatKhau.getText().toString());
                                editor.commit();
                            } else {
                                editor.putString("ten", "");
                                editor.putString("mk", "");
                                editor.commit();
                            }
                            if (taiKhoan == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "TTTTTT...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Intent intent = new Intent(DangNhapActivity.this, HomeActivity.class);
                                startActivity(intent);
                                CustomIntent.customType(DangNhapActivity.this, "right-to-left");
                                finish();
                            }
                        }

                        private void runOnUiThread(Runnable runnable) {
                        }
                    }).start();
                }
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
                CustomIntent.customType(DangNhapActivity.this, "right-to-left");
            }
        });
        tvQmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, QmkActivity.class);
                CustomIntent.customType(DangNhapActivity.this, "up-to-bottom");
                startActivity(intent);
            }
        });
    }
}