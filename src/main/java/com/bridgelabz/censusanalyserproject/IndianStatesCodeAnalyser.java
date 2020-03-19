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

public class IndianStatesCodeAnalyser {
    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws MyExceptions {
        //LOCAL VARIABLE
        int recordCount = 0;
        String format = getFileExtension(csvFilePath);
        if (!format.equals(".csv"))
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
            throw new MyExceptions(MyExceptions.Exception.WRONG_DELIMITER, "Wrong Delimiter");
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

}