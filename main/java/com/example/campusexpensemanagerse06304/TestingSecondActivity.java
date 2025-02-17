package com.example.campusexpensemanagerse06304;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestingSecondActivity extends AppCompatActivity {
    Button btnGotoFirst;
    TextView tvData;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_second);
        tvData = findViewById(R.id.tvData);

        //get data from testingactivity
        Intent getdataintent = getIntent();
        Bundle getdatabundle = getdataintent.getExtras();

        if (getdatabundle != null){
            String data = getdatabundle.getString("data test", "");
            tvData.setText(data);
        }

        btnGotoFirst = findViewById(R.id.btnGôtFirst);
        btnGotoFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TestingSecondActivity.this, TestingActivity.class);
                startActivity(intent);
            }
        });
    }
}


