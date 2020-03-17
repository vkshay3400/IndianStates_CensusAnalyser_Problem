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

public class IndianStatesCensusAnalyser {

    //METHOD TO LOAD CSV FILE
    public int loadIndianCensusData(String csvFilePath) throws MyExceptions, IOException {
        int count = 0;

        String format = getFileExtension(csvFilePath);
        if (!format.equals(".csv"))
            throw new MyExceptions(MyExceptions.Exception.PATH_NOT_FOUND, "No such a type");

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            CsvToBean<CensusData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CensusData> censusCSVIterator = csvToBean.iterator();
            while (censusCSVIterator.hasNext()) {
                System.out.print(count++ + " ");
                CensusData censusCSV = censusCSVIterator.next();
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
        }
        return count;
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