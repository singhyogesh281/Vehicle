package com.example.demo.exception;

public class RegistrationNumberAlreadyExistsException extends RuntimeException {
    public RegistrationNumberAlreadyExistsException(String message) {
        super(message);
    }
}
