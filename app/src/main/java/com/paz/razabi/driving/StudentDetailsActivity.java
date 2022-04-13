package com.paz.razabi.driving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {
    private EditText etName, etPhone, etID, etAddress, etLC, etUL;
    private Button up_bt, del_bt;
    private String key, name, phone, id, address, teacher;
    private int lc, ul, nLc, nUl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        id = getIntent().getStringExtra("id");
        address = getIntent().getStringExtra("address");
        Bundle extras = getIntent().getExtras();
        lc = extras.getInt("lc");
        ul = extras.getInt("ul");
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etID = findViewById(R.id.etID);
        etAddress = findViewById(R.id.etAddress);
        etLC = findViewById(R.id.etLC);
        etUL = findViewById(R.id.etUL);
        etName.setText(name);
        etPhone.setText(phone);
        etID.setText(id);
        etAddress.setText(address);
        etLC.setText("" + lc);
        etUL.setText("" + ul);
        teacher = Globals.currTeacher;
        up_bt = findViewById(R.id.up_bt);
        del_bt = findViewById(R.id.del_bt);

        up_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               nLc = Integer.parseInt(etLC.getText().toString());
               nUl = Integer.parseInt(etUL.getText().toString());
                Student student = new Student();
                student.setName(etName.getText().toString());
                student.setPhone(etPhone.getText().toString());
                student.setId(etID.getText().toString());
                student.setAddress(etAddress.getText().toString());
                student.setLessonCount(nLc);
                student.setUnpaidLessonCount(nUl);
                student.setTeacher(teacher);
                new FirebaseDBHelper().updateStudent(key, student, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Student> students, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(StudentDetailsActivity.this, "The Student Has Been Updated Successfully!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                Intent i = new Intent(StudentDetailsActivity.this , HomeActivity.class);
//                i.putExtra("INT_I_NEED", c1);
//                i.putExtra("STRING_I_NEED", teacher);
                startActivity(i);
            }
        });
        del_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDBHelper().deleteStudent(key, new FirebaseDBHelper.DataStatus() {
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
                    Toast.makeText(StudentDetailsActivity.this, "The Student Has Been Deleted Successfully!", Toast.LENGTH_LONG).show();

                    }
                });
                Intent i = new Intent(StudentDetailsActivity.this , HomeActivity.class);
//                i.putExtra("INT_I_NEED", c1);
//                i.putExtra("STRING_I_NEED", teacher);
                startActivity(i);
            }
        });
    }
}