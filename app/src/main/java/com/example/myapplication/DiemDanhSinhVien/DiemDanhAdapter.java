package com.example.myapplication.DiemDanhSinhVien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DanhSachLopDiemDanh.Attendance;
import com.example.myapplication.R;

import java.util.List;

public class DiemDanhAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Attendance> arrayListLishSuDiemDanh;

    public DiemDanhAdapter(Context context, int layout, List<Attendance> arrayListLishSuDiemDanh) {
        this.context = context;
        this.layout = layout;
        this.arrayListLishSuDiemDanh = arrayListLishSuDiemDanh;
    }

    @Override
    public int getCount() {
        return arrayListLishSuDiemDanh.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        TextView textViewId = view.findViewById(R.id.textView_id);
        TextView textViewMssv = view.findViewById(R.id.textView_mssv);
        TextView textViewHoVaTen = view.findViewById(R.id.textView_hoVaTen);
        TextView textViewNgayDiemDanh = view.findViewById(R.id.textView_ngayDiemDanh);
        TextView textViewTrangThai = view.findViewById(R.id.textView_trangThai);
        TextView textViewLopHoc = view.findViewById(R.id.textView_lopHoc);


        textViewId.setText(String.valueOf(arrayListLishSuDiemDanh.get(i).getId()));
        textViewMssv.setText(String.valueOf(arrayListLishSuDiemDanh.get(i).getMssv()));
        textViewHoVaTen.setText(arrayListLishSuDiemDanh.get(i).getHoTen());
        textViewNgayDiemDanh.setText(arrayListLishSuDiemDanh.get(i).getAttendanceDate());
        textViewTrangThai.setText(arrayListLishSuDiemDanh.get(i).getStatus());
        textViewLopHoc.setText(arrayListLishSuDiemDanh.get(i).getLopHoc());

        return view;
    }
}
