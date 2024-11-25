package com.example.myapplication.DangNhap;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class MainActivityRegister extends AppCompatActivity {
    EditText editTextUserName,editTextPassWord,editTextPassWordConfirm;
    ImageView eyePassWord,eyePassWordConfirm;
    Button btnRegister;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_register);

        editTextUserName= findViewById(R.id.editTextUserName);
        editTextPassWord= findViewById(R.id.editTextPassWord);
        editTextPassWordConfirm= findViewById(R.id.editTextPassWordConfirm);
        eyePassWord= findViewById(R.id.eyePassWord);
        eyePassWordConfirm= findViewById(R.id.eyePassWordConfirm);
        btnRegister= findViewById(R.id.btnRegister);

        eyePassWord.setOnClickListener(new View.OnClickListener() {
            private boolean isPasswordVisible = false;
            @Override
            public void onClick(View view) {
                if(isPasswordVisible){
                    editTextPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyePassWord.setImageResource(R.drawable.eyepass);
                }else{
                    editTextPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyePassWord.setImageResource(R.drawable.eyepassvisible);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        eyePassWordConfirm.setOnClickListener(new View.OnClickListener() {
            private boolean isPasswordVisible = false;
            @Override
            public void onClick(View view) {
                if(isPasswordVisible){
                    editTextPassWordConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyePassWordConfirm.setImageResource(R.drawable.eyepass);
                }else{
                    editTextPassWordConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyePassWordConfirm.setImageResource(R.drawable.eyepassvisible);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        sqLiteDatabase = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS GiaoVien (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "    name TEXT, \n" +
                "    passWord TEXT,\n" +
                "    lopHocId INTEGER, -- Cột khóa ngoại\n" +
                "    FOREIGN KEY (lopHocId) REFERENCES LopHoc(id) -- Liên kết với bảng LopHoc\n" +
                ");");


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUserName.getText().toString();
                String passWord = editTextPassWord.getText().toString();
                String passWordConfirm = editTextPassWordConfirm.getText().toString();
                if (!passWord.equalsIgnoreCase(passWordConfirm)) {
                    Toast.makeText(MainActivityRegister.this, "Mật khẩu xác nhận không trùng khớp!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues cv = new ContentValues();
                cv.put("name", username);
                cv.put("passWord", passWord);

                long result = sqLiteDatabase.insert("GiaoVien", null, cv);
                if (result == -1) {
                    Toast.makeText(MainActivityRegister.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivityRegister.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    editTextUserName.setText("");
                    editTextPassWord.setText("");
                    editTextPassWordConfirm.setText("");
                    Intent intent = new Intent(MainActivityRegister.this,MainActivityLogin.class);
                    startActivity(intent);
                }

            }
        });

    }
}