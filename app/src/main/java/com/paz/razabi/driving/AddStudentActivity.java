package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddStudentActivity extends HomeMenuTemplateActivity {
    private EditText etName;
    private EditText etID;
    private EditText etPhone;
    private EditText etAddress;
    private Button addBt;
    String masterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        masterName = Globals.currTeacher;
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        addBt = findViewById(R.id.up_bt);

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etName.getText().toString().isEmpty() && !etAddress.getText().toString().isEmpty() && Verify.phoneRange(etPhone.getText().toString()) && Verify.idRange(etID.getText().toString())) {
                    Student student = new Student();
                    student.setName(etName.getText().toString());
                    student.setId(etID.getText().toString());
                    student.setPhone(etPhone.getText().toString());
                    student.setAddress(etAddress.getText().toString());
                    student.setLessonCount(0);
                    student.setUnpaidLessonCount(0);
                    student.setTeacher(masterName);
                    new FirebaseDBHelper().addStudent(student, new FirebaseDBHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Student> students, List<String> keys) {

                        }

                        @Override
                        public void DataIsInserted() {
                            Toast.makeText(AddStudentActivity.this, "The Student Has Been Inserted Successfully", Toast.LENGTH_SHORT).show();
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
        });
    }
}