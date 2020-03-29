package com.bridgelabz.censusanalyserproject.service;

import com.bridgelabz.censusanalyserproject.utility.CsvBuilderFactory;
import com.bridgelabz.censusanalyserproject.utility.IcsvBuilder;
import com.bridgelabz.censusanalyserproject.dao.CensusDAO;
import com.bridgelabz.censusanalyserproject.dto.IndianCensusData;
import com.bridgelabz.censusanalyserproject.dto.IndianStateCodeCSV;
import com.bridgelabz.censusanalyserproject.dto.USCensusCSV;
import com.bridgelabz.censusanalyserproject.exception.CsvBuilderException;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {
    //FOR CSV FILE
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //LIST, MAP
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusMap = null;

    //CONSTRUCTOR
    public StateCensusAnalyser() {
        this.censusList = new ArrayList<>();
        this.censusMap = new HashMap<>();
    }

    //METHOD FOR STATE CENSUS
    public int loadIndianCensusData(String csvFilePath) throws MyExceptions {
        return this.loadIndiaStateData(csvFilePath, IndianCensusData.class);
    }

    //METHOD FOR STATE CODE
    public int loadIndianStateCodeData(String csvFilePath) throws MyExceptions {
        return this.loadIndiaStateData(csvFilePath, IndianStateCodeCSV.class);
    }

    //COMMON METHOD TO LOAD INDIA CENSUS AND CODE
    private <E> int loadIndiaStateData(String csvFilePath, Class<E> censusCsvClass) throws MyExceptions {
        String extension = getFileExtension(csvFilePath);
        int numberOfRecords = 0;
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyExceptions(MyExceptions.Exception_Type.PATH_NOT_FOUND, "No such a path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, censusCsvClass);
            Iterable<E> csvFileIterator = () -> stateCensusIterator;
            if (censusCsvClass.getName().contains("IndianCensusData")) {
                StreamSupport.stream(csvFileIterator.spliterator(), false)
                        .map(IndianCensusData.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
                censusList = censusMap.values().stream().collect(Collectors.toList());
                return censusMap.size();
            }
            if (censusCsvClass.getName().contains("IndianStateCode")) {
                StreamSupport.stream(csvFileIterator.spliterator(), false)
                        .map(IndianStateCodeCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.getStateCode(), new CensusDAO(censusCSV)));
                censusList = censusMap.values().stream().collect(Collectors.toList());
                return censusMap.size();
            }
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //METHOD FOR US CENSUS DATA
    public int loadUSCensusData(String csvFilePath) throws MyExceptions {
        String extension = getFileExtension(csvFilePath);
        int numberOfRecords = 0;
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyExceptions(MyExceptions.Exception_Type.PATH_NOT_FOUND, "No such a path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            List<USCensusCSV> csvUSCensusList = csvBuilder.getCSVFileList(reader, USCensusCSV.class);
            numberOfRecords = csvUSCensusList.size();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "No such delimiter and header");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return numberOfRecords;
    }

    //METHOD TO GET EXTENSION OF CSV FILE
    private static String getFileExtension(String file) {
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

    //GENERIC METHOD TO GET COUNT
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    //METHOD FOR STATE CENSUS COMPARATOR
    public String getSortedCensusStateData() throws MyExceptions {
        if (censusList == null || censusList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Code Data not found");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDTO -> censusDTO.state);
        this.sort(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD FOR STATE CODE COMPARATOR
    public String getSortedStateCodeData(String csvFilePath) throws MyExceptions {
        if (censusList == null || censusList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Code Data not found");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDTO -> censusDTO.state);
        this.sort(censusComparator);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT
    private void sort(Comparator<CensusDAO> censusComparator) {
        for (int index1 = 0; index1 < censusList.size(); index1++) {
            for (int index2 = 0; index2 < censusList.size() - index1 - 1; index2++) {
                CensusDAO census1 = censusList.get(index2);
                CensusDAO census2 = censusList.get(index2 + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusList.set(index2, census2);
                    censusList.set(index2 + 1, census1);
                }
            }
        }
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationStateWiseSortedCensusData() throws MyExceptions {
        if (censusList == null || censusList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sort(censusComparator);
        Collections.reverse(censusList);
        String sortedStatePopulationJson = new Gson().toJson(censusList);
        return sortedStatePopulationJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION DENSITY
    public String getPopulationDensityWiseSortedCensusData() throws MyExceptions {
        if (censusList == null || censusList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.density);
        this.sort(censusComparator);
        Collections.reverse(censusList);
        String sortedPopulationDensityJson = new Gson().toJson(censusList);
        return sortedPopulationDensityJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY AREA
    public String getAreaWiseSortedCensusData() throws MyExceptions {
        if (censusList == null || censusList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.area);
        this.sort(censusComparator);
        Collections.reverse(censusList);
        String sortedAreaJson = new Gson().toJson(censusList);
        return sortedAreaJson;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome to Indian Census");
    }
}