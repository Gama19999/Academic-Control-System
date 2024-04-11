package com.example.restservice.signaturestudent.attendance.entities;

import com.example.restservice.pojos.SingleAttendance;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ssAttendanceId;

    public int signStudId;
    public Date date;
    public boolean attendance;

    public Attendance() {}

    public Attendance(SingleAttendance attendance) {
        signStudId = attendance.getSignStudId();
        date = new Date(attendance.getDate());
        this.attendance = attendance.getStatus();
    }

    public int getSignStudId() {
        return signStudId;
    }

    public void setSignStudId(int signStudId) {
        this.signStudId = signStudId;
    }

    public int getSsAttendanceId() {
        return ssAttendanceId;
    }

    public void setSsAttendanceId(int ssAttendanceId) {
        this.ssAttendanceId = ssAttendanceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }
}
