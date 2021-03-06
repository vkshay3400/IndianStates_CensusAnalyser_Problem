package com.bridgelabz.censusanalyserproject.service;

import com.bridgelabz.censusanalyserproject.statecensus.indiauscensusadapter.CensusAdapter;
import com.bridgelabz.censusanalyserproject.statecensus.indiauscensusadapter.CensusAdapterFactory;
import com.bridgelabz.censusanalyserproject.censusdao.CensusDAO;
import com.bridgelabz.censusanalyserproject.exception.StateCensusExceptions;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IndiaUSStateCensusAnalyser {

    COUNTRY country;
    Map<String, CensusDAO> censusMap = new HashMap<>();

    //ENUM FOR COUNTRY
    public enum COUNTRY {
        INDIA, US;
    }

    //ENUM FOR SORTING MODE
    public enum SORTING_MODE {
        STATE, POPULATION, DENSITY, AREA, STATECODE;
    }

    //DEFAULT CONSTRUCTOR
    public IndiaUSStateCensusAnalyser() {
    }

    //PARAMETERISED CONSTRUCTOR
    public IndiaUSStateCensusAnalyser(COUNTRY country) {
        this.country = country;
    }

    //GENERIC METHOD TO LOAD COUNTRY
    public int loadCensusData(String... csvFilePath) throws StateCensusExceptions {
        CensusAdapter censusLoader = CensusAdapterFactory.getCensusData(country);
        censusMap = censusLoader.loadCensusData(csvFilePath);
        return censusMap.size();
    }

    //METHOD TO SORT SORTING MODE
    public String getSortCensusData(SORTING_MODE mode) throws StateCensusExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census data not found");
        }
        ArrayList censusList = censusMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusList);
    }

    //METHOD TO SORT POPULATION AND DENSITY
    public String getSortCensusDataByPopulousStateWithDensity() throws StateCensusExceptions {
        if (censusMap == null || censusMap.size() == 0)
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census data not found");
        ArrayList censusDTO = censusMap.values().stream()
                .sorted(Comparator.comparingInt(CensusDAO::getPopulation).thenComparingDouble(CensusDAO::getDensityPerSqKm))
                .map(c -> c.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }

    //METHOD FOR CSV FILE
    public static String getFileExtension(String file) {
        String extension = "";
        try {
            if (file != null) {
                extension = file.substring(file.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }

    //MAIN METHOD
    public static void main(String[] args) {
    }
}