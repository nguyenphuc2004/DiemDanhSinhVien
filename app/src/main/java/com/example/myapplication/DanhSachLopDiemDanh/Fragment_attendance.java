package com.example.myapplication.DanhSachLopDiemDanh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.DanhSachLop.ListClass;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;

public class Fragment_attendance extends Fragment {

    SQLiteDatabase sqLiteDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        ListView listView = view.findViewById(R.id.listViewClass);

        // Tạo ArrayList để lưu danh sách lớp và HashSet để loại bỏ trùng lặp
        ArrayList<ListClass> arrayList = new ArrayList<>();
        HashSet<String> uniqueClasses = new HashSet<>();

        // Mở cơ sở dữ liệu
        sqLiteDatabase = getActivity().openOrCreateDatabase("qlsv.db", getContext().MODE_PRIVATE, null);

        // Truy vấn để lấy các lớp từ bảng SinhVienDanhSach
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT lopHoc FROM SinhVienDanhSach", null);
        if (cursor.moveToFirst()) {
            do {
                String lopHoc = cursor.getString(0); // Cột 0 là tên lớp học

                // Kiểm tra xem lớp đã tồn tại trong HashSet chưa
                if (uniqueClasses.add(lopHoc)) {
                    arrayList.add(new ListClass(lopHoc)); // Thêm lớp vào arrayList nếu chưa có
                }
            } while (cursor.moveToNext());
        }
        // Đóng cursor sau khi truy vấn xong
        cursor.close();

        // Gán adapter cho ListView
        FragmentAttendanceAdapter adapter = new FragmentAttendanceAdapter(getActivity(), R.layout.items_class, arrayList);
        listView.setAdapter(adapter);
        return view;
    }
}