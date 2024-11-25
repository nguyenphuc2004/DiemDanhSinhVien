package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainSinhVienDetail extends AppCompatActivity {
    TextView textViewHoVaTen,textViewMSSV,textViewSDT,textViewGmail,textViewTenLop,textViewGioiTinh,textViewNamSinh;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_sinh_vien_detail);

//        imageView = findViewById(R.id.imageViewProfile);
        textViewGmail = findViewById(R.id.textViewGmailDT);
        textViewHoVaTen = findViewById(R.id.textViewHoVaTenDT);
        textViewMSSV = findViewById(R.id.textViewMSSVDT);
        textViewSDT = findViewById(R.id.textViewSDTDT);
        textViewTenLop = findViewById(R.id.textViewTenLopDT);
        textViewGioiTinh = findViewById(R.id.textViewGioiTinhDT);
        textViewNamSinh = findViewById(R.id.textViewNamSinhDT);

//        imageView.setImageResource(getIntent().getIntExtra("Image",0));
        textViewMSSV.setText(String.valueOf(getIntent().getIntExtra("MSSV",0)));
        textViewGmail.setText(getIntent().getStringExtra("Gmail"));
        textViewHoVaTen.setText(getIntent().getStringExtra("HoVaTen"));
        textViewGioiTinh.setText(getIntent().getStringExtra("GioiTinh"));
        textViewNamSinh.setText(getIntent().getStringExtra("NamSinh"));
        textViewSDT.setText(getIntent().getStringExtra("SDT"));
        textViewTenLop.setText(getIntent().getStringExtra("TenLop"));

    }
}