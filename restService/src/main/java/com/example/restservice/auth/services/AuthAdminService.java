package com.example.restservice.auth.services;

import com.example.restservice.auth.entities.AuthAdmin;

public interface AuthAdminService {
    AuthAdmin findById(int id);
}
