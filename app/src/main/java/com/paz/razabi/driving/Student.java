package com.paz.razabi.driving;

public class Student {
    private String Name;
    private String id;
    private String address;
    private String phone;
    private int lessonCount;
    private int unpaidLessonCount;
    private String teacher;



    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public int getUnpaidLessonCount() {
        return unpaidLessonCount;
    }

    public void setUnpaidLessonCount(int unpaidLessonCount) {
        this.unpaidLessonCount = unpaidLessonCount;
    }
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }


    public Student() {
        this.Name = null;
        this.id = null;
        this.address = null;
        this.phone = null;
        this.lessonCount = 0;
        this.unpaidLessonCount = 0;
        this.teacher = null;
    }
    public Student(Student student){
        this.Name = student.getName();
        this.id = student.getId();
        this.phone = student.getPhone();
        this.address = student.getAddress();
        this.lessonCount = student.getLessonCount();
        this.unpaidLessonCount = student.getUnpaidLessonCount();
        this.teacher = student.getTeacher();
    }



    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
