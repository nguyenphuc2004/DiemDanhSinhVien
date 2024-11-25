package com.example.myapplication.DanhSachTatCaSinhVien;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivity_editSinhVien extends AppCompatActivity {
    private EditText editTextHoTen, editTextMssv, editTextLopHoc,editTextNamSinh, editTextSoDienThoai, editTextGmail;
    private Button btnThem, btnSua, btnXoa;
    private RadioGroup radioGroupGioiTinh;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_edit_sinh_vien);

        // Tham chiếu đến các View trong layout
        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextMssv = findViewById(R.id.editTextMssv);
        editTextLopHoc = findViewById(R.id.editTextLopHoc);
        radioGroupGioiTinh = findViewById(R.id.radioGroupGioiTinh);
        editTextNamSinh = findViewById(R.id.editTextNamSinh);
        editTextSoDienThoai = findViewById(R.id.editTextSoDienThoai);
        editTextGmail = findViewById(R.id.editTextGmail);
        btnThem = findViewById(R.id.btnThem);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);

        // Tạo hoặc mở cơ sở dữ liệu
        sqLiteDatabase = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);

        // Thêm sinh viên
        btnThem.setOnClickListener(view1 -> {
            String hoTen = editTextHoTen.getText().toString();
            String lopHoc = editTextLopHoc.getText().toString();
            String namSinh = editTextNamSinh.getText().toString();
            String soDienThoai = editTextSoDienThoai.getText().toString();
            String gmail = editTextGmail.getText().toString();

            // Lấy giá trị giới tính từ RadioGroup
            int selectedGenderId = radioGroupGioiTinh.getCheckedRadioButtonId();
            String gioiTinh = "";
            if (selectedGenderId == R.id.radioButtonNam) {
                gioiTinh = "Nam";
            } else if (selectedGenderId == R.id.radioButtonNu) {
                gioiTinh = "Nữ";
            }

            if (hoTen.isEmpty() || lopHoc.isEmpty() || namSinh.isEmpty() || soDienThoai.isEmpty() || gmail.isEmpty() || gioiTinh.isEmpty()) {
                Toast.makeText(MainActivity_editSinhVien.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (lopHoc.length() < 6) {
                Toast.makeText(MainActivity_editSinhVien.this, "Lớp học vui lòng đền đúng", Toast.LENGTH_SHORT).show();
                return;
            }
            if (soDienThoai.length() < 10) {
                Toast.makeText(MainActivity_editSinhVien.this, "Vui lòng đền đúng số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidPhoneNumber(soDienThoai)) {
                Toast.makeText(MainActivity_editSinhVien.this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidEmail(gmail)) {
                Toast.makeText(MainActivity_editSinhVien.this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                return;
            }

            // Thêm sinh viên vào cơ sở dữ liệu
            ContentValues cv = new ContentValues();
            cv.put("hoTen", hoTen);
            cv.put("lopHoc", lopHoc);
            cv.put("gioiTinh", gioiTinh);
            cv.put("namSinh", namSinh);
            cv.put("soDienThoai", soDienThoai);
            cv.put("gmail", gmail);

            long result = sqLiteDatabase.insert("SinhVienDanhSach", null, cv);
            if (result == 0) {
                Toast.makeText(MainActivity_editSinhVien.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity_editSinhVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                clearFields(); // Xóa các trường sau khi thêm thành công
                //Cập nhật danh sách trong Fragment


            }
        });
        // Xóa sinh viên
        btnXoa.setOnClickListener(view12 -> {
            String mssv = editTextMssv.getText().toString();
            int result = sqLiteDatabase.delete("SinhVienDanhSach", "mssv = ?", new String[]{mssv});
            if (result == 0) {
                Toast.makeText(MainActivity_editSinhVien.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity_editSinhVien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        });

        // Sửa thông tin sinh viên
        btnSua.setOnClickListener(view13 -> {
            String mssv = editTextMssv.getText().toString();
            String hoTen = editTextHoTen.getText().toString();
            String lopHoc = editTextLopHoc.getText().toString();
            String namSinh = editTextNamSinh.getText().toString();
            String soDienThoai = editTextSoDienThoai.getText().toString();
            String gmail = editTextGmail.getText().toString();

            // Lấy giá trị giới tính từ RadioGroup
            int selectedGenderId = radioGroupGioiTinh.getCheckedRadioButtonId();
            String gioiTinh = "";
            if (selectedGenderId == R.id.radioButtonNam) {
                gioiTinh = "Nam";
            } else if (selectedGenderId == R.id.radioButtonNu) {
                gioiTinh = "Nữ";
            }
            if(mssv.isEmpty()){
                Toast.makeText(MainActivity_editSinhVien.this, "Vui lòng điền Mssv cần sửa!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (hoTen.isEmpty() || lopHoc.isEmpty() || namSinh.isEmpty() || soDienThoai.isEmpty() || gmail.isEmpty() || gioiTinh.isEmpty()) {
                Toast.makeText(MainActivity_editSinhVien.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (lopHoc.length() < 6) {
                Toast.makeText(MainActivity_editSinhVien.this, "Lớp học vui lòng đền đúng", Toast.LENGTH_SHORT).show();
                return;
            }
            if (soDienThoai.length() < 10) {
                Toast.makeText(MainActivity_editSinhVien.this, "Vui lòng đền đúng số điện thoại", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidPhoneNumber(soDienThoai)) {
                Toast.makeText(MainActivity_editSinhVien.this, "Số điện thoại không đúng định dạng", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!isValidEmail(gmail)) {
                Toast.makeText(MainActivity_editSinhVien.this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues cv = new ContentValues();
            cv.put("hoTen", hoTen);
            cv.put("lopHoc", lopHoc);
            cv.put("gioiTinh", gioiTinh);
            cv.put("namSinh", namSinh);
            cv.put("soDienThoai", soDienThoai);
            cv.put("gmail", gmail);

            int result = sqLiteDatabase.update("SinhVienDanhSach", cv, "mssv = ?", new String[]{mssv});
            if (result == 0) {
                Toast.makeText(MainActivity_editSinhVien.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity_editSinhVien.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                clearFields();
                finish();

            }
        });

    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private boolean isValidPhoneNumber(String phone) {
        String phonePattern = "^03\\d{8}$"; // Định dạng cho số điện thoại 10 chữ số, bắt đầu bằng '01'
        return phone.matches(phonePattern);
    }
    private void clearFields() {
        editTextHoTen.setText("");
        editTextMssv.setText("");
        editTextLopHoc.setText("");
        editTextNamSinh.setText("");
        editTextSoDienThoai.setText("");
        editTextGmail.setText("");
        radioGroupGioiTinh.clearCheck();
    }
}