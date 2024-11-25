package com.example.myapplication.ChuyenMenu;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.DanhSachLop.Fragment_listClass;
import com.example.myapplication.DanhSachTatCaSinhVien.Fragment_editSinhVien;
import com.example.myapplication.DanhSachLopDiemDanh.Fragment_attendance;
import com.example.myapplication.FragmentHistory;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectedListClass = null;
                if(id == R.id.Home){
                    selectedListClass = new Fragment_listClass();
                }else if(id == R.id.Attendance){
                    selectedListClass = new Fragment_attendance();
                }else if(id == R.id.Edit){
                    selectedListClass = new Fragment_editSinhVien();
                }else if(id == R.id.History){
                    selectedListClass = new FragmentHistory();
                }
                if(selectedListClass != null){
                    replaceFragment(selectedListClass);
                    return true;
                }
                return false;
            }
        });
        if(savedInstanceState == null){
            replaceFragment(new Fragment_listClass());
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
       }
}