package com.example.restservice.signaturestudent.attendance.services;

import com.example.restservice.pojos.SingleAttendance;
import com.example.restservice.signaturestudent.attendance.DAOs.AttendanceDAO;
import com.example.restservice.signaturestudent.attendance.entities.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceDAO attendanceDAO;

    @Override
    public ArrayList<Attendance> findAll() {
        return attendanceDAO.findAll();
    }

    @Override
    public void save(Attendance attendance) {
        attendanceDAO.save(attendance);
    }

    @Override
    public Attendance buildObj(SingleAttendance attendance) {
        return new Attendance(attendance);
    }
}
