package com.example.restservice.auth.services;

import com.example.restservice.auth.entities.AuthStudent;
import com.example.restservice.pojos.NewUser;

public interface AuthStudentService {

    AuthStudent findById(int id);

    void save(AuthStudent authStudent);

    AuthStudent buildObj(NewUser newUser);
}
