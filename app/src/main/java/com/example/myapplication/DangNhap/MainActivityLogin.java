package com.example.myapplication.DangNhap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ChuyenMenu.HomePage;
import com.example.myapplication.R;

public class MainActivityLogin extends AppCompatActivity {

    EditText editUserName, editPassWord;
    ImageView eyePassWord;
    CheckBox checkBoxRememberMe;
    Button btnLogin, btnRegisterNow;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Bind views
        editUserName = findViewById(R.id.editTextUserName);
        editPassWord = findViewById(R.id.editTextPassWord);
        eyePassWord = findViewById(R.id.eyePassWord);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        btnRegisterNow = findViewById(R.id.btnRegisterNow);
        btnLogin = findViewById(R.id.btnLogin);

        // Database
        sqLiteDatabase = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);

        // Eye icon click listener for password visibility toggle
        eyePassWord.setOnClickListener(new View.OnClickListener() {
            private boolean isPasswordVisible = false;
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    editPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyePassWord.setImageResource(R.drawable.eyepass);
                } else {
                    editPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyePassWord.setImageResource(R.drawable.eyepassvisible);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        // Login button click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editUserName.getText().toString().trim();
                String passWord = editPassWord.getText().toString().trim();

                // Check if the fields are empty
                if (userName.isEmpty() || passWord.isEmpty()) {
                    Toast.makeText(MainActivityLogin.this, "Vui lòng nhập tài khoản và mật khẩu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Query the database to check if the credentials are correct
                Cursor cursor = sqLiteDatabase.query("GiaoVien", null,
                        "name = ? AND passWord = ?", new String[]{userName, passWord},
                        null, null, null);

                if (cursor != null && cursor.getCount() > 0) {
                    // Login successful
                    Intent intent = new Intent(MainActivityLogin.this, HomePage.class);
                    startActivity(intent);
                    cursor.close();  // Don't forget to close the cursor
                } else {
                    // Invalid login
                    Toast.makeText(MainActivityLogin.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Register now button click listener
        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityLogin.this, MainActivityRegister.class);
                startActivity(intent);
            }
        });
    }
}
