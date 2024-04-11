package com.example.restservice.signature.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "signature")
public class Signature {
    @Id
    public int signatureId;

    public String name;
    public int grade;
    public float credits;

    public Signature() {}

    public Signature(int signatureId, String name, int grade, float credits) {
        this.signatureId = signatureId;
        this.name = name;
        this.grade = grade;
        this.credits = credits;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }
}
