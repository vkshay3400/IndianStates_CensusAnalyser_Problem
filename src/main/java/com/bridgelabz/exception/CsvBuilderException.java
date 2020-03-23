package com.bridgelabz.exception;

public class CsvBuilderException extends Exception {
    public enum Exception {
        FILE_NOT_FOUND, PATH_NOT_FOUND, WRONG_DELIMITER_OR_HEADER;
    }

    public Exception type;

    public CsvBuilderException(Exception type, String message) {
        super(message);
        this.type = type;
    }
}