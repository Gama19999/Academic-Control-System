package com.example.restservice.admin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity(name = "admin")
public class Admin {
    @Id
    public int adminId;

    public String name;
    public String lastName;
    public boolean status;
    public Date since;
    public boolean topLevelAcc;

    public boolean getTopLevelAcc() {
        return topLevelAcc;
    }

    public void setTopLevelAcc(boolean topLevelAcc) {
        this.topLevelAcc = topLevelAcc;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}
