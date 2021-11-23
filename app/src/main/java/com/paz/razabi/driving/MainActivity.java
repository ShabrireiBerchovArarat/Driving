package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast timerToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
        new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {
                timerToast.cancel();
                timerToast.setText("Wait... ");
                timerToast.show();
            }

            @Override
            public void onFinish() {
                Intent go = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(go);
            }
        }.start();
    }
}