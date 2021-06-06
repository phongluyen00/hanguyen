package com.example.duanmot.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Entity.DonHang;
import com.example.duanmot.R;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    ArrayList<DonHang> donHangArrayList;
    Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int Position);

        void deleteItem(int Position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mListener = clickListener;
    }

    public DonHangAdapter(ArrayList<DonHang> donHangArrayList, Context context) {
        this.donHangArrayList = donHangArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View Holder = inflater.inflate(R.layout.item_hoadon, parent, false);
        ViewHolder viewHolder = new ViewHolder(Holder, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = donHangArrayList.get(position);
        holder.mTextViewTenSP.setText("Tên : " + donHang.getTenMatHang());
        holder.mTextViewGiaSP.setText("Giá : " + donHang.getThanhTien() + " VNĐ");
        holder.mTextViewSoLuongSP.setText("Số Lượng : " + donHang.getSoLuong() + " Cái");

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView mImageView;
        TextView mTextViewTenSP;
        TextView mTextViewGiaSP;
        TextView mTextViewSoLuongSP;
        ImageView nImageViewAdd;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            view = itemView;
            mImageView = itemView.findViewById(R.id.imgHoaDon);
            mTextViewTenSP = itemView.findViewById(R.id.tvTenSanPhamHoaDon);
            mTextViewSoLuongSP = itemView.findViewById(R.id.tvSoLuongHoaDon);
            mTextViewGiaSP = itemView.findViewById(R.id.tvGiaHoaDon);
            nImageViewAdd = itemView.findViewById(R.id.imvXoa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }

            });
            nImageViewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.deleteItem(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return donHangArrayList.size();
    }

//    public void TimKiemList(ArrayList<SanPham> foodies){
//        sanPhamArrayList= foodies;
//        notifyDataSetChanged();
//    }

}
