package com.lucic.albumtracker.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
