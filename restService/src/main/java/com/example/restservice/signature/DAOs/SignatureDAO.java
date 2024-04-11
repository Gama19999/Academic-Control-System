package com.example.restservice.signature.DAOs;

import com.example.restservice.signature.entities.Signature;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface SignatureDAO {
    Signature findById(int id);

    ArrayList<Signature> findAll();

    @Transactional
    void save(Signature signature);

    @Transactional
    void deleteById(int id);
}
