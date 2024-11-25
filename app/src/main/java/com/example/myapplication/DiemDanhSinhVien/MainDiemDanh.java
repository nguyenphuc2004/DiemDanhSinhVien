package com.example.myapplication.DiemDanhSinhVien;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DanhSachLopDiemDanh.Attendance;
import com.example.myapplication.DanhSachTatCaSinhVien.SinhVien;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainDiemDanh extends AppCompatActivity {
    DiemDanhAdapter lichSuDiemDanhAdapter;
    ArrayList<Attendance> arrayList;
    ListView listViewLichSuDiemDanh;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_lich_su_diem_danh);

        listViewLichSuDiemDanh = findViewById(R.id.listViewLichSuDiemDanh);
        sqLiteDatabase = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);

        arrayList = new ArrayList<>();
        lichSuDiemDanhAdapter = new DiemDanhAdapter(MainDiemDanh.this, R.layout.items_lichsudiemdanh, arrayList);
        listViewLichSuDiemDanh.setAdapter(lichSuDiemDanhAdapter);
        // Nhận lớp học từ Intent
        String lopHoc = getIntent().getStringExtra("lopHoc");
        /////////////////////////////////////////////
        ArrayList<SinhVien> arrayList2 = new ArrayList<>();
        Cursor cursor2 = sqLiteDatabase.query("SinhVienDanhSach", null, "lopHoc = ?", new String[]{lopHoc}, null, null, null);
        cursor2.moveToFirst();  // Di chuyển con trỏ về dòng đầu tiên
        while (!cursor2.isAfterLast()) {
            int mssv = Integer.parseInt(cursor2.getString(0));
            String hoTen = cursor2.getString(1);
            String lopHoc1 = cursor2.getString(2);
            String gioiTinh = cursor2.getString(3);
            String namSinh = cursor2.getString(4);
            String soDienThoai = cursor2.getString(5);
            String gmail = cursor2.getString(6);
            // Tạo đối tượng SinhVien từ dữ liệu
            SinhVien sinhVien = new SinhVien(mssv, hoTen, lopHoc1,gioiTinh,namSinh, soDienThoai, gmail);
            arrayList2.add(sinhVien);

            cursor2.moveToNext();  // Di chuyển con trỏ đến dòng tiếp theo
        }
        cursor2.close();

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
        // Duyệt qua danh sách sinh viên và lưu vào cơ sở dữ liệu
        for (SinhVien sinhVien : arrayList2) {
            ContentValues cv = new ContentValues();
            cv.put("mssv", sinhVien.getMssv());
            cv.put("hoTen", sinhVien.getHoVaTen());
            cv.put("attendanceDate", currentDate);
            cv.put("status", "Có mặt"); // Hoặc "Vắng mặt" nếu cần
            cv.put("lopHoc",sinhVien.getLopHoc());

            // Thực hiện insert vào bảng DiemDanhSinhVien
            long result = sqLiteDatabase.insert("DiemDanhSinhVien", null, cv);
            if (result == -1) {
                Toast.makeText(MainDiemDanh.this, "Lưu điểm danh cho sinh viên " + sinhVien.getHoVaTen() + " thất bại", Toast.LENGTH_SHORT).show();
            }
        }
        /////////////////////////////////////////////
        arrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DiemDanhSinhVien Where lopHoc = ?", new String[]{lopHoc});
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

        listViewLichSuDiemDanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showMenu(i);
            }
        });
///////////////////////////////////////////////////////////
    }

    public boolean showMenu(int position) {
        Attendance selectedAttendance = arrayList.get(position); // Lấy Attendance đã chọn
        PopupMenu popupMenu = new PopupMenu(MainDiemDanh.this, listViewLichSuDiemDanh);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                String newStatus = "";
                if (id == R.id.item_CoPhep) {
                    newStatus = "Có phép";
                } else if (id == R.id.item_KhongPhep) {
                    newStatus = "Không phép";
                } else if (id == R.id.item_CoMat) {
                    newStatus = "Có mặt";
                }

                // Cập nhật trạng thái trong cơ sở dữ liệu
                if (!newStatus.isEmpty()) {
                    ContentValues cv = new ContentValues();
                    cv.put("status", newStatus); // Cột status trong bảng DiemDanhSinhVien

                    int result = sqLiteDatabase.update("DiemDanhSinhVien", cv, "id = ?", new String[]{String.valueOf(selectedAttendance.getId())});
                    if (result > 0) {
                        // Cập nhật danh sách và thông báo thành công
                        selectedAttendance.setStatus(newStatus); // Cập nhật trạng thái trong đối tượng
                        lichSuDiemDanhAdapter.notifyDataSetChanged(); // Cập nhật adapter
                        
                        Toast.makeText(MainDiemDanh.this, "Cập nhật trạng thái thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainDiemDanh.this, "Cập nhật trạng thái thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
        popupMenu.show();
        return true;
    }
    @Override
    public void onBackPressed() {
        // Tạo AlertDialog để xác nhận khi người dùng nhấn nút Back
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Thông Báo!")
                .setMessage("Nếu bạn thoát thông tin sẽ lưu và không thể sửa đổi?")
                .setPositiveButton("Thoát", (dialog, which) -> {
                    // Đóng Activity nếu người dùng chọn "Có"
                    super.onBackPressed();
                })
                .setNegativeButton("Không", (dialog, which) -> {
                    // Đóng hộp thoại nếu người dùng chọn "Không"
                    dialog.dismiss();
                })
                .show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đóng cơ sở dữ liệu nếu đang mở
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }
}