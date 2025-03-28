package com.example.campusexpensemanagerse06304;

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
import com.example.campusexpensemanagerse06304.model.Users;

import java.io.FileInputStream;

public class SignInActivity extends AppCompatActivity {
    EditText editusername, editpass;
    Button btnSubmit, btncancel;
    TextView tvSignup, tvSignin, tvforgetPass;
    UserDb userDb;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userDb = new UserDb(SignInActivity.this);
        editusername = findViewById(R.id.editusername);
        editpass = findViewById(R.id.editpass);
        btnSubmit = findViewById(R.id.btnSubmit);
        btncancel = findViewById(R.id.btncancel);
        tvSignup = findViewById(R.id.tvSignup);
        tvforgetPass = findViewById(R.id.tvForgetPass);

        tvforgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForgetPw = new Intent(SignInActivity.this, ForgetPasswordActivity.class);
                startActivity(intentForgetPw);
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        // checkloginWithDataFile();
        checkLoginUser();
    }
    private void checkLoginUser(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    editusername.setError("Please enter username");
                    return; //stop
                }
                String pass = editpass.getText().toString().trim();
                if (TextUtils.isEmpty(pass)){
                    editpass.setError("Please enter password");
                    return; //stop
                }
                //check login with sqlite
                Users infoUsers = userDb.checkLoginUser(username, pass);
                assert infoUsers != null;
                if (infoUsers.getUsername() != null){
                    //login success
                    Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ID_USER", infoUsers.getId());
                    bundle.putString("username", infoUsers.getUsername());
                    bundle.putString("EMAIL", infoUsers.getEmail());
                    bundle.putInt("ROLE_ID", infoUsers.getRole_id());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{
                    //login fail
                    Toast.makeText(SignInActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void checkloginWithDataFile(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString().trim();
                String pass = editpass.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignInActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    // xử lý đọc nội dung từ file local storage để kiểm tra dăng nhập
                    FileInputStream fileInputStream = openFileInput("account.txt");
                    int read = -1;
                    StringBuilder builder = new StringBuilder();
                    while ((read = fileInputStream.read()) != -1){
                        builder.append((char) read);
                        //tất cả dữ liệu gán vào biến builder
                    }
                    fileInputStream.close();
                    //validation account
                    String[] infoAccount = null;
                    infoAccount = builder.toString().trim().split("\n");
                    boolean check = false;
                    //duyệt mảng để kiểm tra tài khoản
                    int sizeArrayAccount = infoAccount.length;
                    for (int i = 0; i < infoAccount.length; i++) {
                        String user = infoAccount[i].substring(0, infoAccount[i].indexOf("|"));
                        String password = infoAccount[i].substring(infoAccount[i].indexOf("|") + 1);
                        if (username.equalsIgnoreCase(user) && pass.equalsIgnoreCase(password)) {
                            check = true;
                            break;
                        }
                    }

                    if (check){
                        Toast.makeText(SignInActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username", username);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(SignInActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
//                if (username.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("123")) {
//                    Toast.makeText(SignInActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SignInActivity.this, MenuActivity.class);
//                    startActivity(intent);
//                    finish(); // ko cho back laji activity truoc do
//                }else {
//                    Toast.makeText(SignInActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
}

