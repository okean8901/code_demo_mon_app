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

import com.example.campusexpensemanagerse06304.database.UserDb;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button btnConfirmAccount, btnCancel;
    EditText editAccount, editEmail;
    UserDb userDb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        userDb = new UserDb(ForgetPasswordActivity.this);
        btnConfirmAccount = findViewById(R.id.btnConfirmAccount);
        btnCancel = findViewById(R.id.btnCancel);
        editAccount = findViewById(R.id.edtAccount);
        editEmail = findViewById(R.id.edtEmail);

        btnConfirmAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = editAccount.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    editAccount.setError("Please enter account");
                    return; //stop code
                }
                String email = editEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    editEmail.setError("Please enter email");
                    return; //stop code
                }
                boolean checking = userDb.checkExistUsernameAndEmail(account, email);
                if (checking) {
                    Intent intent = new Intent(ForgetPasswordActivity.this, UpdatePasswordActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ACCOUNT_FORGET_PW", account);
                    bundle.putString("EMAIL_FORGET_PW", email);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Account or email are incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}






