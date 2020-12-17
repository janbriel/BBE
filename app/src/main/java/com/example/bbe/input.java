package com.example.bbe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class input extends AppCompatActivity {

    private EditText Test;
    private EditText Test2;
    private TextView TextViewResult;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Test=findViewById(R.id.Test);
        Test2=findViewById(R.id.Test2);
        TextViewResult=findViewById(R.id.test_result);
        submit = findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Test.getText().toString().length() == 0) {
                    Test.setText("0");
                }

                if(Test2.getText().toString().length() == 0) {
                    Test2.setText("0");
                }

                int  num1 = Integer.parseInt(Test.getText().toString());
                int  num2 = Integer.parseInt(Test2.getText().toString());

                int sum = num1 +num2;

                TextViewResult.setText(String.valueOf(sum));
            }
        });
    }
}