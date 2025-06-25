package com.vsks.exception;

public class StudentDatabaseException extends RuntimeException {

    public StudentDatabaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
