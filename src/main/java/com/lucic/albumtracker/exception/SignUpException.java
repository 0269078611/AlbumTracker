package com.lucic.albumtracker.exception;

public class SignUpException extends RuntimeException {

    public SignUpException(String errorMessage) {
        super(errorMessage);
    }
}
