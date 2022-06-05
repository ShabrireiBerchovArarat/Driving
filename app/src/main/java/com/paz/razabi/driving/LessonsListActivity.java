package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class LessonsListActivity extends HomeMenuTemplateActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_list);
        recyclerView = findViewById(R.id.llRecyclerView);
        new LessFirebaseDBHelper().readLessons(new LessFirebaseDBHelper.LessDataStatus() {
            @Override
            public void LessDataIsLoaded(List<Lesson> lessons, List<String> keys) {
                findViewById(R.id.loadLessPb).setVisibility(View.GONE);
                new LessRecyclerView_Config().setConfig(recyclerView, LessonsListActivity.this, lessons, keys);

            }

            @Override
            public void LessDataIsInserted() {

            }

            @Override
            public void LessDataIsUpdated() {

            }

            @Override
            public void LessDataIsDeleted() {

            }
        }, Globals.currTeacher);
    }
}