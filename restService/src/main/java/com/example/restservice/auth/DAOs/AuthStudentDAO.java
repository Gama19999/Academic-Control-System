package com.example.restservice.auth.DAOs;

import com.example.restservice.auth.entities.AuthStudent;
import org.springframework.transaction.annotation.Transactional;

public interface AuthStudentDAO {
    AuthStudent findById(int id);

    @Transactional
    void save(AuthStudent authStudent);
}
