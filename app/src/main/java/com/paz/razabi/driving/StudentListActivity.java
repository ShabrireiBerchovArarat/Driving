package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StudentListActivity extends AppCompatActivity {
    private ArrayList<Student> studentList;
    private RecyclerView recyclerView;
    FirebaseFirestore db;
    recyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        recyclerView = findViewById(R.id.slRecyclerView);
        studentList = new ArrayList<>();



        recyclerAdapter adapter = new recyclerAdapter(studentList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        db = FirebaseFirestore.getInstance();
        studentList = new ArrayList<Student>();
        recyclerAdapter = new recyclerAdapter( studentList);

//        EventChangeListener();
    }

//    private void EventChangeListener() {
//        db.collection()
//    }

}