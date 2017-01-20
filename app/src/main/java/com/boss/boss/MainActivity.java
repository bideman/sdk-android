package com.boss.boss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boss.stat.LoggerWatcher;

public class MainActivity extends AppCompatActivity {
    private LoggerWatcher loggerWatcher = new LoggerWatcher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggerWatcher.startTracking(this, "app1", "key1", "200101");

        Button reg = (Button) findViewById(R.id.button_register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggerWatcher.trackRegistration(MainActivity.this, "user1");
            }
        });

        Button login = (Button) findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggerWatcher.trackLogin(MainActivity.this, "user1");
            }
        });

        Button payment = (Button) findViewById(R.id.button_payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggerWatcher.trackPurchase(MainActivity.this, "user1", "20170120001", "alipay", "CNY", "1.00");
            }
        });

        Button custom = (Button) findViewById(R.id.button_custom);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggerWatcher.trackCustom(MainActivity.this, "event1", "event_value1", "user1");
            }
        });
    }
}
