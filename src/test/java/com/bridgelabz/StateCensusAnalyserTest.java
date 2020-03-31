package com.bridgelabz;

import com.bridgelabz.censusanalyserproject.dao.CensusDAO;
import com.bridgelabz.censusanalyserproject.dto.IndianCensusData;
import com.bridgelabz.censusanalyserproject.dto.IndianStateCodeCSV;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;
import com.bridgelabz.censusanalyserproject.service.StateCensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {
    private static final String INDIAN_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_INDIAN_STATE_CENSUS_DATA_FILE = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_INDIAN_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.sh";
    private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongDelimiterStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongHeaderStateCensusData.csv";
    private static final String INDIAN_STATE_CODE_DATA_PATH = "./src/test/resources/StateCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_DATA_FILE = "./src/test/resources/StaCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_DATA_PATH = "./src/test/resources/StateCode.sh";
    private static final String WRONG_DELIMITER_STATE_CODE_DATA_PATH = "./src/test/resources/WrongDelimiterStateCodeData.csv";
    private static final String WRONG_HEADER_STATE_CODE_DATA_PATH = "./src/test/resources/WrongHeaderStateCodeData.csv";
    private static final String US_CENSUS_DATA_PATH = "./src/test/resources/USCensusData.csv";

    int checkCensus, checkCode;

    @Test
    public void givenIndianCensusAnalyserCensus_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusAnalyserFile_WhenIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_FILE);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenIncorrectPath_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusAnalyserStateCode_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusAnalyserStateCoderFile_WhenIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CODE_DATA_FILE);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenIncorrectPath_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(WRONG_DELIMITER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(WRONG_HEADER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATE);
            IndianCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATE);
            IndianCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeData_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            String sortedCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCodeCSV[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCodeCSV[].class);
            Assert.assertEquals("AD", stateCSV[0].getStateCode());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeData_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCode = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            String sortedCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCodeCSV[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCodeCSV[].class);
            Assert.assertEquals("WB", stateCSV[36].getStateCode());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulation_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            IndianCensusData[] stateCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals(607688, stateCSV[0].getPopulation());
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulation_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            IndianCensusData[] stateCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals(199812341, stateCSV[28].getPopulation());
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulationDensity_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.DENSITY);
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(52, stateCSV[0].densityPerSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulationDensity_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.DENSITY);
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, stateCSV[28].densityPerSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnAreaWise_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.AREA);
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(142, stateCSV[0].areaInSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnAreaWise_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.AREA);
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, stateCSV[28].areaInSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSCensus_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            checkCensus = censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            Assert.assertEquals(51, checkCensus);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensus_WhenSortedOnPopulation_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Wyoming", censusCSV[0].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSCensus_WhenSortedOnPopulation_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusCSV[50].state);
            System.out.println(sortedCensusData);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }
}