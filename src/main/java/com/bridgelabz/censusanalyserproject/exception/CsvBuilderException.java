package com.bridgelabz.censusanalyserproject.exception;

public class CsvBuilderException extends Exception {
    public enum Exception_Type {
        UNABLE_TO_PARSE, FILE_NOT_FOUND, PATH_NOT_FOUND, WRONG_DELIMITER_OR_HEADER;
    }

    public StateCensusExceptions.Exception_Type type;

    public CsvBuilderException(StateCensusExceptions.Exception_Type type, String message) {
        super(message);
        this.type = type;
    }
}