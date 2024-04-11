package com.example.restservice.student.DAOs;

import com.example.restservice.pojos.NewUser;
import com.example.restservice.pojos.User;
import com.example.restservice.student.entities.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

public interface StudentDAO {

    Student findById(int id);

    ArrayList<Student> findAll();

    @Transactional
    void save(Student student);

    @Transactional
    void deleteById(int deletedStudentId);

    String addStudent(String name, String lastName, int grade, Date since, String nip);

    void deleteStudentData(int deletedStudId);
}
