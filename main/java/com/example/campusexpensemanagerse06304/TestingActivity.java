package com.example.campusexpensemanagerse06304;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestingActivity extends AppCompatActivity {
    private final String LOG_ACTIVITY = "LOG_ACTIVITY";
    Button btnOpenSecond;
    EditText edtData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // methods sẽ chạy đầu tiên khi Activity được khởi động
        // thông thường method này sẽ là nơi load layout của ứng dụng

        setContentView(R.layout.activity_testing);
        Log.i(LOG_ACTIVITY, "******* onCreate is running *******");

        edtData = findViewById(R.id.edtData);
        btnOpenSecond = findViewById(R.id.btnOpenSecond);
        btnOpenSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtData.getText().toString().trim();
                //send data to other activity

                if (TextUtils.isEmpty(data)){
                    Toast.makeText(TestingActivity.this, "Data not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(TestingActivity.this, TestingSecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data test", data);
                intent.putExtras(bundle);
                startActivity(intent); //chuyển sang other activity
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_ACTIVITY, "****** onDestroy is running ******");
        //huy - dong app
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_ACTIVITY, "****** onRestart is running ******");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_ACTIVITY, "****** onPause is running ******");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_ACTIVITY, "****** onStop is running ******");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_ACTIVITY, "****** onStart is running ******");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_ACTIVITY, "****** onResume is running ******");
    }
}
