package com.example.restservice.teacher.repositories;

import com.example.restservice.teacher.DAOs.TeacherDAO;
import com.example.restservice.teacher.entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Teacher findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Teacher.class, id);
    }

    @Override
    public ArrayList<Teacher> findAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Teacher> query = currSession.createQuery("SELECT t FROM teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        return new ArrayList<>(teachers);
    }

    @Override
    public void save(Teacher teacher) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(teacher);
    }

    @Override
    public void deleteById(int deletedTeacherId) {
        Session currSession = entityManager.unwrap(Session.class);
        MutationQuery query = currSession.createNativeMutationQuery("delete from teacher where teacher_id = ?1");
        query.setParameter(1, deletedTeacherId);
        query.executeUpdate();
    }

    @Override
    public String addTeacher(String name, String lastName, int ofGroup, Date since, String nip) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_add_teacher");
        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(4, Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(6, Integer.class, ParameterMode.OUT);
        sp.setParameter(1, name);
        sp.setParameter(2, lastName);
        sp.setParameter(3, ofGroup);
        sp.setParameter(4, since);
        sp.setParameter(5, nip);
        sp.execute();
        var newTeacherId = String.valueOf(sp.getOutputParameterValue(6));
        System.out.printf("New teacher ID: %s", newTeacherId);
        return newTeacherId;
    }

    @Override
    public void deleteTeacherData(int deletedTeachId) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_delete_teacher_data");
        sp.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        sp.setParameter(1, deletedTeachId);
        sp.execute();
    }
}
