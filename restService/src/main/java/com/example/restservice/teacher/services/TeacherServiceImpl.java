package com.example.restservice.teacher.services;

import com.example.restservice.pojos.NewUser;
import com.example.restservice.teacher.DAOs.TeacherDAO;
import com.example.restservice.teacher.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDAO teacherDAO;

    @Override
    public Teacher findById(int id) {
        return teacherDAO.findById(id);
    }

    @Override
    public ArrayList<Teacher> findAll() {
        return teacherDAO.findAll();
    }

    @Override
    public void save(Teacher teacher) {
        teacherDAO.save(teacher);
    }

    @Override
    public void deleteById(int deletedTeacherId) {
        teacherDAO.deleteById(deletedTeacherId);
    }

    @Override
    public String addTeacher(String name, String lastName, int ofGroup, Date since, String nip) {
        return teacherDAO.addTeacher(name, lastName, ofGroup, since, nip);
    }

    @Override
    public void deleteTeacherData(int deletedTeachId) {
        teacherDAO.deleteTeacherData(deletedTeachId);
    }

    @Override
    public Teacher buildObj(NewUser newUser) {
        var data = newUser.getData();
        return new Teacher(
                data.getEntityId(),
                data.getName(),
                data.getLastName(),
                data.getGrade(),
                data.getStatus(),
                new Date(data.getSince())
        );
    }
}
