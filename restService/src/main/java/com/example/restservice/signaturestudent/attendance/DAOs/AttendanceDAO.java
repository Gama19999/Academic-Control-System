package com.example.restservice.signaturestudent.attendance.DAOs;

import com.example.restservice.signaturestudent.attendance.entities.Attendance;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface AttendanceDAO {
    ArrayList<Attendance> findAll();

    @Transactional
    void save(Attendance attendance);
}
