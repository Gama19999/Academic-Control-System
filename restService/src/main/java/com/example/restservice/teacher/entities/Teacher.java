package com.example.restservice.teacher.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "teacher")
public class Teacher {
    @Id
    public int teacherId;

    public String name;
    public String lastName;
    public int ofGrade;
    public boolean status;
    public Date since;

    public Teacher() {}

    public Teacher(int teacherId, String name, String lastName, int ofGrade, boolean status, Date since) {
        this.teacherId = teacherId;
        this.name = name;
        this.lastName = lastName;
        this.ofGrade = ofGrade;
        this.status = status;
        this.since = since;
    }

    public int getOfGrade() {
        return ofGrade;
    }

    public void setOfGrade(int ofGrade) {
        this.ofGrade = ofGrade;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
