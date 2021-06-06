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

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ten = edtTen.getText().toString();
                final String mk = edtMk.getText().toString();
                final String mk2 = edtMk2.getText().toString();

                if (ten.isEmpty() || mk.isEmpty() || mk2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                if (!mk.equals(mk2)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
                DatabaseTaiKhoan databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getApplicationContext());
                List<TaiKhoan> taiKhoanList = databaseTaiKhoan.daoTaiKhoan().TAI_KHOAN_LIST();

                for (TaiKhoan taiKhoan : taiKhoanList) {
                    if (ten.equals(taiKhoan.getTenTaiKhoan())) {
                        taiKhoan.setMatKhau(edtMk.getText().toString());
                        databaseTaiKhoan.daoTaiKhoan().UpdateTaiKhoan(taiKhoan);
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Không tồn tại tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}