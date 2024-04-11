package com.example.restservice.signaturestudent.calif.entities;

import jakarta.persistence.*;

@Entity(name = "calif")
public class Calif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ssCalifId;

    public int signStudId;
    public int term;
    public float calif;

    public Calif() {
    }

    public Calif(Calif calif) {
        ssCalifId = calif.ssCalifId;
        signStudId = calif.signStudId;
        term = calif.term;
        this.calif = calif.calif;
    }

    public int getSsCalifId() {
        return ssCalifId;
    }

    public void setSsCalifId(int ssCalifId) {
        this.ssCalifId = ssCalifId;
    }

    public int getSignStudId() {
        return signStudId;
    }

    public void setSignStudId(int signStudId) {
        this.signStudId = signStudId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public float getCalif() {
        return calif;
    }

    public void setCalif(float calif) {
        this.calif = calif;
    }

    @Override
    public String toString() {
        return "ssCalifId: " + ssCalifId + "\nSignStudId: " + signStudId + "\nTerm: " + term + "\nCalif: " + calif;
    }
}
