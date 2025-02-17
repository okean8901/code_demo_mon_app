package com.example.campusexpensemanagerse06304;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FunnyGameActivity extends AppCompatActivity {
    Button btnAnswer;
    RadioGroup radioAnswer;
    RadioButton radgocu, radgoku, radkakarot;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funny_game);
        btnAnswer = findViewById(R.id.btnAnswer);
        radioAnswer = findViewById(R.id.radiogrAnswer);
        radgocu = findViewById(R.id.radgocu);
        radgoku = findViewById(R.id.radgoku);
        radkakarot = findViewById(R.id.radkakarot);

        //khi bấm nút trả lời
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kiểm tra các đáp án đã chọn là gì?
                int selectedId = radioAnswer.getCheckedRadioButtonId();
                RadioButton radioAnswer = (RadioButton) findViewById(selectedId);
                if(radioAnswer == null) {
                    Toast.makeText(FunnyGameActivity.this,"Vui lòng chọn đáp án",Toast.LENGTH_SHORT).show();
                    return;
                }
                String answer = radioAnswer.getText().toString().trim().toLowerCase();

                // chọn đáp án đúng sai

                if(answer.equalsIgnoreCase("kakarot")) {
                    Toast.makeText(FunnyGameActivity.this,"Đáp án đúng",Toast.LENGTH_SHORT).show();
                }else {
                    count += 1;
                    if (count > 2) {
                        Toast.makeText(FunnyGameActivity.this,"Bạn đã sai quá 3 lần",Toast.LENGTH_SHORT).show();
                        btnAnswer.setEnabled(false);
                        return;
                    }
                    Toast.makeText(FunnyGameActivity.this,"Đáp án sai",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
