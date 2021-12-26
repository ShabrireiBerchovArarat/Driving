package com.paz.razabi.driving;

import java.util.Date;

public class Lesson {
    private Date lDate;
    private Student student;
    private boolean isPaid;
    private boolean isOver;

    public Lesson(Date lDate, Student student) {
        this.lDate = lDate;
        this.student = student;
    }

    public Date getlDate() {
        return lDate;
    }

    public void setlDate(Date lDate) {
        this.lDate = lDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
