package com.zhengfu.chatappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tvTimer;
    private Button btnStart;
    private Button btnStop;
    private Format decimailFormat;

    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
                startTimer();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });

    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRunning) {
                            if(decimailFormat == null){
                                decimailFormat = new DecimalFormat("00");
                            }
                            seconds++;
                            String hours = decimailFormat.format(seconds / 3600);
                            String minutes = decimailFormat.format(seconds % 3600 /60);
                            String sec = decimailFormat.format(seconds % 60);
                            Log.e("time", "run: "+hours+minutes+sec);
                            tvTimer.setText(hours+":"+minutes+":"+sec);
                        }
                    }
                });
            }
        }, 0, 1000);
    }

}