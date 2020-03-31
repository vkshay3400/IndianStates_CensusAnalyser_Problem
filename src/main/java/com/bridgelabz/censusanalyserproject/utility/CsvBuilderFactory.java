package com.bridgelabz.censusanalyserproject.utility;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new com.bridgelabz.censusanalyserproject.utility.CsvBuilder();
    }
}