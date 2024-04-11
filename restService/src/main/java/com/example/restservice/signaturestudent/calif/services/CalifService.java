package com.example.restservice.signaturestudent.calif.services;

import com.example.restservice.signaturestudent.calif.entities.Calif;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface CalifService {
    Calif findById(int id);

    ArrayList<Calif> findAll();

    void save(Calif calif);
}
