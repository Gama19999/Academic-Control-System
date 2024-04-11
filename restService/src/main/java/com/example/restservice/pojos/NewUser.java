package com.example.restservice.pojos;

public class NewUser {
    private User data;
    private String nip;
    private String oldNip;

    public NewUser() {}

    public NewUser(User data, String nip, String oldNip) {
        this.data = data;
        this.nip = nip;
        this.oldNip = oldNip;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getOldNip() {
        return oldNip;
    }

    public void setOldNip(String oldNip) {
        this.oldNip = oldNip;
    }

    @Override
    public String toString() {
        return "User: " + data.toString() + "\nNIP: " + nip;
    }
}
