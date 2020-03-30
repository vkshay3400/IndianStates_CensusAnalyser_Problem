package com.bridgelabz.censusanalyserproject.adapter;

import com.bridgelabz.censusanalyserproject.dao.CensusDAO;
import com.bridgelabz.censusanalyserproject.dto.USCensusCSV;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws MyExceptions {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}