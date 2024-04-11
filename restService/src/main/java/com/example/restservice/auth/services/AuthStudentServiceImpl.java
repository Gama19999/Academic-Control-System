package com.example.restservice.auth.services;

import com.example.restservice.auth.DAOs.AuthStudentDAO;
import com.example.restservice.auth.entities.AuthAdmin;
import com.example.restservice.auth.entities.AuthStudent;
import com.example.restservice.pojos.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthStudentServiceImpl implements AuthStudentService {

    @Autowired
    AuthStudentDAO authStudentDAO;

    @Override
    public AuthStudent findById(int id) {
        return authStudentDAO.findById(id);
    }

    @Override
    public void save(AuthStudent authStudent) {
        authStudentDAO.save(authStudent);
    }

    @Override
    public AuthStudent buildObj(NewUser newUser) {
        var authStudent = new AuthStudent();
        authStudent.setEntityId(newUser.getData().getEntityId());
        authStudent.setNip(newUser.getNip());
        authStudent.setLastChanged(new Date());
        return authStudent;
    }
}
