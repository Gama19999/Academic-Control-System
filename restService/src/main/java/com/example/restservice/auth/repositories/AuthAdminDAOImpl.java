package com.example.restservice.auth.repositories;

import com.example.restservice.auth.DAOs.AuthAdminDAO;
import com.example.restservice.auth.entities.AuthAdmin;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthAdminDAOImpl implements AuthAdminDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public AuthAdmin findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(AuthAdmin.class, id);
    }
}
