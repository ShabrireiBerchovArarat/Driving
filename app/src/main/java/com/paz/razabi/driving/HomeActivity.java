package com.paz.razabi.driving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends HomeMenuTemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void sList(View view) {
        startActivity(new Intent(HomeActivity.this , StudentListActivity.class));
    }

    public void sLesson(View view) {
        startActivity(new Intent(HomeActivity.this , ScheduleActivity.class));
    }
}