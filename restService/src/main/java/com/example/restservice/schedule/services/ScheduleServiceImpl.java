package com.example.restservice.schedule.services;

import com.example.restservice.schedule.DAOs.ScheduleDAO;
import com.example.restservice.schedule.entities.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleDAO scheduleDAO;

    @Override
    public Schedule findById(int id) {
        return scheduleDAO.findById(id);
    }

    @Override
    public ArrayList<Schedule> findAll() {
        return scheduleDAO.findAll();
    }

    @Override
    public String getScheduleTimeById(int id) {
        return scheduleDAO.getScheduleTimeById(id);
    }

    @Override
    public int addSchedule(Schedule schedule) {
        return scheduleDAO.addSchedule(schedule);
    }

    @Override
    public void deleteById(int id) {
        scheduleDAO.deleteById(id);
    }
}
