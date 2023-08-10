package com.example.zipcode2023.exception;

public class ZipCodeNotFoundException extends RuntimeException {
    public ZipCodeNotFoundException(String message) {
        super(message);
    }
}
