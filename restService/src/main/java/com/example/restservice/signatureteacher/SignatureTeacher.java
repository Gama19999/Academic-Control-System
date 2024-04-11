package com.example.restservice.signatureteacher;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SignatureTeacher {
    @Id
    public int signTeachId;

    public int teacherId;
    public int signatureId;

    public int getSignTeachId() {
        return signTeachId;
    }

    public void setSignTeachId(int signTeachId) {
        this.signTeachId = signTeachId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(int signatureId) {
        this.signatureId = signatureId;
    }
}
