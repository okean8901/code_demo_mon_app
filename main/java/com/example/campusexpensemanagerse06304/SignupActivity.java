package com.example.campusexpensemanagerse06304;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.campusexpensemanagerse06304.database.UserDb;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class SignupActivity extends AppCompatActivity {
    EditText editusername, editpass, edtEmail, edtPhone;
    Button btnRegister, btncancel;
    UserDb userDb;
    TextView tvLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userDb = new UserDb(SignupActivity.this);
        editusername = findViewById(R.id.editusername);
        editpass = findViewById(R.id.editpass);
        btnRegister = findViewById(R.id.btnRegister);
        btncancel = findViewById(R.id.btncancel);
        tvLogin = findViewById(R.id.tvLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);

        signupAccount(); // save sqlite
//        registerAccount(); // Gọi phương thức đăng ký
    }

    private void signupAccount(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String pass = editpass.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;

                }
                boolean checkingUsernameEmail = userDb.checkExistUsernameAndEmail(username, email);
                if (checkingUsernameEmail){
                    editusername.setError("Username or email already exists");
                    edtEmail.setError("Username or email already exists");
                    return;

                }

                //insert data to sqlite
                long insert = userDb.insertUserAccount(username, pass, email, phone, 1);
                if (insert == -1){
                    //fail
                    Toast.makeText(SignupActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }else{
                    //success
                    Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    //go to login
                    Intent intent = new Intent(SignupActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void registerAccount() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String pass = editpass.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lưu dữ liệu người dùng vào local storage
                try {
                    username += "|";
                    FileOutputStream fileOpS = openFileOutput("account.txt", MODE_APPEND);
                    fileOpS.write(username.getBytes(StandardCharsets.UTF_8));
                    fileOpS.write(pass.getBytes(StandardCharsets.UTF_8));
                    fileOpS.write('\n');
                    fileOpS.close();

                    editusername.setText("");
                    editpass.setText("");

                    Toast.makeText(SignupActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                    // Chuyển hướng về trang đăng nhập
                    Intent intent = new Intent(SignupActivity.this, SignInActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(SignupActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
