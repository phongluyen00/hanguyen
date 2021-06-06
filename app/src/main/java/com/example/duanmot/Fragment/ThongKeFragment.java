package com.example.duanmot.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Adapter.DonHangAdapter;
import com.example.duanmot.Adapter.SanPhamAdapter;
import com.example.duanmot.DAO.DAOHoaDon;
import com.example.duanmot.Database.DatabaseDonHang;
import com.example.duanmot.Database.DatabaseHoaDon;
import com.example.duanmot.Database.DatabaseSanPham;
import com.example.duanmot.Database.DatabaseTaiKhoan;
import com.example.duanmot.Entity.DonHang;
import com.example.duanmot.Entity.HoaDon;
import com.example.duanmot.Entity.SanPham;
import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class ThongKeFragment extends Fragment {
    TextView tvTongThuNhap, tvTongHangHoa, tvDonHangDuocBanRa;
    DonHangAdapter donHangAdapter;
    ArrayList<DonHang> donHangs;

    public ThongKeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
//        BarChart barChart = view.findViewById(R.id.barChart);
        tvTongThuNhap = view.findViewById(R.id.tvThongKheNgay);
        tvTongHangHoa = view.findViewById(R.id.tvThongKheThang);
        tvDonHangDuocBanRa = view.findViewById(R.id.tvThongKheNam);
        RecyclerView rcvSp = view.findViewById(R.id.rcvSp);
        DatabaseSanPham databaseSanPham = DatabaseSanPham.getInstance(getContext());
        List<SanPham> sanPhamList = databaseSanPham.daoSanPham().SANPHAM_LIST();
        DatabaseDonHang databaseDonHang = DatabaseDonHang.getInstance(getContext());
        List<DonHang> donHangList = databaseDonHang.daoDonHang().DON_HANG_LIST();
        DatabaseHoaDon databaseHoaDon = DatabaseHoaDon.getInstance(getContext());
        List<HoaDon> hoaDonList = databaseHoaDon.daoHoaDon().HOA_DON_LIST();
        double tien = 0;
        for (HoaDon hoaDon : hoaDonList) {
            tien = hoaDon.getTongTien();
            tvTongThuNhap.setText(tien + "VND");
        }
        int tongHang = 0;
        for (SanPham sanPham : sanPhamList) {
            tongHang = tongHang + sanPham.getSoLuongSanPham();
        }
        tvTongHangHoa.setText(tongHang + "");
        int donHangDuocBanRa = 0;
        for (DonHang donHang : donHangList) {
            donHangDuocBanRa = donHangDuocBanRa + donHang.getSoLuong();
        }
        tvDonHangDuocBanRa.setText(donHangDuocBanRa + "");
        //
        for (DonHang donHang : donHangList) {
            if (donHang.getThanhTien() > 0) {
                donHangs = (ArrayList<DonHang>) databaseDonHang.daoDonHang().DON_HANG_LIST();
                donHangAdapter = new DonHangAdapter(donHangs, getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                rcvSp.setAdapter(donHangAdapter);
                rcvSp.setLayoutManager(layoutManager);
            }
        }
        return view;
    }
}