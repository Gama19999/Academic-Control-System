package com.example.restservice.signaturestudent.attendance.repositories;

import com.example.restservice.signaturestudent.attendance.DAOs.AttendanceDAO;
import com.example.restservice.signaturestudent.attendance.entities.Attendance;
import com.example.restservice.signaturestudent.calif.entities.Calif;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceDAOImpl implements AttendanceDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public ArrayList<Attendance> findAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Attendance> query = currSession.createQuery("SELECT a FROM attendance a", Attendance.class);
        List<Attendance> attendances = query.getResultList();
        return new ArrayList<>(attendances);
    }

    @Override
    @Transactional
    public void save(Attendance attendance) {
        entityManager.merge(attendance);
    }
}
