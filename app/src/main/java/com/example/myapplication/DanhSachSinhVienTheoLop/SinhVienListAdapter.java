package com.example.myapplication.DanhSachSinhVienTheoLop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DanhSachTatCaSinhVien.SinhVien;
import com.example.myapplication.MainSinhVienDetail;
import com.example.myapplication.R;

import java.util.List;

public class SinhVienListAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<SinhVien> arrayListSinhVien;

    public SinhVienListAdapter(Context context, int layout, List<SinhVien> arrayListSinhVien) {
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

        TextView textViewMssv = view.findViewById(R.id.textViewMSSV);
        TextView textViewHoVaTen = view.findViewById(R.id.textViewHoVaTen);

        textViewMssv.setText(String.valueOf(arrayListSinhVien.get(i).getMssv()));
        textViewHoVaTen.setText(arrayListSinhVien.get(i).getHoVaTen());

        // Thêm OnClickListener cho toàn bộ item
        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainSinhVienDetail.class);
            SinhVien sinhVien = arrayListSinhVien.get(i);

            // Gửi dữ liệu qua Intent
            intent.putExtra("MSSV", sinhVien.getMssv());
            intent.putExtra("HoVaTen", sinhVien.getHoVaTen());
            intent.putExtra("SDT", sinhVien.getSoDienThoai());
            intent.putExtra("GioiTinh", sinhVien.getGioiTinh());
            intent.putExtra("NamSinh", sinhVien.getNamSinh());
            intent.putExtra("Gmail", sinhVien.getGmail());
            intent.putExtra("TenLop", sinhVien.getLopHoc());

            context.startActivity(intent);
        });


        return view;
    }
}
