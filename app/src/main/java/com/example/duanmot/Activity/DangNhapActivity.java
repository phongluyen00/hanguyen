package com.example.duanmot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmot.DAO.DAOTaiKhoan;
import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;
import com.google.android.material.snackbar.Snackbar;

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
        btnDangNhap.setOnClickListener(v -> {
            hideKeyboard(this);
            final String tenDn = edtTenDn.getText().toString();
            final String matKhau1 = edtMatKhau.getText().toString();
            if (tenDn.isEmpty() || matKhau1.isEmpty()) {
                Snackbar.make(v, "Sai Mat Khau Hoac Ten Dang Nhap", Snackbar.LENGTH_LONG).show();
            } else {
                DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getApplicationContext());
                DAOTaiKhoan daoTaiKhoan = databaseTaiKhoan.daoTaiKhoan();
                TaiKhoan taiKhoan = daoTaiKhoan.dangnhap(tenDn, matKhau1);
                if (taiKhoan == null) {
                    Snackbar.make(v, "Tài khoản không tồn tại", Snackbar.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(DangNhapActivity.this, HomeActivity.class);
                    startActivity(intent);
                    CustomIntent.customType(DangNhapActivity.this, "right-to-left");
                    finish();
                    Snackbar.make(v, "Đăng nhập thành công ...", Snackbar.LENGTH_LONG).show();
                    if (remember.isChecked()) {
                        editor.putString("ten", edtTenDn.getText().toString());
                        editor.putString("mk", edtMatKhau.getText().toString());
                    } else {
                        editor.putString("ten", "");
                        editor.putString("mk", "");
                    }
                    editor.apply();
                }
            }
        });
        btnDangKy.setOnClickListener(v -> {
            Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
            startActivity(intent);
            CustomIntent.customType(DangNhapActivity.this, "right-to-left");
        });
        tvQmk.setOnClickListener(v -> {
            Intent intent = new Intent(DangNhapActivity.this, QmkActivity.class);
            CustomIntent.customType(DangNhapActivity.this, "up-to-bottom");
            startActivity(intent);
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}