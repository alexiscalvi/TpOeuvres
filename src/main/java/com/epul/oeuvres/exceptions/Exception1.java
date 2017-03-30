package com.epul.oeuvres.exceptions;

public class Exception1 extends Exception {
    private String message;
    private String type;

    public Exception1(String message) {
        this.message = message;
    }

    public Exception1(String message, String type) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String libelle) {
        this.message = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
