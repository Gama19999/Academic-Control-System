package com.example.restservice.signaturestudent.attendance.services;

import com.example.restservice.pojos.SingleAttendance;
import com.example.restservice.signaturestudent.attendance.entities.Attendance;

import java.util.ArrayList;

public interface AttendanceService {
    ArrayList<Attendance> findAll();

    void save(Attendance attendance);

    Attendance buildObj(SingleAttendance attendance);
}
