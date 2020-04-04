package com.bridgelabz.censusanalyserproject.statecensus.indiauscensusadapter;

import com.bridgelabz.censusanalyserproject.censusdao.CensusDAO;
import com.bridgelabz.censusanalyserproject.statecensus.dto.USCensusCSV;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws MyExceptions {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}