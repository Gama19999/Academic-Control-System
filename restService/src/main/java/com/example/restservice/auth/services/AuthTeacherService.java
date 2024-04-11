package com.example.restservice.auth.services;

import com.example.restservice.auth.entities.AuthTeacher;
import com.example.restservice.pojos.NewUser;

public interface AuthTeacherService {
    AuthTeacher findById(int id);

    void save(AuthTeacher authTeacher);

    AuthTeacher buildObj(NewUser newUser);
}
