package com.example.restservice.schedule.DAOs;

import com.example.restservice.schedule.entities.Schedule;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ScheduleDAO {
    Schedule findById(int id);

    ArrayList<Schedule> findAll();

    String getScheduleTimeById(int id);

    @Transactional
    int addSchedule(Schedule schedule);

    @Transactional
    void deleteById(int id);
}
