package com.bridgelabz.exception;

public class MyExceptions extends Exception {
    public enum Exception {
        FILE_NOT_FOUND;
    }

    public Exception type;

    public MyExceptions(Exception type, String message) {
        super(message);
        this.type = type;
    }
}