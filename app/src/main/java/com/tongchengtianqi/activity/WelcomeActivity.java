package com.tongchengtianqi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tongchengtianqi.R;
import com.tongchengtianqi.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private TextView countdown;
    private int recLen = 5;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        countdown = (TextView) findViewById(R.id.countdown);
        countdown.setTextColor(Color.RED);
        countdown.setText(recLen + "秒|跳过");
        timer.schedule(task, 1000, 1000);
        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                countdown.setVisibility(View.GONE);
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    countdown.setTextColor(Color.RED);
                    countdown.setText(recLen + "秒|跳过");
                    recLen--;

                    if (recLen < 0) {
                        timer.cancel();
                        countdown.setVisibility(View.GONE);
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    };
}
