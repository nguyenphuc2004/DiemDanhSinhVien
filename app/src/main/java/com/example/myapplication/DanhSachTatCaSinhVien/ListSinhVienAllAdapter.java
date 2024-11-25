package com.example.myapplication.DanhSachTatCaSinhVien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListSinhVienAllAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<SinhVien> arrayListSinhVien;

    public ListSinhVienAllAdapter(Context context, int layout, List<SinhVien> arrayListSinhVien) {
        this.context = context;
        this.layout = layout;
        this.arrayListSinhVien = arrayListSinhVien;
    }

    @Override
    public int getCount() {
        return arrayListSinhVien.size();
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

        TextView textViewMssv = view.findViewById(R.id.textViewMSSVAll);
        TextView textViewHoVaTen = view.findViewById(R.id.textViewHoVaTenAll);
        TextView textViewLop = view.findViewById(R.id.textViewTenLopAll);
        TextView textViewGioiTinh = view.findViewById(R.id.textViewGioiTinhAll);
        TextView textViewNamSinh = view.findViewById(R.id.textViewNamSinhAll);
        TextView textViewsdt = view.findViewById(R.id.textViewSDTAll);
        TextView textViewGmai = view.findViewById(R.id.textViewGmailAll);


        textViewMssv.setText(String.valueOf(arrayListSinhVien.get(i).getMssv()));
        textViewHoVaTen.setText(arrayListSinhVien.get(i).getHoVaTen());
        textViewLop.setText(arrayListSinhVien.get(i).getLopHoc());
        textViewGioiTinh.setText(arrayListSinhVien.get(i).getGioiTinh());
        textViewNamSinh.setText(arrayListSinhVien.get(i).getNamSinh());
        textViewsdt.setText(arrayListSinhVien.get(i).getSoDienThoai());
        textViewGmai.setText(String.valueOf(arrayListSinhVien.get(i).getGmail()));


        return view;
    }
}
