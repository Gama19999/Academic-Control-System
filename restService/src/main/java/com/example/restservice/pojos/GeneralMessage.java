package com.example.restservice.pojos;

public class GeneralMessage {
    public String message;
    public String cause;
    public boolean isError;

    public GeneralMessage(String message, String cause, boolean isError) {
        this.message = message;
        this.cause = cause;
        this.isError = isError;
    }
}
