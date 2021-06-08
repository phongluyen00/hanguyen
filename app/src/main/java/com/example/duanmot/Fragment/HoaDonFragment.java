package com.example.duanmot.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Adapter.DonHangAdapter;
import com.example.duanmot.Database.DatabaseDonHang;
import com.example.duanmot.Database.DatabaseHoaDon;
import com.example.duanmot.Entity.DonHang;
import com.example.duanmot.Entity.HoaDon;
import com.example.duanmot.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HoaDonFragment extends Fragment {
    private  Button buttonThanhToan;
    private RecyclerView rcvHoaDon;
    private Context context;
    private List<DonHang> donHangList;
    private TextView mTextView;
    private Button mButton;
    private DatabaseDonHang databaseDonHang;
    private DatabaseHoaDon databaseHoaDon;
    double thanhtien = 0;



    public HoaDonFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hoa_don,container,false);
        rcvHoaDon=view.findViewById(R.id.rcvHoaDon);
        mTextView=view.findViewById(R.id.tvThanhTien);
        mButton=view.findViewById(R.id.btnThanhToan);


        databaseDonHang = DatabaseDonHang.getInstance(getContext());
        databaseHoaDon = DatabaseHoaDon.getInstance(getContext());

        donHangList=databaseDonHang.daoDonHang().DON_HANG_LIST();


        for (DonHang donHang : donHangList){
            thanhtien+=donHang.getThanhTien();
        }

        mTextView.setText(""+thanhtien+" VNĐ");

        DonHangAdapter donHangAdapter = new DonHangAdapter((ArrayList<DonHang>) donHangList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvHoaDon.setLayoutManager(linearLayoutManager);
        rcvHoaDon.setAdapter(donHangAdapter);
        donHangAdapter.setOnItemClickListener(new DonHangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int Position) {

            }

            @Override
            public void deleteItem(int Position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Bạn có muốn xóa ?");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DonHang donHang = donHangList.get(Position);
                        databaseDonHang.daoDonHang().Delete(donHang);
                        List<DonHang> donHangs = databaseDonHang.daoDonHang().DON_HANG_LIST();
                        DonHangAdapter donHangAdapter = new DonHangAdapter((ArrayList<DonHang>) donHangs,getContext());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        rcvHoaDon.setLayoutManager(linearLayoutManager);
                        rcvHoaDon.setAdapter(donHangAdapter);
                        for (DonHang donHang1 : donHangs){
                            thanhtien+=donHang1.getThanhTien();
                        }
                        mTextView.setText(""+thanhtien+" VNĐ");

                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                BottomSheetDialog dialogHoaDon = new BottomSheetDialog(getContext(),R.style.BottomSheetDialogTheme);
                dialogHoaDon.setContentView(R.layout.dialog_them_hoadon);
                dialogHoaDon.setCanceledOnTouchOutside(false);
                Button mButton = dialogHoaDon.findViewById(R.id.btnNgay);
                TextView mTextView = dialogHoaDon.findViewById(R.id.edtNgay);
                EditText mEditTextTenKH = dialogHoaDon.findViewById(R.id.edtTenKh);
                EditText mEditTextDC = dialogHoaDon.findViewById(R.id.edtDiaChi);
                EditText mEditText = dialogHoaDon.findViewById(R.id.edtSdtKh);
                Button mButtonThanhToan = dialogHoaDon.findViewById(R.id.btnThanhToandal);
                Button btnShowMonHoc = dialogHoaDon.findViewById(R.id.btnShowMonHoc);
                Button mButtonHuy = dialogHoaDon.findViewById(R.id.btnShowMonHoc);
                TextView mTextViewTOngTien = dialogHoaDon.findViewById(R.id.tvTongTien);
                mTextViewTOngTien.setText("Tổng Tiền : "+thanhtien);
                String ngay = null;
                dialogHoaDon.show();
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
                        datePickerDialog.show();
                        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int thang = month+1;
                                mTextView.setText(dayOfMonth+"-"+thang+"-"+year);

                            }
                        });
                    }
                });
                btnShowMonHoc.setOnClickListener(v1 -> dialogHoaDon.dismiss());
                mButtonThanhToan.setOnClickListener(v12 -> {

                    if(thanhtien == 0){
                        dialogHoaDon.dismiss();
                        Toast.makeText(getContext(), "Không thể thanh toán !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setNgayLap(ngay);
                    hoaDon.setDiaChi(mEditTextDC.getText().toString());
                    hoaDon.setTenKhachHang(mEditTextTenKH.getText().toString());
                    hoaDon.setSoDienThoaiKh(mEditText.getText().toString());
                    hoaDon.setTongTien(thanhtien);

                    try {
                        databaseHoaDon.daoHoaDon().InsertHoaDon(hoaDon);

                        Toast.makeText(getContext(), "Thành công ! ", Toast.LENGTH_SHORT).show();
                        databaseDonHang.daoDonHang().DeleteTable();
                        List<DonHang> donHangs = databaseDonHang.daoDonHang().DON_HANG_LIST();
                        DonHangAdapter donHangAdapter1 = new DonHangAdapter((ArrayList<DonHang>) donHangs,getContext());
                        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                        rcvHoaDon.setLayoutManager(linearLayoutManager1);
                        rcvHoaDon.setAdapter(donHangAdapter1);
                        dialogHoaDon.dismiss();
                    }catch (Exception e){
                        Log.e("ERRO",""+e);
                    }


                });



            }
        });
        return view;
    }
}