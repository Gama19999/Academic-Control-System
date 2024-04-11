package com.example.restservice.teacher.DAOs;

import com.example.restservice.teacher.entities.Teacher;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

public interface TeacherDAO {

    Teacher findById(int id);

    ArrayList<Teacher> findAll();

    @Transactional
    void save(Teacher teacher);

    @Transactional
    void deleteById(int deletedTeacherId);

    String addTeacher(String name, String lastName, int ofGroup, Date since, String nip);

    void deleteTeacherData(int deletedTeachId);
}
