package com.paz.razabi.driving;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LessFirebaseDBHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrenceLessons;
    private List<Lesson> lessons = new ArrayList<>();


    public interface LessDataStatus{
        void LessDataIsLoaded(List<Lesson> lessons, List<String> keys);
        void LessDataIsInserted();
        void LessDataIsUpdated();
        void LessDataIsDeleted();
    }

    public LessFirebaseDBHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefrenceLessons = mDatabase.getReference("lessons");
    }


    public void readLessons(LessFirebaseDBHelper.LessDataStatus dataStatus, String str){
        mRefrenceLessons.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lessons.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Lesson lesson = keyNode.getValue(Lesson.class);
                    if(lesson.getStudent().getTeacher().equals(str)) {
                        lessons.add(lesson);
                    }
                }
                dataStatus.LessDataIsLoaded(lessons,keys);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void addLesson(Lesson lesson , final LessFirebaseDBHelper.LessDataStatus dataStatus){
        lesson.getStudent().setLessonCount((lesson.getStudent().getLessonCount() + 1));
        String key = mRefrenceLessons.push().getKey();
        mRefrenceLessons.child(key).setValue(lesson)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.LessDataIsInserted();
                    }
                });

    }
    public void updateLesson(String key, Lesson lesson, final LessFirebaseDBHelper.LessDataStatus dataStatus){
        mRefrenceLessons.child(key).setValue(lesson)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.LessDataIsUpdated();
                    }
                });
    }
    public void deleteLesson(String key, final LessFirebaseDBHelper.LessDataStatus dataStatus){
        mRefrenceLessons.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.LessDataIsDeleted();
                    }
                });
    }
}
