package com.bridgelabz.exception;

public class MyExceptions extends Exception {
    public enum Exception_Type {
        FILE_NOT_FOUND, PATH_NOT_FOUND, WRONG_DELIMITER_OR_HEADER,NO_SUCH_CENSUS_DATA;
    }

    public Exception_Type type;

    public MyExceptions(Exception_Type type, String message) {
        super(message);
        this.type = type;
    }
}