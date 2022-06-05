package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class StudentListActivity extends HomeMenuTemplateActivity {
    private RecyclerView recyclerView;
    String masterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        masterName = Globals.currTeacher;
        recyclerView = findViewById(R.id.slRecyclerView);
        new FirebaseDBHelper().readStudents(new FirebaseDBHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> students, List<String> keys) {
                findViewById(R.id.loadStPb).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(recyclerView, StudentListActivity.this, students, keys);
            }


            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        }, masterName);


    }
}