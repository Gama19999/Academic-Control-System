package com.example.restservice.student.services;

import com.example.restservice.pojos.NewUser;
import com.example.restservice.student.entities.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

public interface StudentService {
    Student findById(int id);

    ArrayList<Student> findAll();

    void save(Student student);

    void deleteById(int deletedStudentId);

    String addStudent(String name, String lastName, int grade, Date since, String nip);

    void deleteStudentData(int deletedStudId);

    Student buildObj(NewUser newUser);
}
