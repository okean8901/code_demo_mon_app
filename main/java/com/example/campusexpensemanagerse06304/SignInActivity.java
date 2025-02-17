package com.example.campusexpensemanagerse06304;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    EditText editusername, editpass;
    Button btnSubmit, btncancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editusername = findViewById(R.id.editusername);
        editpass = findViewById(R.id.editpass);
        btnSubmit = findViewById(R.id.btnSubmit);
        btncancel = findViewById(R.id.btncancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editusername.getText().toString().trim();
                String pass = editpass.getText().toString().trim();

                if (username.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("123")) {
                    Toast.makeText(SignInActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, FunnyGameActivity.class);
                    startActivity(intent);
                    finish(); // ko cho back laji activity truoc do
                }else {
                    Toast.makeText(SignInActivity.this, "Account invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

