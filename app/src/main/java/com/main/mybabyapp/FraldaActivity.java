package com.main.mybabyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FraldaActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private RadioGroup radioGroup;
    private RadioButton xixiBtn, cocoBtn, ambosBtn;
    private Button salvarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fralda);

        timePicker = findViewById(R.id.timePicker);
        radioGroup = findViewById(R.id.radioGroup);
        xixiBtn = findViewById(R.id.xixiBtn);
        cocoBtn = findViewById(R.id.cocoBtn);
        ambosBtn = findViewById(R.id.ambosBtn);
        salvarBtn = findViewById(R.id.salvarBtn);

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOption = "";

                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);
                    selectedOption = selectedRadioButton.getText().toString();
                }

                if (!selectedOption.isEmpty()) {
                    Toast.makeText(FraldaActivity.this, "Selected option: " + selectedOption, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FraldaActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
