package com.example.duanmot.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;

public class DangKyActivity extends AppCompatActivity {
    Button btnDangKy2;
    EditText edtTenDn, edtNhapMk, edtSdt, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnDangKy2 = findViewById(R.id.btnDangKy2);
        edtTenDn = findViewById(R.id.edtTenDn);
        edtNhapMk = findViewById(R.id.edtNhapMk);
        edtSdt = findViewById(R.id.edtSdt);
        edtEmail = findViewById(R.id.edtEmail);
        //sukien
        btnDangKy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setTenTaiKhoan(edtTenDn.getText().toString());
                taiKhoan.setMatKhau(edtNhapMk.getText().toString());
                taiKhoan.setSoDienThoai(edtSdt.getText().toString());
                taiKhoan.seteMail(edtEmail.getText().toString());
                if (validateInput(taiKhoan)) {
                    //insert
                    DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getApplicationContext());
                    databaseTaiKhoan.daoTaiKhoan().InsertTaiKhoan(taiKhoan);
                    Toast.makeText(getApplicationContext(), "tao thanh cong", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(getApplicationContext(), "khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // check rong roi tra ve true false
    private boolean validateInput(TaiKhoan taiKhoan) {
        return !taiKhoan.getTenTaiKhoan().isEmpty() && !taiKhoan.getMatKhau().isEmpty();
    }
}