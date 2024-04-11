package com.example.restservice.auth.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity(name = "auth_student")
public class AuthStudent {
    @Id
    public int entityId;

    public String nip;
    public Date lastChanged;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public Boolean authOk(Optional<AuthStudent> other) {
        return other.filter(authStudentObj -> nip.equals(authStudentObj.getNip())).isPresent();
    }
}
