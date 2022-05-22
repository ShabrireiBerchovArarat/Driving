package com.paz.razabi.driving;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    private EditText etMonth, etDay, etHour;
    private Spinner spinner;
    private Button sc_bt;
    private DatabaseReference databaseReference, databaseReference2;
    private List<String> names;
    Lesson lesson = new Lesson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        spinner = findViewById(R.id.spinner);
        etMonth = findViewById(R.id.etMonth);
        etDay = findViewById(R.id.etDay);
        etHour = findViewById(R.id.etHour);
        sc_bt = findViewById(R.id.sc_bt);
        names = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Student student = childSnapshot.getValue(Student.class);
                    String spinnerName;
                    if (student.getTeacher().equals(Globals.currTeacher)) {
                        spinnerName = student.getName();
                        names.add(spinnerName);
                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ScheduleActivity.this, android.R.layout.simple_spinner_dropdown_item, names);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sc_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                databaseReference2 = FirebaseDatabase.getInstance().getReference();
                databaseReference2.child("students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot childSnapshot2 : snapshot.getChildren()) {
                            Student student = childSnapshot2.getValue(Student.class);
                            Log.i("BBB", student.getName() + " ," + spinner.getSelectedItem().toString());
                            if (student.getTeacher().equals(Globals.currTeacher)) {
                                if (student.getName().equals(spinner.getSelectedItem().toString())) {
                                    Log.i("BBB", student.toString());
                                    lesson.setStudent(student);
                                    lesson.setDate(etDay.getText().toString() + "/" + etMonth.getText().toString() + " , " + etHour.getText().toString());
                                    StringBuilder url = new StringBuilder();
                                    url.append("https://wa.me/");
                                    url.append(Formatting.formatPhone(lesson.getStudent().getPhone()));
                                    url.append("?text=");
                                    url.append(Formatting.encodeValue("New Lesson Has Been Scheduled For "));
                                    url.append(Formatting.encodeValue(lesson.getDate()));

                                    Uri uri = Uri.parse(url.toString());
                                    Intent send = new Intent(Intent.ACTION_VIEW, uri);

                                    startActivity(send);
                                    Log.i("LINK", url.toString());

                                    new LessFirebaseDBHelper().addLesson(lesson, new LessFirebaseDBHelper.LessDataStatus() {
                                        @Override
                                        public void LessDataIsLoaded(List<Lesson> lessons, List<String> keys) {

                                        }

                                        @Override
                                        public void LessDataIsInserted() {
                                            Toast.makeText(ScheduleActivity.this, "The Lesson Has Been Inserted Successfully", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void LessDataIsUpdated() {

                                        }

                                        @Override
                                        public void LessDataIsDeleted() {

                                        }
                                    });
                                    Intent i = new Intent(ScheduleActivity.this, HomeActivity.class);
                                    startActivity(i);
                                }
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}