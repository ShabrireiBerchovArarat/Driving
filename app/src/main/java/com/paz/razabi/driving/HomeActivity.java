package com.paz.razabi.driving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends HomeMenuTemplateActivity implements ExampleDialog.ExampleDialogListener {
    private TextView tvWellMaster , tvSL , tvAAS , tvLL , tvSAL;
    private String masterName;
    private Button bt;
//    private  int c = 0;
//    private boolean b = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvWellMaster = findViewById(R.id.tv_well_master);
        tvSL = findViewById(R.id.tvSL);
        tvAAS = findViewById(R.id.tvAAS);
        tvLL = findViewById(R.id.tvLL);
        tvSAL = findViewById(R.id.tvSAL);
        bt = findViewById(R.id.bt);

        if(Globals.firstEnter) {
            tvSL.setVisibility(View.GONE);
            tvAAS.setVisibility(View.GONE);
            tvLL.setVisibility(View.GONE);
            tvSAL.setVisibility(View.GONE);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog();
                    bt.setVisibility(View.GONE);
                    tvSL.setVisibility(View.VISIBLE);
                    tvAAS.setVisibility(View.VISIBLE);
                    tvLL.setVisibility(View.VISIBLE);
                    tvSAL.setVisibility(View.VISIBLE);
                }
            });
            Globals.firstEnter = false;
        }
        else {
            bt.setVisibility(View.GONE);
            tvWellMaster.setText("Welcome " + Globals.currTeacher);
        }


    }

    private void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public void sList(View view) {
        Intent i = new Intent(HomeActivity.this , StudentListActivity.class);
        i.putExtra("STRING_I_NEED", masterName);
        startActivity(i);
    }

    public void sLesson(View view) {
        startActivity(new Intent(HomeActivity.this , ScheduleActivity.class));
    }

    public void lList(View view) {
        startActivity(new Intent(HomeActivity.this , LessonsListActivity.class));
    }

    public void addStudent(View view) {
        Intent i = new Intent(HomeActivity.this , AddStudentActivity.class);
        i.putExtra("STRING_I_NEED", masterName);
        startActivity(i);
    }

    @Override
    public void applyText(String master) {
        tvWellMaster.setText("Welcome " + master);
        masterName = master;
        Globals.currTeacher = master;
    }
}