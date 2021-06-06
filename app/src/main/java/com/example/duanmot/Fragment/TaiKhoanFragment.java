package com.example.duanmot.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Activity.DangNhapActivity;
import com.example.duanmot.Activity.GioiThieuActivity;
import com.example.duanmot.Adapter.TaiKhoanAdapter;
import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class TaiKhoanFragment extends Fragment {
    private Context context;
    TextView tvDangXuat, tvThoat, tvGioiThieu, tvTenDn, tvEmail;
    DatabaseTaiKhoan databaseTaiKhoan;
    TaiKhoan taiKhoan;
    TaiKhoanAdapter taiKhoanAdapter;
    ArrayList<TaiKhoan> taiKhoans;
    RecyclerView recyclerView;

    public TaiKhoanFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        tvDangXuat = view.findViewById(R.id.tvDangXuat);
        tvThoat = view.findViewById(R.id.tvThoat);
        tvGioiThieu = view.findViewById(R.id.tvGioiThieu);
        recyclerView = view.findViewById(R.id.rcvTaiKhoan);
        //database
        databaseTaiKhoan = DatabaseTaiKhoan.getInstance(getContext());
        //set data vao list tk
        taiKhoans = (ArrayList<TaiKhoan>) databaseTaiKhoan.daoTaiKhoan().TAI_KHOAN_LIST();
        taiKhoanAdapter = new TaiKhoanAdapter(taiKhoans, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(taiKhoanAdapter);
        recyclerView.setLayoutManager(layoutManager);
        taiKhoanAdapter.setOnItemClickListener(new TaiKhoanAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int Position) {
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.dialog_tai_khoan, null);
                TextView tvTenDn = view1.findViewById(R.id.tvTenDn);
                TextView tvSdt = view1.findViewById(R.id.tvSdt);
                TextView tvEmail = view1.findViewById(R.id.tvEmail);
                TextView tvMk = view1.findViewById(R.id.tvMatKhau);
                Button btnDoiMk = view1.findViewById(R.id.btnDoiMk);
                TaiKhoan taiKhoan = taiKhoans.get(Position);
                tvTenDn.setText("Tên: " + taiKhoan.getTenTaiKhoan());
                tvSdt.setText("SDT: " + taiKhoan.getSoDienThoai());
                tvEmail.setText("Email: " + taiKhoan.geteMail());
                tvMk.setText("Mật khẩu: " + taiKhoan.getMatKhau());
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setView(view1);
                AlertDialog dialog = alert.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                btnDoiMk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        View view2 = inflater1.inflate(R.layout.dialog_doi_mk, null);
                        EditText edtMkMoi = view2.findViewById(R.id.edtNewPass1);
                        EditText edtMkCofirm = view2.findViewById(R.id.edtConfirmPass1);
                        Button btnOk = view2.findViewById(R.id.btnOk);
                        alert.setView(view2);
                        AlertDialog dialog2 = alert.create();
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog2.show();
                        btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                taiKhoan.setMatKhau(edtMkCofirm.getText().toString());
                                databaseTaiKhoan.daoTaiKhoan().UpdateTaiKhoan(taiKhoan);
                                Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                dialog2.cancel();
                            }
                        });

                    }
                });
            }

            @Override
            public void deleteItem(int Position) {

            }
        });
        //su kien
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        tvThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
        tvGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GioiThieuActivity.class);
                CustomIntent.customType(getContext(),"left-to-right");
                startActivity(intent);
            }
        });
        return view;
    }

    //logout
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không ?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), DangNhapActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //thoat
    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có muốn đóng ứng dụng không ?")
                .setCancelable(false)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}