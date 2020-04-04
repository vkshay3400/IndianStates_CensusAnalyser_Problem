package com.bridgelabz.censusanalyserproject.statecensus.indiauscensusadapter;

import com.bridgelabz.censusanalyserproject.service.IndiaUSStateCensusAnalyser;

public class CensusAdapterFactory {
    public static CensusAdapter getCensusData(IndiaUSStateCensusAnalyser.COUNTRY country) {
        if (country.equals(IndiaUSStateCensusAnalyser.COUNTRY.INDIA))
            return new IndiaCensusAdapter();
        if (country.equals(IndiaUSStateCensusAnalyser.COUNTRY.US))
            return new USCensusAdapter();
        return null;
    }
}