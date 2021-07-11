package com.example.duanmot.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;

import java.util.List;

public class QmkActivity extends AppCompatActivity {
    Button btnCheck;
    EditText edtTen, edtMk, edtMk2;
    DatabaseTaiKhoan databaseTaiKhoan;
    RecyclerView recyclerView;
    private String ten,mk,mk2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmk);
        btnCheck = findViewById(R.id.btnOk);
        edtTen = findViewById(R.id.edtEmail);
        edtMk = findViewById(R.id.edtNewPass);
        edtMk2 = findViewById(R.id.edtConfirmPass);
        recyclerView = findViewById(R.id.rcvTaiKhoan);
        databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getApplicationContext());

        btnCheck.setOnClickListener(v -> {
            ten = edtTen.getText().toString();
            mk = edtMk.getText().toString();
            mk2 = edtMk2.getText().toString();
            if (ten.isEmpty() || mk.isEmpty() || mk2.isEmpty()) {
                Toast.makeText(QmkActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!mk.equals(mk2)) {
                Toast.makeText(QmkActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }
            DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getApplicationContext());
            TaiKhoan taiKhoan = databaseTaiKhoan.daoTaiKhoan().checkAccountEmpty(ten);
            if (taiKhoan != null){
                taiKhoan.setMatKhau(edtMk.getText().toString());
                databaseTaiKhoan.daoTaiKhoan().UpdateTaiKhoan(taiKhoan);
                Toast.makeText(QmkActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(QmkActivity.this, "Không tồn tại tài khoản", Toast.LENGTH_SHORT).show();
            }
        });
    }
}