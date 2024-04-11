package com.example.restservice.student.services;

import com.example.restservice.pojos.NewUser;
import com.example.restservice.student.DAOs.StudentDAO;
import com.example.restservice.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public ArrayList<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void deleteById(int deletedStudentId) {
        studentDAO.deleteById(deletedStudentId);
    }

    @Override
    public String addStudent(String name, String lastName, int grade, Date since, String nip) {
        return studentDAO.addStudent(name, lastName, grade, since, nip);
    }

    @Override
    public void deleteStudentData(int deletedStudId) {
        studentDAO.deleteStudentData(deletedStudId);
    }

    @Override
    public Student buildObj(NewUser newUser) {
        var data = newUser.getData();
        return new Student(
                data.getEntityId(),
                data.getName(),
                data.getLastName(),
                data.getGrade(),
                data.getStatus(),
                new Date(data.getSince())
        );
    }
}
