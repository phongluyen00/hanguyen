package com.example.duanmot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Entity.LoaiSanPham;
import com.example.duanmot.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.ViewHolder> {
    ArrayList<LoaiSanPham> loaiSanPhamArrayList;
    Context context;
    private LoaiAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int Position);

        void deleteItem(int Position);
    }

    public void setOnItemClickListener(LoaiAdapter.OnItemClickListener clickListener1) {
        mListener = clickListener1;
    }

    public LoaiAdapter(List<LoaiSanPham> loaiSanPhamArrayList, Context context) {
        this.loaiSanPhamArrayList = (ArrayList<LoaiSanPham>) loaiSanPhamArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View Holder = inflater.inflate(R.layout.item_loai, parent, false);
        ViewHolder viewHolder = new ViewHolder(Holder, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiSanPham loaiSanPham = loaiSanPhamArrayList.get(position);
        holder.textView.setText(loaiSanPham.getLoaiSanPham());
    }

    @Override
    public int getItemCount() {
        return loaiSanPhamArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView textView;

        public ViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            view=itemView;
            textView = itemView.findViewById(R.id.tvLoai);
        }


    }
}
