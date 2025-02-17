package com.example.campusexpensemanagerse06304;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventActivity extends AppCompatActivity {

    Button btnClickme, btnOpen, btnBlock, btnSubmit; // property in class - oop java
    EditText editText; //property
    TextView tvText;
    CheckBox cbNotRobot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_demo);

        //find element in layout
        btnClickme = findViewById(R.id.btnCLickme);
        btnBlock = findViewById(R.id.btnBlock);
        editText = findViewById(R.id.editInput);
        cbNotRobot = findViewById(R.id.cbNotRobot);
        btnSubmit = findViewById(R.id.btnSubmit);


        editText.setEnabled(false); //block input
        btnClickme.setEnabled(false); //block btn
        btnSubmit.setEnabled(false);


        btnOpen = findViewById(R.id.btnOpen);
        tvText = findViewById(R.id.tvTextHidden);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvText.setText(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //gan cho su kien
        btnClickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString().trim();
                Toast.makeText(EventActivity.this,"xin chào "+ data, Toast.LENGTH_SHORT).show();

            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(true);
                btnClickme.setEnabled(true);
            }
        });
        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setEnabled(false);
                btnClickme.setEnabled(false);
            }
        });
        cbNotRobot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    btnSubmit.setEnabled(true);
                }
                else
                {
                    btnSubmit.setEnabled(false);
                }
            }
        });


    }
}
