package com.example.myapplication.DanhSachTatCaSinhVien;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import java.util.ArrayList;

public class Fragment_editSinhVien extends Fragment {
    private ImageView imageAdd, imageSeach;
    private EditText editTextSearch;
    private ListView listViewSinhVien;
    private ArrayList<SinhVien> arrayList, arrayList2;
    private ListSinhVienAllAdapter arrayAdapter;
    private SQLiteDatabase sqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_sinh_vien, container, false);
        listViewSinhVien = view.findViewById(R.id.listViewSinhVien);
        imageAdd = view.findViewById(R.id.imageAdd);
        imageSeach = view.findViewById(R.id.imageSeach);
        editTextSearch = view.findViewById(R.id.editTextTimKiemSV);

        // Khởi tạo danh sách và adapter
        arrayList = new ArrayList<>();
        arrayAdapter = new ListSinhVienAllAdapter(getActivity(), R.layout.items_listsinhvien, arrayList);
        listViewSinhVien.setAdapter(arrayAdapter);

        // Tạo hoặc mở cơ sở dữ liệu
        sqLiteDatabase = getActivity().openOrCreateDatabase("qlsv.db", getContext().MODE_PRIVATE, null);
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SinhVienDanhSach");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS SinhVienDanhSach(mssv INTEGER PRIMARY KEY AUTOINCREMENT, hoTen TEXT, lopHoc TEXT,gioiTinh TEXT,namSinh TEXT, soDienThoai TEXT, gmail TEXT)");

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity_editSinhVien.class);
                startActivity(intent);
            }
        });

        imageSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchQuery = editTextSearch.getText().toString().toLowerCase().trim();
                if (searchQuery.isEmpty()) {
                    // Nếu ô tìm kiếm trống, hiển thị toàn bộ danh sách
                    arrayAdapter = new ListSinhVienAllAdapter(getActivity(), R.layout.items_listsinhvien, arrayList);
                } else {
                    // Lọc danh sách sinh viên dựa trên từ khóa tìm kiếm
                    arrayList2 = new ArrayList<>();
                    for (SinhVien sinhvien : arrayList) {
                        if (sinhvien.getHoVaTen().toLowerCase().contains(searchQuery)) {
                            arrayList2.add(sinhvien);
                        }
                    }
                    // Gán adapter với danh sách đã lọc
                    arrayAdapter = new ListSinhVienAllAdapter(getActivity(), R.layout.items_listsinhvien, arrayList2);
                }

                // Cập nhật lại ListView
                listViewSinhVien.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

                // Hiển thị thông báo nếu không tìm thấy sinh viên nào
                if (arrayList2.isEmpty() && !searchQuery.isEmpty()) {
                    Toast.makeText(getContext(), "Không tìm thấy sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Gọi hàm loadData để tự động hiển thị danh sách sinh viên
        loadData();
        return view;
    }

    // Hàm load dữ liệu từ database vào ListView
    private void loadData() {
        arrayList.clear();
        Cursor cursor = sqLiteDatabase.query("SinhVienDanhSach", null, null, null, null, null, null);
        cursor.moveToFirst();  // Chuyển con trỏ về dòng đầu tiên
        while (!cursor.isAfterLast()) {
            int mssv = cursor.getInt(0);  // Cột 0 cho mssv
            String hoTen = cursor.getString(1); // Cột 1 cho hoTen
            String lopHoc = cursor.getString(2); // Cột 2 cho lopHoc
            String gioiTinh = cursor.getString(3);
            String namSinh = cursor.getString(4);// Cột 2 cho lopHoc
            String soDienThoai = cursor.getString(5); // Cột 3 cho soDienThoai
            String gmail = cursor.getString(6); // Cột 4 cho gmail
            SinhVien sinhVien = new SinhVien(mssv, hoTen, lopHoc,gioiTinh,namSinh, soDienThoai, gmail);
            arrayList.add(sinhVien);
            cursor.moveToNext();  // Di chuyển đến dòng tiếp theo
        }
        cursor.close();
        arrayAdapter.notifyDataSetChanged();
    }
}