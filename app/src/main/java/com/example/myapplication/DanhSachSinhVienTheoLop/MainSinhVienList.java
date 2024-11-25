package com.example.myapplication.DanhSachSinhVienTheoLop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DanhSachTatCaSinhVien.SinhVien;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainSinhVienList extends AppCompatActivity {

    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sinh_vien_list);

        // Khởi tạo ListView
        ListView listViewSinhVien = findViewById(R.id.listViewSinhVien);
        // Tạo danh sách sinh viên từ cơ sở dữ liệu
        ArrayList<SinhVien> arrayList = new ArrayList<>();

        // Mở hoặc tạo cơ sở dữ liệu
        sqLiteDatabase = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
        // Nhận giá trị lớp học từ Intent
        String lopHoc1 = getIntent().getStringExtra("lopHoc");
        // Truy vấn tất cả sinh viên từ bảng listSinhVien
        Cursor cursor = sqLiteDatabase.query("SinhVienDanhSach", null, "lopHoc = ?", new String[]{lopHoc1}, null, null, null);

        // Duyệt qua dữ liệu trong cursor và thêm vào arrayList
        cursor.moveToFirst();  // Di chuyển con trỏ về dòng đầu tiên
        while (!cursor.isAfterLast()) {
            int mssv = Integer.parseInt(cursor.getString(0));
            String hoTen = cursor.getString(1);
            String lopHoc = cursor.getString(2);
            String gioiTinh = cursor.getString(3);
            String namSinh = cursor.getString(4);
            String soDienThoai = cursor.getString(5);
            String gmail = cursor.getString(6);


            // Tạo đối tượng SinhVien từ dữ liệu
            SinhVien sinhVien = new SinhVien(mssv, hoTen, lopHoc,gioiTinh,namSinh, soDienThoai, gmail);
            arrayList.add(sinhVien);

            cursor.moveToNext();  // Di chuyển con trỏ đến dòng tiếp theo
        }
        cursor.close();  // Đóng cursor sau khi sử dụng

        // Khởi tạo adapter và gán vào ListView
        SinhVienListAdapter adapter = new SinhVienListAdapter(MainSinhVienList.this, R.layout.items_sinhvien, arrayList);
        listViewSinhVien.setAdapter(adapter);
    }
}
