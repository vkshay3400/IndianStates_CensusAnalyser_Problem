package com.bridgelabz.censusanalyserproject.statecensus.indiauscensusadapter;

import com.bridgelabz.censusanalyserproject.censusdao.CensusDAO;
import com.bridgelabz.censusanalyserproject.service.IndiaUSStateCensusAnalyser;
import com.bridgelabz.censusanalyserproject.statecensus.dto.IndianCensusDataCSV;
import com.bridgelabz.censusanalyserproject.statecensus.dto.USCensusCSV;
import com.bridgelabz.censusanalyserproject.exception.CsvBuilderException;
import com.bridgelabz.censusanalyserproject.exception.StateCensusExceptions;
import com.bridgelabz.censusanalyserproject.statecensus.builder.CsvBuilderFactory;
import com.bridgelabz.censusanalyserproject.statecensus.builder.IcsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusExceptions;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCsvClass, String... csvFilePath) throws StateCensusExceptions {
        Map<String, CensusDAO> censusMap = new HashMap<>();
        String extension = IndiaUSStateCensusAnalyser.getFileExtension(csvFilePath[0]);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.PATH_NOT_FOUND, "No such path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, censusCsvClass);
            Iterable<E> csvIterable = () -> stateCensusIterator;
            if (censusCsvClass.getName().contains("IndianCensusData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndianCensusDataCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
                return censusMap;
            }
            if (censusCsvClass.getName().contains("USCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
                return censusMap;
            } else {
                throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.NO_SUCH_COUNTRY, "Wrong country name");
            }
        } catch (NoSuchFileException e) {
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.NO_SUCH_CENSUS_DATA, "No such data");
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "Incorrect Delimiter or Header");
        }
        return censusMap;
    }
}