package com.example.myapplication.DanhSachLopDiemDanh;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DanhSachLop.ListClass;
import com.example.myapplication.DiemDanhSinhVien.MainDiemDanh;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FragmentAttendanceAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ListClass> arrayListAttendance;

    public FragmentAttendanceAdapter(Context context, int layout, List<ListClass> arrayListAttendance) {
        this.context = context;
        this.layout = layout;
        this.arrayListAttendance = arrayListAttendance;
    }

    @Override
    public int getCount() {
        return arrayListAttendance.size();
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
        view = inflater.inflate(layout, null);

        TextView textViewClass = view.findViewById(R.id.textViewClass);
        textViewClass.setText(arrayListAttendance.get(i).getTenLop());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lopHoc = arrayListAttendance.get(i).getTenLop();
                if (isAttendanceToday(lopHoc)) {
                    Toast.makeText(context, "Lớp này đã được điểm danh hôm nay!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, MainDiemDanh.class);
                    intent.putExtra("lopHoc", lopHoc);
                    context.startActivity(intent);
                }
            }
        });
        return view;
    }
    // Phương thức kiểm tra điểm danh
    private boolean isAttendanceToday(String lopHoc) {
        SQLiteDatabase db = context.openOrCreateDatabase("qlsv.db", Context.MODE_PRIVATE, null);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Calendar.getInstance().getTime());
        Cursor cursor = db.rawQuery(
                "SELECT * FROM DiemDanhSinhVien WHERE attendanceDate = ? AND lopHoc = ?",
                new String[]{currentDate, lopHoc}
        );
        boolean hasAttendance = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return hasAttendance;
    }
}
