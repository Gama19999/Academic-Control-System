package com.example.restservice.signature.repositories;

import com.example.restservice.signature.DAOs.SignatureDAO;
import com.example.restservice.signature.entities.Signature;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SignatureDAOImpl implements SignatureDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Signature findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Signature.class, id);
    }

    @Override
    public ArrayList<Signature> findAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Signature> query = currSession.createQuery("SELECT s FROM signature s", Signature.class);
        List<Signature> signatures = query.getResultList();
        return new ArrayList<>(signatures);
    }

    @Override
    @Transactional
    public void save(Signature signature) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(signature);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        MutationQuery query = currSession.createNativeMutationQuery("delete from signature where signature_id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();
    }
}
