package com.example.restservice.signaturestudent.calif.DAOs;

import com.example.restservice.signaturestudent.calif.entities.Calif;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface CalifDAO {
    Calif findById(int id);

    ArrayList<Calif> findAll();

    @Transactional
    void save(Calif calif);
}
