package com.example.duanmot.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Adapter.LoaiAdapter;
import com.example.duanmot.Adapter.SanPhamAdapter;
import com.example.duanmot.Database.DatabaseDonHang;
import com.example.duanmot.Database.DatabaseLoaiSP;
import com.example.duanmot.Database.DatabaseSanPham;
import com.example.duanmot.Entity.DonHang;
import com.example.duanmot.Entity.LoaiSanPham;
import com.example.duanmot.Entity.SanPham;
import com.example.duanmot.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SanPhamFragment extends Fragment {

    private FloatingActionButton flThem;
    private Context context;
    private RecyclerView mRecyclerViewSanpham;
    private RecyclerView mRecyclerViewLoaiSP;
    private DatabaseSanPham databaseSanPham;
    private DatabaseLoaiSP databaseLoaiSP;
    private DatabaseDonHang databaseDonHang;
    private EditText mEditTextTimKiem;
    private ArrayList<SanPham> sanPhams;
    private SanPhamAdapter aqAdapter;
    private List<LoaiSanPham> arrayListLoai;
    private List<String> arrayListTenLoaiSP = new ArrayList<>();

    public SanPhamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        flThem = view.findViewById(R.id.flThemSp);
        mRecyclerViewSanpham = view.findViewById(R.id.rcSanPham);
        mRecyclerViewLoaiSP = view.findViewById(R.id.rcLoai);
        mEditTextTimKiem = view.findViewById(R.id.edtTimKiem);


        //database
        databaseSanPham = DatabaseSanPham.getInstance(getContext());
        databaseLoaiSP = DatabaseLoaiSP.getInstance(getContext());
        databaseDonHang = DatabaseDonHang.getInstance(getContext());
        //database

        arrayListLoai = databaseLoaiSP.daoLoaiSanPham().loaiSanPham_LIST();
        // get ten loai san pham
        for (LoaiSanPham loaiSanPham : arrayListLoai) {
            arrayListTenLoaiSP.add(loaiSanPham.getLoaiSanPham());
        }
        LoaiAdapter dataAdapter = new LoaiAdapter(arrayListLoai, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewLoaiSP.setLayoutManager(linearLayoutManager);
        mRecyclerViewLoaiSP.setAdapter(dataAdapter);

        // tim kiem tren recy san pham
        mEditTextTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                TimKiem(s.toString());
            }
        });
        //set list sản phẩm
        sanPhams = (ArrayList<SanPham>) databaseSanPham.daoSanPham().SANPHAM_LIST();
        aqAdapter = new SanPhamAdapter(sanPhams, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewSanpham.setAdapter(aqAdapter);
        mRecyclerViewSanpham.setLayoutManager(layoutManager);
        aqAdapter.setOnItemClickListener(new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int Position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Bạn muốn ?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SanPham sanPham = sanPhams.get(Position);
                        databaseSanPham.daoSanPham().DeleteSanPham(sanPham);
                        ArrayList<SanPham> sanPhams = (ArrayList<SanPham>) databaseSanPham.daoSanPham().SANPHAM_LIST();
                        SanPhamAdapter aqAdapter = new SanPhamAdapter(sanPhams, getContext());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        mRecyclerViewSanpham.setAdapter(aqAdapter);
                        Toast.makeText(context, "Xóa thành công ", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BottomSheetDialog sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
                        sheetDialog.setContentView(R.layout.dialog_sua_sp);
                        sheetDialog.setCanceledOnTouchOutside(false);
                        sheetDialog.show();
                        SanPham sanPham = sanPhams.get(Position);
                        //
                        EditText editTextTenSp = sheetDialog.findViewById(R.id.edtTenSpS);
                        Spinner spinnerLoaiSP = sheetDialog.findViewById(R.id.spLoaiS);
                        EditText editTextGiaSP = sheetDialog.findViewById(R.id.edtGiaSpS);
                        EditText editTextSoLuong = sheetDialog.findViewById(R.id.edtSoluongS);
                        Button buttonThemSP = sheetDialog.findViewById(R.id.btnThemSPS);
                        //
                        editTextTenSp.setText(sanPham.getTenSanPham());
                        editTextGiaSP.setText(String.valueOf(sanPham.getGiaSanPham()));
                        editTextSoLuong.setText(String.valueOf(sanPham.getSoLuongSanPham()));
                        //
                        ArrayAdapter dataAdapter = new ArrayAdapter(getContext(),
                                R.layout.support_simple_spinner_dropdown_item, arrayListTenLoaiSP);

                        spinnerLoaiSP.setAdapter(dataAdapter);

                        buttonThemSP.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sanPham.setTenSanPham(editTextTenSp.getText().toString());
                                sanPham.setLoaiSanPham(spinnerLoaiSP.getSelectedItem().toString());
                                sanPham.setGiaSanPham(Double.parseDouble(editTextGiaSP.getText().toString()));
                                sanPham.setSoLuongSanPham(Integer.parseInt(editTextSoLuong.getText().toString()));
                                try {
                                    databaseSanPham.daoSanPham().UpdataSanpham(sanPham);
                                    sanPhams = (ArrayList<SanPham>) databaseSanPham.daoSanPham().SANPHAM_LIST();
                                    aqAdapter = new SanPhamAdapter(sanPhams, getContext());
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                    mRecyclerViewSanpham.setAdapter(aqAdapter);
                                    mRecyclerViewSanpham.setLayoutManager(layoutManager);
                                    sheetDialog.dismiss();
                                    Toast.makeText(getContext(), "Sửa thành công ! ", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Log.e("ERRO", "" + e);
                                }
                            }
                        });
                    }
                });
                builder.show();
            }

            @Override
            public void deleteItem(int Position) {
                SanPham sanPham = sanPhams.get(Position);
                if (sanPham.getSoLuongSanPham() == 0) {
                    Toast.makeText(getContext(), "Hết hàng ! ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.item_soluong);
                Button mButton = dialog.findViewById(R.id.btnThemSPHoaDon);
                EditText mEditText = dialog.findViewById(R.id.edtSoLuongSP);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int sl = Integer.parseInt(mEditText.getText().toString());
                        int sl1 = sanPham.getSoLuongSanPham();
                        if (sl <= sl1) {
                            String Ten = sanPham.getTenSanPham();
                            int slmh = sanPham.getSoLuongSanPham();
                            double dongia = sanPham.getGiaSanPham();
                            double thanhtien = sl * dongia;
                            DonHang donHang = new DonHang(Ten, sl, thanhtien);
                            try {
                                databaseDonHang.daoDonHang().InsertDonHang(donHang);
                                Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } catch (Exception e) {
                                Log.e("ERRO", "" + e);
                            }
                            try {
                                int soLuongConLai = sl1 - sl;
                                sanPham.setSoLuongSanPham(soLuongConLai);
                                databaseSanPham.daoSanPham().UpdataSanpham(sanPham);
                                sanPhams = (ArrayList<SanPham>) databaseSanPham.daoSanPham().SANPHAM_LIST();
                                aqAdapter = new SanPhamAdapter(sanPhams, getContext());
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                mRecyclerViewSanpham.setAdapter(aqAdapter);
                                mRecyclerViewSanpham.setLayoutManager(layoutManager);

                            } catch (Exception e) {
                                Log.e("ERRO", "" + e);
                            }
                        } else {
                            mEditText.setError("Chỉ còn " + sl + " sản phẩm");
                            return;
                        }
                    }
                });
                dialog.show();
            }
        });
        //sukien
        flThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Bạn muốn thêm ? ");
                builder.setNegativeButton("Sản Phẩm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BottomSheetDialog sheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
                        sheetDialog.setContentView(R.layout.dialog_them_sp);
                        sheetDialog.setCanceledOnTouchOutside(false);
                        //
                        EditText editTextTenSp = sheetDialog.findViewById(R.id.edtTenSp);
                        Spinner spinnerLoaiSP = sheetDialog.findViewById(R.id.spLoai);
                        EditText editTextGiaSP = sheetDialog.findViewById(R.id.edtGiaSp);
                        EditText editTextSoLuong = sheetDialog.findViewById(R.id.edtSoluong);
                        Button buttonThemSP = sheetDialog.findViewById(R.id.btnThemSP);
                        //
                        databaseLoaiSP = DatabaseLoaiSP.getInstance(getContext());
                        List<LoaiSanPham> loaiSanPhams = (ArrayList<LoaiSanPham>)
                                databaseLoaiSP.daoLoaiSanPham().loaiSanPham_LIST();
                        List<String> stringList = new ArrayList<>();

                        for (LoaiSanPham loaiSanPham : loaiSanPhams) {
                            stringList.add(loaiSanPham.getLoaiSanPham());
                        }

                        ArrayAdapter dataAdapter = new ArrayAdapter(getContext(),
                                R.layout.support_simple_spinner_dropdown_item, stringList);

                        spinnerLoaiSP.setAdapter(dataAdapter);

                        buttonThemSP.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String tenSP = editTextTenSp.getText().toString();
                                String giaSp = String.valueOf(editTextGiaSP.getText());
                                String soLuongSP = String.valueOf(editTextSoLuong.getText());
                                //Get item đưuọc chọn từ spinner
                                String loai = spinnerLoaiSP.getSelectedItem().toString();
                                if (tenSP.isEmpty()) {
                                    editTextTenSp.setError("Nhập tên sản phẩm");
                                    return;
                                }
                                if (giaSp.isEmpty()) {
                                    editTextGiaSP.setError("Nhập giá sản phẩm");
                                    return;
                                }
                                if (soLuongSP.isEmpty()) {
                                    editTextSoLuong.setError("Nhập số lượng sản phẩm");
                                    return;
                                }

                                try {
                                    SanPham sanPham = new SanPham(tenSP, loai, Double.parseDouble(giaSp), Integer.parseInt(soLuongSP));
                                    DatabaseSanPham databaseSanPham = DatabaseSanPham.getInstance(getContext());
                                    databaseSanPham.daoSanPham().InsertSanPham(sanPham);
                                    Toast.makeText(context, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                                    ArrayList<SanPham> sanPhams = (ArrayList<SanPham>)
                                            databaseSanPham.daoSanPham().SANPHAM_LIST();
                                    SanPhamAdapter aqAdapter = new SanPhamAdapter(sanPhams, getContext());
                                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                                    mRecyclerViewSanpham.setAdapter(aqAdapter);
                                    mRecyclerViewSanpham.setLayoutManager(layoutManager);
                                    sheetDialog.dismiss();
                                } catch (Exception e) {
                                    Log.e("Error", "" + e);
                                    Toast.makeText(requireActivity(), "Co loi xay ra", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        sheetDialog.show();
                    }
                });

                builder.setPositiveButton("Loại Sản Phẩm ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Loại Sản Phẩm", Toast.LENGTH_SHORT).show();
                        BottomSheetDialog sheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);
                        sheetDialog.setContentView(R.layout.dialog_them_loai);
                        sheetDialog.setCanceledOnTouchOutside(false);
                        EditText mEditText = sheetDialog.findViewById(R.id.edtLoaiSp);
                        Button mButtonThem = sheetDialog.findViewById(R.id.btnThemLoai);
                        Button mButtonHuy = sheetDialog.findViewById(R.id.btnHuyLSP);
                        mButtonThem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String typeStr = mEditText.getText().toString();
                                if (typeStr.isEmpty()) {
                                    Toast.makeText(requireActivity(), "Nhap day du thong tin", Toast.LENGTH_SHORT).show();
                                } else {
                                    LoaiSanPham loaiSanPham = new LoaiSanPham(typeStr);
                                    try {
                                        DatabaseLoaiSP databaseLoaiSP = DatabaseLoaiSP.getInstance(getContext());
                                        databaseLoaiSP.daoLoaiSanPham().InsertLoaiSanpham(loaiSanPham);
                                        List<LoaiSanPham> loaiSanPhams = databaseLoaiSP.daoLoaiSanPham().loaiSanPham_LIST();
                                        LoaiAdapter dataAdapter = new LoaiAdapter(loaiSanPhams, getContext());
                                        mRecyclerViewLoaiSP.setAdapter(dataAdapter);
                                        sheetDialog.dismiss();
                                        Toast.makeText(context, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                                        databaseLoaiSP.daoLoaiSanPham().loaiSanPham_LIST();
                                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                                                LinearLayoutManager.HORIZONTAL, false);
                                        mRecyclerViewLoaiSP.setAdapter(dataAdapter);
                                        mRecyclerViewLoaiSP.setLayoutManager(layoutManager);
                                        sheetDialog.dismiss();

                                    } catch (Exception e) {
                                        Log.e("ERRO", "" + e);
                                    }
                                }
                            }
                        });
                        mButtonHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sheetDialog.dismiss();
                            }
                        });
                        sheetDialog.show();
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    private void TimKiem(String text) {
        ArrayList<SanPham> sanPhamList = new ArrayList<>();
        for (SanPham sanPham : sanPhams) {
            if (sanPham.getTenSanPham().toLowerCase().contains(text.toLowerCase())) {
                sanPhamList.add(sanPham);
            }
        }
        aqAdapter.TimKiemList(sanPhamList);
    }


}