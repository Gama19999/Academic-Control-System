package com.example.restservice.auth.repositories;

import com.example.restservice.auth.DAOs.AuthTeacherDAO;
import com.example.restservice.auth.entities.AuthTeacher;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AuthTeacherDAOImpl implements AuthTeacherDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public AuthTeacher findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(AuthTeacher.class, id);
    }

    @Override
    @Transactional
    public void save(AuthTeacher authTeacher) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(authTeacher);
    }
}
