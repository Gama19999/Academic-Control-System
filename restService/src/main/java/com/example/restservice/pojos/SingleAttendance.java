package com.example.restservice.pojos;

public class SingleAttendance {
    public int signStudId;
    public boolean status;
    public long date;

    public SingleAttendance() {}

    public SingleAttendance(int signStudId, boolean status, long date) {
        this.signStudId = signStudId;
        this.status = status;
        this.date = date;
    }

    public int getSignStudId() {
        return signStudId;
    }

    public void setSignStudId(int signStudId) {
        this.signStudId = signStudId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SingleAttendance {\n" +
                "signStudId=" + signStudId +
                ",\nstatus=" + status +
                ",\ndate=" + date + "\n}";
    }
}
