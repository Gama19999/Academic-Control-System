package com.example.restservice.admin.services;

import com.example.restservice.admin.DAOs.AdminDAO;
import com.example.restservice.admin.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDAO adminDAO;

    @Override
    public Admin findById(int id) {
        return adminDAO.findById(id);
    }
}
