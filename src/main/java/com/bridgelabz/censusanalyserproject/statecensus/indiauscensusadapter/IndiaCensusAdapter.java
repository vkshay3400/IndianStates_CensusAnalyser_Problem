package com.bridgelabz.censusanalyserproject.statecensus.indiauscensusadapter;

import com.bridgelabz.censusanalyserproject.censusdao.CensusDAO;
import com.bridgelabz.censusanalyserproject.statecensus.builder.CsvBuilderFactory;
import com.bridgelabz.censusanalyserproject.statecensus.dto.IndianCensusDataCSV;
import com.bridgelabz.censusanalyserproject.statecensus.dto.IndianStateCodeCSV;
import com.bridgelabz.censusanalyserproject.exception.CsvBuilderException;
import com.bridgelabz.censusanalyserproject.exception.StateCensusExceptions;
import com.bridgelabz.censusanalyserproject.service.IndiaUSStateCensusAnalyser;
import com.bridgelabz.censusanalyserproject.statecensus.builder.IcsvBuilder;

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
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateCensusExceptions {
        Map<String, CensusDAO> censusMap = super.loadCensusData(IndianCensusDataCSV.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusMap;
        return this.loadStateCodeCensusData(censusMap, csvFilePath[1]);
    }

    //FUNCTION TO LOAD CENSUS DATA
    private <E> Map<String, CensusDAO> loadStateCodeCensusData(Map<String, CensusDAO> censusMap, String csvFilePath) throws StateCensusExceptions {
        String extension = IndiaUSStateCensusAnalyser.getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.PATH_NOT_FOUND, "No such path");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCodeCSV> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCodeCSV.class);
            Iterable<IndianStateCodeCSV> csvIterable = () -> stateCensusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.state) != null)
                    .forEach(csvState -> censusMap.get(csvState.state).stateCode = csvState.stateCode);
            return censusMap;
        } catch (RuntimeException e) {
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, "Incorrect Delimiter or Header");
        } catch (NoSuchFileException e) {
            throw new StateCensusExceptions(StateCensusExceptions.Exception_Type.FILE_NOT_FOUND, "No such file");
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}