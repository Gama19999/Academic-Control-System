package com.example.restservice.schedule.repositories;

import com.example.restservice.schedule.DAOs.ScheduleDAO;
import com.example.restservice.schedule.entities.Schedule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Schedule findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Schedule.class, id);
    }

    @Override
    public ArrayList<Schedule> findAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Schedule> query = currSession.createQuery("SELECT s FROM schedule s", Schedule.class);
        List<Schedule> schedules = query.getResultList();
        return new ArrayList<>(schedules);
    }

    @Override
    public String getScheduleTimeById(int id) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_schedule_by_id");
        sp.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
        sp.setParameter(1, id);
        sp.execute();
        var time = (String) sp.getOutputParameterValue(2);
        System.out.println(time);
        return time;
    }

    @Override
    @Transactional
    public int addSchedule(Schedule schedule) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.merge(schedule).getScheduleId();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        MutationQuery query = currSession.createNativeMutationQuery("delete from schedule where schedule_id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();
    }
}
