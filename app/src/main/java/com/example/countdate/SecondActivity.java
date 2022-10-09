package com.example.countdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    long time=0;
    TextView day;
    int i=0;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        day = (TextView) findViewById(R.id.textDay);
        btn = (Button) findViewById(R.id.buttonSecond);
        Intent intent1 = getIntent();
        time = intent1.getLongExtra("Day",time);
        day.setText(time +"");
        if(time==0){
            day.setText("Nhấn để nhập ngày");
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });

        }
        else {
            CountDownTimer count = new CountDownTimer(2000000,10) {
                @Override
                public void onTick(long millisUntilFinished) {
                    i++;
                    day.setText(i + "");
                    if (i> time) this.onFinish();

                }

                @Override
                public void onFinish() {
                    day.setText(time + "");
                }
            };
            count.start();
        }
    }
}