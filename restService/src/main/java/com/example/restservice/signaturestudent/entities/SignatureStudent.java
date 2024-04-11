package com.example.restservice.signaturestudent.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SignatureStudent {
    @Id
    public int signStudId;

    public int studentId;
    public int signatureId;

    public int getSignStudId() {
        return signStudId;
    }

    public void setSignStudId(int signStudId) {
        this.signStudId = signStudId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }
}
