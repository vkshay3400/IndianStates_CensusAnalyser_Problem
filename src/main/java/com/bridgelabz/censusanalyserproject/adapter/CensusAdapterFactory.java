package com.bridgelabz.censusanalyserproject.adapter;

import com.bridgelabz.censusanalyserproject.service.StateCensusAnalyser;

public class CensusAdapterFactory {
    public static CensusAdapter getCensusData(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new IndiaCensusAdapter();
        if (country.equals(StateCensusAnalyser.COUNTRY.US))
            return new USCensusAdapter();
        return null;
    }
}