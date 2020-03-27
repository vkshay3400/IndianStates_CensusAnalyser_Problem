package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CsvBuilderException;
import com.bridgelabz.exception.MyExceptions;
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

public class IndianStatesAnalyser {
    //FOR CSV FILE
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //LIST, MAP
    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusMap = null;

    //CONSTRUCTOR
    public IndianStatesAnalyser() {
        this.censusList = new ArrayList<>();
        this.censusMap = new HashMap<>();
    }

    //METHOD FOR STATE CENSUS
    public int loadIndianCensusData(String csvFilePath) throws MyExceptions {
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyExceptions(MyExceptions.Exception_Type.PATH_NOT_FOUND, "No such a path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianCensusData> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianCensusData.class);
            while (stateCensusIterator.hasNext()) {
                CensusDAO censusDTO = new CensusDAO(stateCensusIterator.next());
                this.censusMap.put(censusDTO.state, censusDTO);
                censusList = censusMap.values().stream().collect(Collectors.toList());
            }
            return censusMap.size();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //METHOD FOR STATE CODE
    public int loadIndianStateCodeData(String csvFilePath) throws MyExceptions {
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyExceptions(MyExceptions.Exception_Type.PATH_NOT_FOUND, "No such a path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCode.class);
            while (stateCensusIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(stateCensusIterator.next());
                this.censusMap.put(censusDAO.stateCode, censusDAO);
                censusList = censusMap.values().stream().collect(Collectors.toList());
            }
            return censusMap.size();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "No such delimiter and header");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return 0;
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
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
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
    public String getPopulationWiseSortedCensusData() throws MyExceptions {
        if (censusList == null || censusList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sort(censusComparator);
        Collections.reverse(censusList);
        String sortedStatePopulationJson = new Gson().toJson(censusList);
        return sortedStatePopulationJson;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome to Indian Census");
    }
}