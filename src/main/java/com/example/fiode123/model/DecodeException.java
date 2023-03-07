package com.example.fiode123.model;

public class DecodeException extends RuntimeException {

    public DecodeException() {
    }

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}