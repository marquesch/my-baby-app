package com.main.mybabyapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.main.mybabyapp.data.dao.DiaperDao;
import com.main.mybabyapp.data.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DiaperActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private DiaperDao diaperDao;

    private TimePicker timePicker;
    private RadioGroup radioGroup;
    private RadioButton peeBtn, poopBtn, bothBtn;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaper);

        dbHelper = new DatabaseHelper(this);
        diaperDao = new DiaperDao(dbHelper.getWritableDatabase());

        timePicker = findViewById(R.id.timePicker);
        radioGroup = findViewById(R.id.radioGroup);
        peeBtn = findViewById(R.id.peeBtn);
        poopBtn = findViewById(R.id.poopBtn);
        bothBtn = findViewById(R.id.bothBtn);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOption = "";

                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);
                    selectedOption = selectedRadioButton.getText().toString();
                }

                if (!selectedOption.isEmpty()) {
                    boolean pee = selectedOption.contains("Pee") || selectedOption.contains("Both");
                    boolean poop = selectedOption.contains("Poop") || selectedOption.contains("Both");

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());
                    calendar.set(Calendar.SECOND, 0);

                    Date selectedDate = calendar.getTime();

                    long result = diaperDao.insertDiaper(selectedDate, pee, poop);

                    if (result != -1) {
                        Toast.makeText(DiaperActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(DiaperActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(DiaperActivity.this, "Falha ao salvar dados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DiaperActivity.this, "Por favor selecione uma opção", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
