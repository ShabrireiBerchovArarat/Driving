package com.paz.razabi.driving;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecordCoordinator {
    public static void coordinateStudentRecords(Student s) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Student student = childSnapshot.getValue(Student.class);
                    String key;
                    if (student.getTeacher().equals(Globals.currTeacher)) {
                        if (student.getName().equals(s.getName())) {
                            key = childSnapshot.getKey();
                            new FirebaseDBHelper().updateStudent(key, s, new FirebaseDBHelper.DataStatus() {
                                @Override
                                public void DataIsLoaded(List<Student> students, List<String> keys) {
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
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void coordinateLessonRecords(Student s) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("lessons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Lesson lesson = childSnapshot.getValue(Lesson.class);
                    String key;
                    if (lesson.getStudent().getTeacher().equals(Globals.currTeacher)) {
                        if (lesson.getStudent().getId().equals(s.getId())) {
                            key = childSnapshot.getKey();
                            lesson.setStudent(s);
                            new LessFirebaseDBHelper().updateLesson(key, lesson, new LessFirebaseDBHelper.LessDataStatus() {
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

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
