package com.example.gonfimbl.retrofit_example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.gonfimbl.retrofit_example.services.GetUserService;

import static com.example.gonfimbl.retrofit_example.services.GetUserService.SHOW_USER_ACTION;

public class MainActivity extends AppCompatActivity {

    Button button;

    // Создаем broadcast receiver для прослушивания сообщений
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            String surname = intent.getStringExtra("surname");
            String birthDate = intent.getStringExtra("birthdate");
            Toast.makeText(MainActivity.this, String.format("%s %s %s", name, surname, birthDate), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Регистрируем наш ресивер на прослушивание определенного Intent
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(SHOW_USER_ACTION));

        View.OnClickListener myButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GetUserService.class);
                startService(intent);
            }
        };

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(myButtonClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
