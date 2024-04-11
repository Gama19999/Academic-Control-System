package com.example.restservice.student.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "student")
public class Student {

    @Id
    public int studentId;

    public String name;
    public String lastName;
    public int grade;
    public boolean status;
    public Date since;

    public Student() {}

    public Student(int studentId, String name, String lastName, int grade, boolean status, Date since) {
        this.studentId = studentId;
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
        this.status = status;
        this.since = since;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}
