package com.example.campusexpensemanagerse06304;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharePreferencesActivity extends AppCompatActivity {

    EditText edtNumber1, edtNumber2, edtResult;
    Button btnAdd, btnClear;
    TextView tvHistory;

    private String history = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        edtNumber1 = findViewById(R.id.edtNumber1);
        edtNumber2 = findViewById(R.id.edtNumber2);
        edtResult = findViewById(R.id.edtResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        tvHistory = findViewById(R.id.tvHistory);
        edtResult.setEnabled(false);

        //get data from share preferences
        SharedPreferences myPref = getSharedPreferences("calculator", MODE_PRIVATE);
        history = myPref.getString("history", "");
        tvHistory.setText(history);
        if(!history.isEmpty()){
            edtResult.setEnabled(true);
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1 = Integer.parseInt(edtNumber1.getText().toString().trim());
                int n2 = Integer.parseInt(edtNumber2.getText().toString().trim());
                int result = n1 + n2;
                edtResult.setText(String.valueOf(result));
                history += n1 + " + " + n2 + " = " + result;
                tvHistory.setText(history);
                history += "\n";

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               history = "";
               tvHistory.setText(history);
               edtResult.setText(history);
               edtNumber1.setText(history);
               edtNumber2.setText(history);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myPref = getSharedPreferences("calculator", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("history", history);
        editor.apply();

    }
}
