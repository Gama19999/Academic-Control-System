package com.example.restservice.auth.DAOs;

import com.example.restservice.auth.entities.AuthAdmin;

public interface AuthAdminDAO {
    AuthAdmin findById(int id);
}
