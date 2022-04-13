package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etID;
    private EditText etPhone;
    private EditText etAddress;
    private Button addBt;
//    private  int c1 = 1;
    String masterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
//        if(savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if (extras == null) {
//                masterName = null;
//            } else {
//                masterName = extras.getString("STRING_I_NEED");
//            }
//        }else {
//            masterName = (String) savedInstanceState.getSerializable("STRING_I_NEED");
//        }
        masterName = Globals.currTeacher;
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        addBt = findViewById(R.id.up_bt);

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent i = new Intent(AddStudentActivity.this , HomeActivity.class);
//                i.putExtra("INT_I_NEED", c1);
//                i.putExtra("STRING_I_NEED", masterName);
                startActivity(i);
            }
        });
    }
}