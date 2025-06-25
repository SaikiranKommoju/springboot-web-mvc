package com.vsks.exception;

public class EmployeeDatabaseException extends RuntimeException {

    public EmployeeDatabaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
