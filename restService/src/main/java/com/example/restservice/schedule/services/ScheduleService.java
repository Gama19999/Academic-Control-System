package com.example.restservice.schedule.services;

import com.example.restservice.schedule.entities.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public interface ScheduleService {
    Schedule findById(int id);

    ArrayList<Schedule> findAll();

    String getScheduleTimeById(int id);

    int addSchedule(Schedule schedule);

    void deleteById(int id);
}
