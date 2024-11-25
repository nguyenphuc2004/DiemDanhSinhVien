package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.myapplication.DanhSachLopDiemDanh.Attendance;
import com.example.myapplication.DiemDanhSinhVien.DiemDanhAdapter;

import java.util.ArrayList;


public class FragmentHistory extends Fragment {
    DiemDanhAdapter lichSuDiemDanhAdapter;
    ArrayList<Attendance> arrayList;
    ListView listViewHistory;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        listViewHistory = view.findViewById(R.id.listViewHistory);
        sqLiteDatabase = getActivity().openOrCreateDatabase("qlsv.db", getActivity().MODE_PRIVATE, null);

        arrayList = new ArrayList<>();
        lichSuDiemDanhAdapter = new DiemDanhAdapter(getContext(), R.layout.items_lichsudiemdanh, arrayList);
        listViewHistory.setAdapter(lichSuDiemDanhAdapter);

        arrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DiemDanhSinhVien", null);
        cursor.moveToFirst();  // Chuyển con trỏ về dòng đầu tiên
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);  // Cột 0 cho mssv
            int mssv = cursor.getInt(1); // Cột 1 cho hoTen
            String hoTen = cursor.getString(2); // Cột 2 cho lopHoc
            String attendanceDat = cursor.getString(3);
            String status = cursor.getString(4);// Cột 2 cho lopHoc
            String lopHoc1 = cursor.getString(5);

            Attendance attendance = new Attendance(id, mssv, hoTen, attendanceDat, status,lopHoc1);
            arrayList.add(attendance);
            cursor.moveToNext();  // Di chuyển đến dòng tiếp theo
        }
        cursor.close();
        lichSuDiemDanhAdapter.notifyDataSetChanged();
        return view;
    }
}