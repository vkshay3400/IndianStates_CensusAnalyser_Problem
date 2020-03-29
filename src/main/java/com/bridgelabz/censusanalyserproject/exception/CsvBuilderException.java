package com.bridgelabz.censusanalyserproject.exception;

public class CsvBuilderException extends Exception {
    public enum Exception_Type {
        UNABLE_TO_PARSE;
    }

    public MyExceptions.Exception_Type type;

    public CsvBuilderException(MyExceptions.Exception_Type type, String message) {
        super(message);
        this.type = type;
    }
}