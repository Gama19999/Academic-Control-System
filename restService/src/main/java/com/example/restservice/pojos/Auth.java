package com.example.restservice.pojos;

import java.util.Date;
import java.util.Objects;

public class Auth {
    private int id;
    private String nip;
    private String userType;
    private Date lastChanged;

    public void setId(int id) {
        this.id = id;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public String getNip() {
        return nip;
    }

    public String getUserType() {
        return userType;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public boolean hasSameNIP(String nip) {
        return !Objects.isNull(nip) && nip.equals(this.nip);
    }

    @Override
    public String toString() {
        return String.format("Auth id: %d\nNip: %s\nUser type: %s", this.id, this.nip, this.userType);
    }
}
