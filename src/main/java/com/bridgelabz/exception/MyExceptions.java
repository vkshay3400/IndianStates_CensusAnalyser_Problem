package com.bridgelabz.exception;

public class MyExceptions extends Exception {
    public enum Exception {
        FILE_NOT_FOUND, PATH_NOT_FOUND, WRONG_DELIMITER_OR_HEADER;
    }

    public Exception type;

    public MyExceptions(Exception type, String message) {
        super(message);
        this.type = type;
    }
}