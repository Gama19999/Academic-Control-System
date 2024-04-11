package com.example.restservice.student.repositories;

import com.example.restservice.pojos.NewUser;
import com.example.restservice.pojos.User;
import com.example.restservice.student.DAOs.StudentDAO;
import com.example.restservice.student.entities.Student;
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
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public Student findById(int id) {
        Session currSession = entityManager.unwrap(Session.class);
        return currSession.get(Student.class, id);
    }

    @Override
    public ArrayList<Student> findAll() {
        Session currSession = entityManager.unwrap(Session.class);
        Query<Student> query = currSession.createQuery("SELECT s FROM student s", Student.class);
        List<Student> students = query.getResultList();
        return new ArrayList<>(students);
    }

    @Override
    public void save(Student student) {
        Session currSession = entityManager.unwrap(Session.class);
        currSession.merge(student);
    }

    @Override
    public void deleteById(int deletedStudentId) {
        Session currSession = entityManager.unwrap(Session.class);
        MutationQuery query = currSession.createNativeMutationQuery("delete from student where student_id = ?1");
        query.setParameter(1, deletedStudentId);
        query.executeUpdate();
    }

    @Override
    public String addStudent(String name, String lastName, int grade, Date since, String nip) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_add_student");
        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(4, Date.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(6, Integer.class, ParameterMode.OUT);
        sp.setParameter(1, name);
        sp.setParameter(2, lastName);
        sp.setParameter(3, grade);
        sp.setParameter(4, since);
        sp.setParameter(5, nip);
        sp.execute();
        var newStudentId = String.valueOf(sp.getOutputParameterValue(6));
        System.out.printf("New student ID: %s", newStudentId);
        return newStudentId;
    }

    @Override
    public void deleteStudentData(int deletedStudId) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("sp_delete_student_data");
        sp.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        sp.setParameter(1, deletedStudId);
        sp.execute();
    }
}
