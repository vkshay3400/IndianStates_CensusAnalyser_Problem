package com.bridgelabz.censusanalyserproject.statecensus.builder;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new CsvBuilder();
    }
}