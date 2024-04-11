package com.example.restservice.signature.services;

import com.example.restservice.signature.DAOs.SignatureDAO;
import com.example.restservice.signature.entities.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SignatureServiceImpl implements SignatureService {

    @Autowired
    SignatureDAO signatureDAO;

    @Override
    public Signature findById(int id) {
        return signatureDAO.findById(id);
    }

    @Override
    public ArrayList<Signature> findAll() {
        return signatureDAO.findAll();
    }

    @Override
    public void save(Signature signature) {
        signatureDAO.save(signature);
    }

    @Override
    public void deleteById(int id) {
        signatureDAO.deleteById(id);
    }

    @Override
    public Signature buildObj(int id, Signature data) {
        var updSignature = new Signature();
        updSignature.setSignatureId(id);
        updSignature.setName(data.getName());
        updSignature.setGrade(data.getGrade());
        updSignature.setCredits(data.getCredits());
        return updSignature;
    }
}
