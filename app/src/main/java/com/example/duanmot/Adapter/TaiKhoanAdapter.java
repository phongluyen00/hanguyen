package com.example.duanmot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.Entity.TaiKhoan;
import com.example.duanmot.R;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolder> {
    ArrayList<TaiKhoan> taiKhoans;
    Context context;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int Position);

        void deleteItem(int Position);
    }
    public void setOnItemClickListener(OnItemClickListener clickListener1) {
        mListener = clickListener1;
    }
    public TaiKhoanAdapter(List<TaiKhoan> taiKhoans, Context context) {
        this.taiKhoans = (ArrayList<TaiKhoan>) taiKhoans;
        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View Holder = layoutInflater.inflate(R.layout.item_tk, parent, false);
        ViewHolder viewHolder = new ViewHolder(Holder, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaiKhoan taiKhoan = taiKhoans.get(position);
        holder.tvTenDn.setText(taiKhoan.getTenTaiKhoan());
        holder.tvEmail.setText(taiKhoan.geteMail());
        holder.tvSdt.setText(taiKhoan.getSoDienThoai());
    }

    @Override
    public int getItemCount() {
        return taiKhoans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvTenDn, tvEmail, tvSdt;

        public ViewHolder(@NonNull View itemView, OnItemClickListener mListener) {
            super(itemView);
            view=itemView;
            tvTenDn = itemView.findViewById(R.id.tvTenDn);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvSdt = itemView.findViewById(R.id.tvSdt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }

            });
        }
    }


}
