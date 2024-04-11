package com.example.restservice.teacher.services;

import com.example.restservice.pojos.NewUser;
import com.example.restservice.teacher.entities.Teacher;

import java.util.ArrayList;
import java.util.Date;

public interface TeacherService {

    Teacher findById(int id);

    ArrayList<Teacher> findAll();

    void save(Teacher teacher);

    void deleteById(int deletedTeacherId);

    String addTeacher(String name, String lastName, int ofGroup, Date since, String nip);

    void deleteTeacherData(int deletedTeachId);

    Teacher buildObj(NewUser newUser);
}
