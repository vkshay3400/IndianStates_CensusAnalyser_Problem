package com.bridgelabz.censusanalyserproject.service;

import com.bridgelabz.censusanalyserproject.adapter.CensusAdapter;
import com.bridgelabz.censusanalyserproject.adapter.CensusAdapterFactory;
import com.bridgelabz.censusanalyserproject.dao.CensusDAO;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class StateCensusAnalyser {

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
    public StateCensusAnalyser() {
    }

    //PARAMETERISED CONSTRUCTOR
    public StateCensusAnalyser(COUNTRY country) {
        this.country = country;
    }

    //GENERIC METHOD TO LOAD COUNTRY
    public int loadCensusData(String... csvFilePath) throws MyExceptions {
        CensusAdapter censusLoader = CensusAdapterFactory.getCensusData(country);
        censusMap = censusLoader.loadCensusData(csvFilePath);
        return censusMap.size();
    }

    //METHOD TO SORT SORTING MODE
    public String getSortCensusData(SORTING_MODE mode) throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census data not found");
        }
        ArrayList censusList = censusMap.values().stream()
                .sorted(CensusDAO.getSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusList);
    }

    //METHOD TO SORT POPULATION AND DENSITY
    public String getSortCensusDataByPopulousStateWithDensity() throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0)
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census data not found");
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