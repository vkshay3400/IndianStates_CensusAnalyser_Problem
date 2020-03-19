package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyExceptions;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;

public class IndianStatesAnalyser {
    //FOR CSV FILE
    private static final String CSV_FILE_PATTERN = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //METHOD FOR STATE CENSUS
    public int loadIndianCensusData(String csvFilePath) throws MyExceptions, IOException {
        int count = 0;

        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(CSV_FILE_PATTERN, extension))
            throw new MyExceptions(MyExceptions.Exception.PATH_NOT_FOUND, "No such a type");

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<IndianCensusData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianCensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianCensusData> censusCSVIterator = csvToBean.iterator();
            while (censusCSVIterator.hasNext()) {
                System.out.print(count++ + " ");
                IndianCensusData censusCSV = censusCSVIterator.next();
                System.out.print("state: " + censusCSV.getState() + ", ");
                System.out.print("population: " + censusCSV.getPopulation() + ", ");
                System.out.print("area: " + censusCSV.getAreaInSqKm() + ", ");
                System.out.print("density: " + censusCSV.getDensityPerSqKm() + ", ");
                System.out.println();
            }
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, "Wrong Delimiter or Header");
        }
        return count;
    }

    //METHOD FOR STATE CODE
    public int loadIndianStateCodeData(String csvFilePath) throws MyExceptions {
        //LOCAL VARIABLE
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(CSV_FILE_PATTERN, extension))
            throw new MyExceptions(MyExceptions.Exception.PATH_NOT_FOUND, "No such a type");

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<IndianStateCode> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianStateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianStateCode> statesCSVIterator = csvToBean.iterator();
            while (statesCSVIterator.hasNext()) {
                IndianStateCode censusCSV = statesCSVIterator.next();
                ++recordCount;
                System.out.print("SrNo: " + censusCSV.getSrNo() + ", ");
                System.out.print("state: " + censusCSV.getState() + ", ");
                System.out.print("Name: " + censusCSV.getName() + ", ");
                System.out.print("TIN: " + censusCSV.getTIN() + ", ");
                System.out.print("StateCode: " + censusCSV.getStateCode() + ", ");
                System.out.println();
            }
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, "Wrong Delimiter or Header");
        }
        return recordCount;
    }

    //FOR FILE EXTENSION
    private static String getFileExtension(String file) {
        String format = null;
        try {
            if (file != null) {
                format = file.substring(file.lastIndexOf("."));
            }
        } catch (Exception e) {
            format = "";
        }
        return format;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome to Indian Census");
    }
}