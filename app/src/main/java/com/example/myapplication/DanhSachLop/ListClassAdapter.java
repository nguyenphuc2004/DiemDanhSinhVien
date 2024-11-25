package com.example.myapplication.DanhSachLop;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.DanhSachSinhVienTheoLop.MainSinhVienList;

import com.example.myapplication.R;

import java.util.List;

public class ListClassAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ListClass> arrayListClass;

    private SQLiteDatabase sqLiteDatabase;
    public ListClassAdapter(Context context, int layout, List<ListClass> listClassList) {
        this.context = context;
        this.layout = layout;
        this.arrayListClass = listClassList;
    }

    @Override
    public int getCount() {
        return arrayListClass.size();
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


        TextView textViewClass = view.findViewById(R.id.textViewClass);
        textViewClass.setText(arrayListClass.get(i).getTenLop());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainSinhVienList.class);
                intent.putExtra("lopHoc", arrayListClass.get(i).getTenLop());
                context.startActivity(intent);
            }
        });
        return view;
    }
}