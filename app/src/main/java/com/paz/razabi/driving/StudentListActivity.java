package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String masterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
//        if(savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if (extras == null) {
//                masterName = null;
//            } else {
//                masterName = extras.getString("STRING_I_NEED");
//            }
//        }else {
//                masterName = (String) savedInstanceState.getSerializable("STRING_I_NEED");
//            }
        masterName = Globals.currTeacher;
        recyclerView = findViewById(R.id.slRecyclerView);
        new FirebaseDBHelper().readStudents(new FirebaseDBHelper.DataStatus() {
        @Override
        public void DataIsLoaded (List < Student > students, List < String > keys){
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