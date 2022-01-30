package com.paz.razabi.driving;

public class Student {
    private String Name;
    private String id;
    private String address;
    private String phone;
    private int lessonCount;
    private int unpaidLessonCount;


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

    public Student(String Name, String id, String address, String phone) {
        this.Name = Name;
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.lessonCount = 0;
        this.unpaidLessonCount = 0;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public int getUnpaidLessonCount() {
        return unpaidLessonCount;
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
