package com.example.restservice.auth.services;

import com.example.restservice.auth.DAOs.AuthAdminDAO;
import com.example.restservice.auth.entities.AuthAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthAdminServiceImpl implements AuthAdminService {

    @Autowired
    AuthAdminDAO authAdminDAO;

    @Override
    public AuthAdmin findById(int id) {
        return authAdminDAO.findById(id);
    }
}
