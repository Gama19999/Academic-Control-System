package com.example.restservice.admin.repositories;

import com.example.restservice.admin.DAOs.AdminDAO;
import com.example.restservice.admin.entities.Admin;
import com.example.restservice.student.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Admin findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Admin.class, id);
    }
}
