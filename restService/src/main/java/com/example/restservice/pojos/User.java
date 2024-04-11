package com.example.restservice.pojos;

import com.example.restservice.admin.entities.Admin;
import com.example.restservice.student.entities.Student;
import com.example.restservice.teacher.entities.Teacher;

public class User {
    private int entityId;
    private String userType;
    private String name;
    private String lastName;
    private boolean status;
    private long since;
    private String _token;
    private String _tokenExpDate;
    private int grade;
    private boolean topLevelAcc;

    public User buildUser(Object data, String userType) {
        switch (userType) {
            case "student" -> {
                entityId = ((Student) data).getStudentId();
                name = ((Student) data).getName();
                lastName = ((Student) data).getLastName();
                status = ((Student) data).getStatus();
                since = ((Student) data).getSince().getTime();
                grade = ((Student) data).getGrade();
            }
            case "teacher" -> {
                entityId = ((Teacher) data).getTeacherId();
                name = ((Teacher) data).getName();
                lastName = ((Teacher) data).getLastName();
                status = ((Teacher) data).getStatus();
                grade = ((Teacher) data).getOfGrade();
                since = ((Teacher) data).getSince().getTime();
            }
            case "admin" -> {
                entityId = ((Admin) data).getAdminId();
                name = ((Admin) data).getName();
                lastName = ((Admin) data).getLastName();
                status = ((Admin) data).getStatus();
                since = ((Admin) data).getSince().getTime();
                grade = -1;
                topLevelAcc = ((Admin) data).getTopLevelAcc();
            }
        }
        this.userType = userType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public long getSince() {
        return since;
    }

    public void setSince(long since) {
        this.since = since;
    }

    public String get_token() {
        return _token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }

    public String get_tokenExpDate() {
        return _tokenExpDate;
    }

    public void set_tokenExpDate(String _tokenExpDate) {
        this._tokenExpDate = _tokenExpDate;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isTopLevelAcc() {
        return topLevelAcc;
    }

    public void setTopLevelAcc(boolean topLevelAcc) {
        this.topLevelAcc = topLevelAcc;
    }

    @Override
    public String toString() {
        return "User ID: " + entityId + "\tUserType: " + userType + "\tName: " + name;
    }
}
