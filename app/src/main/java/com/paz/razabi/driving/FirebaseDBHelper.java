package com.paz.razabi.driving;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDBHelper {
    // step_2
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrenceStudents;
    private ArrayList<Student> students = new ArrayList<>();

    // step_3
    public FirebaseDBHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefrenceStudents = mDatabase.getReference("students");
    }

    //step_4
    public interface DataStatus {
        void DataIsLoaded(ArrayList<Student> students, List<String> keys);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    // step_5
    public void readStudents(DataStatus dataStatus) {
        mRefrenceStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                students.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Student student = keyNode.getValue(Student.class);
                    students.add(student);
                }
                dataStatus.DataIsLoaded(students, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); // don't forget ;

    }

    // Add Book step11
    public void addStudent(Student student, final DataStatus dataStatus) {
        String key = mRefrenceStudents.push().getKey();
        mRefrenceStudents.child(key).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dataStatus.DataIsInserted();
            }
        }); // dont forget ;
    }

    public void updateStudent(String key, Student student, final DataStatus dataStatus) {
        mRefrenceStudents.child(key).setValue(student).
                addOnSuccessListener(unused -> dataStatus.DataIsUpdated()); // dont forget ;
    }

    public void deleteStudent(String key, final DataStatus dataStatus) {
        mRefrenceStudents.child(key).setValue(null).
                addOnSuccessListener(unused -> dataStatus.DataIsDeleted()); // dont forget ;
    }
}
