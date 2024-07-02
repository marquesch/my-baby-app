package com.main.mybabyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.main.mybabyapp.data.dao.BottleFeedDao;
import com.main.mybabyapp.data.dao.BreastFeedDao;
import com.main.mybabyapp.data.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FeedActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private BottleFeedDao bottleFeedDao;
    private BreastFeedDao breastFeedDao;

    private TimePicker timePicker;
    private RadioGroup radioGroup;
    private RadioButton breastBtn, bottleBtn;
    private LinearLayout breastNumberLayout, bottleNumberLayout;
    private NumberPicker tensMinutePicker, minutePicker, hundredsMlPicker, tensMlPicker, mlPicker;
    private TextView minuteText, mlText;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        dbHelper = new DatabaseHelper(this);

        timePicker = findViewById(R.id.timePicker);
        radioGroup = findViewById(R.id.radioGroup);
        breastBtn = findViewById(R.id.breastBtn);
        bottleBtn = findViewById(R.id.bottleBtn);
        breastNumberLayout = findViewById(R.id.breastNumberLayout);
        bottleNumberLayout = findViewById(R.id.bottleNumberLayout);
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
        saveBtn = findViewById(R.id.saveBtn);

        breastNumberLayout.setVisibility(View.GONE);
        bottleNumberLayout.setVisibility(View.GONE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.breastBtn) {
                    breastNumberLayout.setVisibility(View.VISIBLE);
                    bottleNumberLayout.setVisibility(View.GONE);
                } else if (checkedId == R.id.bottleBtn) {
                    breastNumberLayout.setVisibility(View.GONE);
                    bottleNumberLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);

                Date selectedDate = calendar.getTime();

                if (breastBtn.isChecked()) {
                    int tensMinutes = tensMinutePicker.getValue();
                    int minutes = minutePicker.getValue();
                    int minutesValue = tensMinutes * 10 + minutes;
                    breastFeedDao = new BreastFeedDao(dbHelper.getWritableDatabase());
                    long result = breastFeedDao.insertBreastFeed(selectedDate, minutesValue);

                    if (result != -1) {
                        Toast.makeText(FeedActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(FeedActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(FeedActivity.this, "Falha ao salvar dados", Toast.LENGTH_SHORT).show();
                    }

                } else if (bottleBtn.isChecked()) {
                    int hundredsMl = hundredsMlPicker.getValue();
                    int tensMl = tensMlPicker.getValue();
                    int ml = mlPicker.getValue();
                    int mlValue = hundredsMl * 100 + tensMl * 10 + ml;
                    bottleFeedDao = new BottleFeedDao(dbHelper.getWritableDatabase());
                    long result = bottleFeedDao.insertBottleFeed(mlValue, selectedDate);

                    if (result != -1) {
                        Toast.makeText(FeedActivity.this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(FeedActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(FeedActivity.this, "Falha ao salvar dados", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}