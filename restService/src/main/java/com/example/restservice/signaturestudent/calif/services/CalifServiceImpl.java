package com.example.restservice.signaturestudent.calif.services;

import com.example.restservice.signaturestudent.calif.DAOs.CalifDAO;
import com.example.restservice.signaturestudent.calif.entities.Calif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalifServiceImpl implements CalifService {

    @Autowired
    CalifDAO califDAO;

    @Override
    public Calif findById(int id) {
        return califDAO.findById(id);
    }

    @Override
    public ArrayList<Calif> findAll() {
        return califDAO.findAll();
    }

    @Override
    public void save(Calif calif) {
        califDAO.save(calif);
    }
}
