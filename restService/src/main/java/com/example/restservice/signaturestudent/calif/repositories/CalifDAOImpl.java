package com.example.restservice.signaturestudent.calif.repositories;

import com.example.restservice.signaturestudent.calif.DAOs.CalifDAO;
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
public class CalifDAOImpl implements CalifDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Calif findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Calif.class, id);
    }

    @Override
    public ArrayList<Calif> findAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Calif> query = currSession.createQuery("SELECT c FROM calif c", Calif.class);
        List<Calif> califs = query.getResultList();
        return new ArrayList<>(califs);
    }

    @Override
    @Transactional
    public void save(Calif calif) {
        entityManager.merge(calif);
    }
}
