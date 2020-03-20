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
import java.util.stream.StreamSupport;

public class IndianStatesAnalyser {
    //FOR CSV FILE
    private static final String CSV_FILE_PATTERN = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    //METHOD FOR STATE CENSUS
    public int loadIndianCensusData(String csvFilePath) throws MyExceptions {
        //LOCAL VARIABLE
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(CSV_FILE_PATTERN, extension))
            throw new MyExceptions(MyExceptions.Exception.PATH_NOT_FOUND, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<IndianCensusData> censusCSVIterator = this.getCsvFileIterator(reader, IndianCensusData.class);
            return this.getCount(censusCSVIterator);
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, "Wrong Delimiter or Header");
        }
        return recordCount;
    }

    //METHOD FOR STATE CODE
    public int loadIndianStateCodeData(String csvFilePath) throws MyExceptions {
        //LOCAL VARIABLE
        int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(CSV_FILE_PATTERN, extension))
            throw new MyExceptions(MyExceptions.Exception.PATH_NOT_FOUND, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<IndianStateCode> codeCSVIterator = this.getCsvFileIterator(reader, IndianStateCode.class);
            return this.getCount(codeCSVIterator);
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, "Wrong Delimiter or Header");
        }
        return recordCount;
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

    //GENERIC METHOD FOR CSV ITERATOR
    private <T> Iterator<T> getCsvFileIterator(Reader reader, Class<T> csvClass) {
        CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder(reader);
        csvToBeanBuilder.withType(csvClass);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<T> csvToBean = csvToBeanBuilder.build();
        return csvToBean.iterator();
    }

    //GENERIC METHOD TO GET COUNT
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        System.out.println("Welcome to Indian Census");
    }
}