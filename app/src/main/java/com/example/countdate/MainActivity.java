package com.example.countdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView textViewShowDay;
    EditText editDay1;
    Button Chose;
    Calendar calendar1 = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewShowDay = (TextView) findViewById(R.id.textShowDay);
        editDay1 = (EditText) findViewById(R.id.editTextDay_1);
        Chose = (Button) findViewById(R.id.buttonAccept);
        Chose.setText("Xác nhận");
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        editDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChoseDay();
            }
        });
        Chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = CountDay();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Day", time);
                startActivity(intent);
            }
        });
    }
    private void ChoseDay(){

        int ngay = calendar2.get(Calendar.DATE);
        int thang = calendar2.get(Calendar.MONTH);
        int nam = calendar2.get(Calendar.YEAR);
        calendar2.set(nam,thang,ngay);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar1.set(year,month,dayOfMonth);
                editDay1.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        }, nam,thang , ngay);
        datePickerDialog.show();
    }
    private long CountDay(){
        long count=0;
        count = calendar1.getTimeInMillis() - calendar2.getTimeInMillis() ;
        if (count<0) return -1*count/86400000;
        return count/86400000;
    }


}