package com.bridgelabz.censusanalyserproject.adapter;

import com.bridgelabz.censusanalyserproject.dao.CensusDAO;
import com.bridgelabz.censusanalyserproject.dto.IndianCensusData;
import com.bridgelabz.censusanalyserproject.dto.IndianStateCodeCSV;
import com.bridgelabz.censusanalyserproject.exception.CsvBuilderException;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;
import com.bridgelabz.censusanalyserproject.service.StateCensusAnalyser;
import com.bridgelabz.censusanalyserproject.utility.CsvBuilderFactory;
import com.bridgelabz.censusanalyserproject.utility.IcsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    //FOR CSV PATTERN
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws MyExceptions {
        Map<String, CensusDAO> censusMap = super.loadCensusData(IndianCensusData.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusMap;
        return this.loadStateCodeCensusData(censusMap, csvFilePath[1]);
    }

    //FUNCTION TO LOAD CENSUS DATA
    private <E> Map<String, CensusDAO> loadStateCodeCensusData(Map<String, CensusDAO> censusMap, String csvFilePath) throws MyExceptions {
        String extension = StateCensusAnalyser.getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyExceptions(MyExceptions.Exception_Type.PATH_NOT_FOUND, "No such path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCodeCSV> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCodeCSV.class);
            Iterable<IndianStateCodeCSV> csvIterable = () -> stateCensusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.getState()) != null)
                    .forEach(csvState -> censusMap.get(csvState.getState()).stateCode = csvState.getStateCode());
            return censusMap;
        } catch (RuntimeException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "Incorrect Delimiter or Header");
        } catch (NoSuchFileException e) {
            throw new MyExceptions(MyExceptions.Exception_Type.FILE_NOT_FOUND, "No such file");
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}