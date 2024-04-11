package com.example.restservice.auth.services;

import com.example.restservice.auth.DAOs.AuthTeacherDAO;
import com.example.restservice.auth.entities.AuthTeacher;
import com.example.restservice.pojos.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthTeacherServiceImpl implements AuthTeacherService {

    @Autowired
    AuthTeacherDAO authTeacherDAO;

    @Override
    public AuthTeacher findById(int id) {
        return authTeacherDAO.findById(id);
    }

    @Override
    public void save(AuthTeacher authTeacher) {
        authTeacherDAO.save(authTeacher);
    }

    @Override
    public AuthTeacher buildObj(NewUser newUser) {
        var authTeacher = new AuthTeacher();
        authTeacher.setEntityId(newUser.getData().getEntityId());
        authTeacher.setNip(newUser.getNip());
        authTeacher.setLastChanged(new Date());
        return authTeacher;
    }
}
