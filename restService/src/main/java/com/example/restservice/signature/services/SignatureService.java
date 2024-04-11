package com.example.restservice.signature.services;

import com.example.restservice.signature.entities.Signature;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface SignatureService {
    Signature findById(int id);

    ArrayList<Signature> findAll();

    void save(Signature signature);

    void deleteById(int id);

    Signature buildObj(int id, Signature data);
}
