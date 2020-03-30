package com.bridgelabz.censusanalyserproject.service;

import com.bridgelabz.censusanalyserproject.adapter.CensusAdapter;
import com.bridgelabz.censusanalyserproject.adapter.CensusAdapterFactory;
import com.bridgelabz.censusanalyserproject.dao.CensusDAO;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    Map<String, CensusDAO> censusMap = null;

    public enum COUNTRY {
        INDIA, US;
    }

    public StateCensusAnalyser() {
        this.censusMap = new HashMap<>();
    }

    //GENERIC METHOD TO LOAD COUNTRY
    public int loadCensusData(COUNTRY country, String... csvFilePath) throws MyExceptions {
        CensusAdapter censusLoader = CensusAdapterFactory.getCensusData(country);
        censusMap = censusLoader.loadCensusData(csvFilePath);
        return censusMap.size();
    }

    //METHOD TO GET EXTENSION OF CSV FILE
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

    //METHOD TO GET COUNT OF RECORDS
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    //METHOD TO SORT STATE CENSUS
    public String getSortedCensusStateData() throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census data not found");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusComparator, censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CODE
    public String getSortedStateCodeData() throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census state code data not found");
        }
        Comparator<CensusDAO> stateCodeComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(stateCodeComparator, censusList);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusComparator, censusList);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION DENSITY
    public String getPopulationDensityWiseSortedCensusData() throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusComparator, censusList);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY AREA
    public String getAreaWiseSortedCensusData() throws MyExceptions {
        if (censusMap == null || censusMap.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.areaInSqKm);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sortCSVData(censusComparator, censusList);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT CSV DATA
    private void sortCSVData(Comparator<CensusDAO> csvComparator, List<CensusDAO> censusList) {
        for (int index1 = 0; index1 < censusList.size() - 1; index1++) {
            for (int index2 = 0; index2 < censusList.size() - index1 - 1; index2++) {
                CensusDAO census1 = censusList.get(index2);
                CensusDAO census2 = censusList.get(index2 + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    censusList.set(index2, census2);
                    censusList.set(index2 + 1, census1);
                }
            }
        }
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome to States Census Analyser Problem");
    }
}