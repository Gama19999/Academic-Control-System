package com.example.restservice.auth.DAOs;

import com.example.restservice.auth.entities.AuthTeacher;
import org.springframework.transaction.annotation.Transactional;

public interface AuthTeacherDAO {
    AuthTeacher findById(int id);

    @Transactional
    void save(AuthTeacher authTeacher);
}
