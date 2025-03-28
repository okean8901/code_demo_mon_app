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

public class UpdatePasswordActivity  extends AppCompatActivity {
    Button btnSaveChangePassword;
    EditText edtNewPassword, edtConfirmNewPassword;
    UserDb userDb;
    Intent intent;
    Bundle bundle;

    private String account = null;
    private String email = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activuty_update_password);
        btnSaveChangePassword = findViewById(R.id.btnSaveChangePassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = findViewById(R.id.edtConfirmNewPassword);
        userDb = new UserDb(UpdatePasswordActivity.this);
        intent = getIntent();
        bundle = intent.getExtras();
        account = bundle.getString("ACCOUNT_FORGET_PW");
        email = bundle.getString("EMAIL_FORGET_PW");
        if (bundle != null){
            account = bundle.getString("ACCOUNT_FORGET_PW", "");
            email = bundle.getString("EMAIL_FORGET_PW","");
        }

        btnSaveChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = edtNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(newPassword)){
                    edtNewPassword.setError("Please enter new password");
                    return; //stop code
                }
                String confirmNewPassword = edtConfirmNewPassword.getText().toString().trim();
                if(!confirmNewPassword.equals(newPassword)){
                    edtConfirmNewPassword.setError("Confirm password is not match");
                    return; //stop code
                }
                int update = userDb.changePassword(account, email, newPassword);
                if (update == -1){
                    //fail
                    Toast.makeText(UpdatePasswordActivity.this, "Update password fail", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intentLogin = new Intent(UpdatePasswordActivity.this, SignInActivity.class);
                    startActivity(intentLogin);
                    Toast.makeText(UpdatePasswordActivity.this, "Update password success", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }
}
