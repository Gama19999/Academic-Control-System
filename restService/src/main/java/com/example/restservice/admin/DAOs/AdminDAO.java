package com.example.restservice.admin.DAOs;

import com.example.restservice.admin.entities.Admin;

public interface AdminDAO {
    Admin findById(int id);
}
