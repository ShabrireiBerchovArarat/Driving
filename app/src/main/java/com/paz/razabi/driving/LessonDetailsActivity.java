package com.paz.razabi.driving;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LessonDetailsActivity extends AppCompatActivity {
    private TextView tvCurrLessDis;
    private EditText etNewMonth, etNewDay, etNewHour;
    private Spinner new_sp;
    private Button up_less_bt, del_less_bt;
    private String key, currName, currDate, newDate;
    private Student s1;
    private DatabaseReference databaseReference, databaseReference2;
    private List<String> names;
    private Lesson lesson = new Lesson();
    private AlertDialog.Builder builder1, builder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_details);
        builder1 = new AlertDialog.Builder(LessonDetailsActivity.this);
        builder2 = new AlertDialog.Builder(LessonDetailsActivity.this);
        builder1.setTitle("Delete")
                .setMessage("Is The Lesson Over?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference2 = FirebaseDatabase.getInstance().getReference();
                        databaseReference2.child("students").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot childSnapshot2 : snapshot.getChildren()) {
                                    Student student = childSnapshot2.getValue(Student.class);
                                    Log.i("BBB", student.getName() + " ," + new_sp.getSelectedItem().toString());
                                    if (student.getTeacher().equals(Globals.currTeacher)){
                                        if (student.getName().equals(new_sp.getSelectedItem().toString())) {
                                            Log.i("BBB", student.toString());
                                            student.setLessonCount((student.getLessonCount() + 1));
                                            builder2.show();
                                        }
                                    }
                                }
                            }



                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new LessFirebaseDBHelper().deleteLesson(key, new LessFirebaseDBHelper.LessDataStatus() {
                            @Override
                            public void LessDataIsLoaded(List<Lesson> lessons, List<String> keys) {

                            }

                            @Override
                            public void LessDataIsInserted() {

                            }

                            @Override
                            public void LessDataIsUpdated() {

                            }

                            @Override
                            public void LessDataIsDeleted() {
                                Toast.makeText(LessonDetailsActivity.this , "The Lesson Has Been Deleted Successfully!", Toast.LENGTH_LONG).show();

                            }
                        });
                        Intent j = new Intent(LessonDetailsActivity.this, HomeActivity.class);
                        startActivity(j);
                    }
                });
        builder2.setTitle("Delete")
                .setMessage("Is The Lesson Paid?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new LessFirebaseDBHelper().deleteLesson(key, new LessFirebaseDBHelper.LessDataStatus() {
                            @Override
                            public void LessDataIsLoaded(List<Lesson> lessons, List<String> keys) {

                            }

                            @Override
                            public void LessDataIsInserted() {

                            }

                            @Override
                            public void LessDataIsUpdated() {

                            }

                            @Override
                            public void LessDataIsDeleted() {
                                Toast.makeText(LessonDetailsActivity.this , "The Lesson Has Been Deleted Successfully!", Toast.LENGTH_LONG).show();

                            }
                        });
                        Intent j = new Intent(LessonDetailsActivity.this, HomeActivity.class);
                        startActivity(j);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference2 = FirebaseDatabase.getInstance().getReference();
                        databaseReference2.child("students").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for (DataSnapshot childSnapshot2 : snapshot.getChildren()) {
                                    Student student = childSnapshot2.getValue(Student.class);
                                    Log.i("BBB", student.getName() + " ," + new_sp.getSelectedItem().toString());
                                    if (student.getTeacher().equals(Globals.currTeacher)){
                                        if (student.getName().equals(new_sp.getSelectedItem().toString())) {
                                            Log.i("BBB", student.toString());
                                            student.setUnpaidLessonCount((student.getUnpaidLessonCount() + 1));
                                            new LessFirebaseDBHelper().deleteLesson(key, new LessFirebaseDBHelper.LessDataStatus() {
                                                @Override
                                                public void LessDataIsLoaded(List<Lesson> lessons, List<String> keys) {

                                                }

                                                @Override
                                                public void LessDataIsInserted() {

                                                }

                                                @Override
                                                public void LessDataIsUpdated() {

                                                }

                                                @Override
                                                public void LessDataIsDeleted() {
                                                    Toast.makeText(LessonDetailsActivity.this , "The Lesson Has Been Deleted Successfully!", Toast.LENGTH_LONG).show();

                                                }
                                            });
                                            Intent i = new Intent(LessonDetailsActivity.this, HomeActivity.class);
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

        key = getIntent().getStringExtra("less_key");
        currName = getIntent().getStringExtra("less_name");
        currDate = getIntent().getStringExtra("less_date");
        tvCurrLessDis = findViewById(R.id.tvCurrLessDis);
        etNewMonth = findViewById(R.id.etNewMonth);
        etNewDay = findViewById(R.id.etNewDay);
        etNewHour = findViewById(R.id.etNewHour);
        new_sp = findViewById(R.id.new_sp);
        up_less_bt = findViewById(R.id.up_less_bt);
        del_less_bt = findViewById(R.id.del_less_bt);
        tvCurrLessDis.setText("The Current Lesson Is For " + currName + " at "+ currDate);
        names = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Student student = childSnapshot.getValue(Student.class);
                    String spinnerName;
                    if (student.getTeacher().equals(Globals.currTeacher)){
                        spinnerName = student.getName();
                        names.add(spinnerName);
                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(LessonDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, names);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                new_sp.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        up_less_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference2 = FirebaseDatabase.getInstance().getReference();
                databaseReference2.child("students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot childSnapshot2 : snapshot.getChildren()) {
                            Student student = childSnapshot2.getValue(Student.class);
                            Log.i("BBB", student.getName() + " ," + new_sp.getSelectedItem().toString());
                            if (student.getTeacher().equals(Globals.currTeacher)){
                                if (student.getName().equals(new_sp.getSelectedItem().toString())) {
                                    Log.i("BBB", student.toString());
                                    lesson.setStudent(student);
                                    lesson.setDate(etNewDay.getText().toString() + "/" + etNewMonth.getText().toString() + " , " + etNewHour.getText().toString());
                                    new LessFirebaseDBHelper().updateLesson(key, lesson, new LessFirebaseDBHelper.LessDataStatus() {
                                        @Override
                                        public void LessDataIsLoaded(List<Lesson> lessons, List<String> keys) {

                                        }

                                        @Override
                                        public void LessDataIsInserted() {

                                        }

                                        @Override
                                        public void LessDataIsUpdated() {
                                        Toast.makeText(LessonDetailsActivity.this , "The Lesson Has Been Updated Successfully!", Toast.LENGTH_LONG).show();
                                        }

                                        @Override
                                        public void LessDataIsDeleted() {

                                        }
                                    });
                                    Intent i = new Intent(LessonDetailsActivity.this, HomeActivity.class);
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
        del_less_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder1.show();
            }
        });
    }
}