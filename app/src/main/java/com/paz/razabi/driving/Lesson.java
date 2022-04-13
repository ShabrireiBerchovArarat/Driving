package com.paz.razabi.driving;

import java.util.Date;

public class Lesson{
    private Student student;
    private String date;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "student=" + student +
                ", date='" + date + '\'' +
                '}';
    }

    public Lesson() {
        this.date = null;
        this.student = null;
    }


    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
