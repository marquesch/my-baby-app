package com.main.mybabyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MamaActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton seioBtn, mamadeiraBtn;
    private LinearLayout seioNumberLayout, mamadeiraNumberLayout;
    private NumberPicker tensMinutePicker, minutePicker, hundredsMlPicker, tensMlPicker, mlPicker;
    private TextView minuteText, mlText;
    private Button salvarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mama);

        radioGroup = findViewById(R.id.radioGroup);
        seioBtn = findViewById(R.id.seioBtn);
        mamadeiraBtn = findViewById(R.id.mamadeiraBtn);
        seioNumberLayout = findViewById(R.id.seioNumberLayout);
        mamadeiraNumberLayout = findViewById(R.id.mamandeiraNumberLayout);
        tensMinutePicker = findViewById(R.id.tensMinutePicker);
        tensMinutePicker.setMinValue(0);
        tensMinutePicker.setMaxValue(9);
        tensMinutePicker.setValue(0);
        minutePicker = findViewById(R.id.minutePicker);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(9);
        minutePicker.setValue(0);
        hundredsMlPicker = findViewById(R.id.hundredsMlPicker);
        hundredsMlPicker.setMinValue(0);
        hundredsMlPicker.setMaxValue(9);
        hundredsMlPicker.setValue(0);
        tensMlPicker = findViewById(R.id.tensMlPicker);
        tensMlPicker.setMinValue(0);
        tensMlPicker.setMaxValue(9);
        tensMlPicker.setValue(0);
        mlPicker = findViewById(R.id.mlPicker);
        mlPicker.setMinValue(0);
        mlPicker.setMaxValue(9);
        mlPicker.setValue(0);
        minuteText = findViewById(R.id.minuteText);
        mlText = findViewById(R.id.mlText);
        salvarBtn = findViewById(R.id.salvarBtn);

        seioNumberLayout.setVisibility(View.GONE);
        mamadeiraNumberLayout.setVisibility(View.GONE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.seioBtn) {
                    seioNumberLayout.setVisibility(View.VISIBLE);
                    mamadeiraNumberLayout.setVisibility(View.GONE);
                } else if (checkedId == R.id.mamadeiraBtn) {
                    seioNumberLayout.setVisibility(View.GONE);
                    mamadeiraNumberLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seioBtn.isChecked()) {
                    int tensMinutes = tensMinutePicker.getValue();
                    int minutes = minutePicker.getValue();
                    int minutesValue = tensMinutes * 10 + minutes;
                    Toast.makeText(MamaActivity.this, "Seio. " + minutesValue + " minutos.", Toast.LENGTH_SHORT).show();
                } else if (mamadeiraBtn.isChecked()) {
                    int hundredsMl = hundredsMlPicker.getValue();
                    int tensMl = tensMlPicker.getValue();
                    int ml = mlPicker.getValue();
                    int mlValue = hundredsMl * 100 + tensMl * 10 + ml;
                    Toast.makeText(MamaActivity.this, "Mamadeira. " + mlValue + " ml.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}