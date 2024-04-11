package com.example.restservice.auth.repositories;

import com.example.restservice.auth.DAOs.AuthStudentDAO;
import com.example.restservice.auth.entities.AuthAdmin;
import com.example.restservice.auth.entities.AuthStudent;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AuthStudentDAOImpl implements AuthStudentDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public AuthStudent findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(AuthStudent.class, id);
    }

    @Override
    @Transactional
    public void save(AuthStudent authStudent) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(authStudent);
    }
}
