package com.example.duanmot.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Entity.SanPham;
import com.example.duanmot.R;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    ArrayList<SanPham> sanPhamArrayList;
    Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int Position);

        void deleteItem(int Position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mListener = clickListener;
    }

    public SanPhamAdapter(ArrayList<SanPham> sanPhamArrayList, Context context) {
        this.sanPhamArrayList = sanPhamArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View Holder = inflater.inflate(R.layout.item_sp, parent, false);
        return new ViewHolder(Holder, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = sanPhamArrayList.get(position);
        holder.mTextViewTenSP.setText("Tên : " + sanPham.getTenSanPham());
        holder.mTextViewGiaSP.setText("Giá : " + sanPham.getGiaSanPham() + " VNĐ");
        holder.mTextViewSoLuongSP.setText("Số Lượng : " + sanPham.getSoLuongSanPham() + " Cái");
        holder.mTextViewLoaiSP.setText("Loại : " + sanPham.getLoaiSanPham());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView mImageView;
        TextView mTextViewTenSP;
        TextView mTextViewLoaiSP;
        TextView mTextViewGiaSP;
        TextView mTextViewSoLuongSP;
        ImageView nImageViewAdd;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            view = itemView;
            mImageView = itemView.findViewById(R.id.imvSP);
            mTextViewTenSP = itemView.findViewById(R.id.tvTenSP);
            mTextViewLoaiSP = itemView.findViewById(R.id.tvLoaiSP);
            mTextViewGiaSP = itemView.findViewById(R.id.tvGiaSP);
            mTextViewSoLuongSP = itemView.findViewById(R.id.tvSoLuongSP);
            nImageViewAdd = itemView.findViewById(R.id.imvAddSP);
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
        return sanPhamArrayList.size();
    }

    public void TimKiemList(ArrayList<SanPham> foodies) {
        sanPhamArrayList = foodies;
        notifyDataSetChanged();
    }

}
