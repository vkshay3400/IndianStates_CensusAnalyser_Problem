package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CsvBuilderException;
import com.bridgelabz.exception.MyExceptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class IndianStatesAnalyser {
    //FOR CSV FILE
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //FOR LIST
    List<IndianCensusData> csvFileList = null;
    List<IndianStateCode> csvStateCodeList = null;

    //METHOD FOR STATE CENSUS
    public int loadIndianCensusData(String csvFilePath) throws MyExceptions {
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyExceptions(MyExceptions.Exception_Type.PATH_NOT_FOUND, "No such a path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            csvFileList = csvBuilder.getCSVFileList(reader, IndianCensusData.class);
            return csvFileList.size();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
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
            csvStateCodeList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);
            return csvStateCodeList.size();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "No such delimiter and header");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
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
    public String getSortedCensusStateData(String csvFilePath) throws MyExceptions {
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Census Data not found");
        }
        csvFileList.sort(Comparator.comparing(e -> e.getState()));
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    //METHOD FOR STATE CODE COMPARATOR
    public String getSortedStateCodeData(String csvFilePath) throws MyExceptions {
        if (csvStateCodeList == null || csvStateCodeList.size() == 0) {
            throw new MyExceptions(MyExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "Code Data not found");
        }
        csvStateCodeList.sort(Comparator.comparing(e -> e.getState()));
        String toJson = new Gson().toJson(csvStateCodeList);
        return toJson;
    }

    //METHOD TO SORT
     private<T> void sort(Comparator<T> censusComparator, List<T> csvFileList){
        for (int index1 = 0; index1 < csvFileList.size(); index1++) {
            for (int index2 = 0; index2 < csvFileList.size() - index1 - 1; index2++) {
                T census1 = csvFileList.get(index2);
                T census2 = csvFileList.get(index2 + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    csvFileList.set(index2, census2);
                    csvFileList.set(index2 + 1, census1);
                }
            }
        }
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome to Indian Census");
    }
}