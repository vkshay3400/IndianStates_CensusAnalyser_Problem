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
    private static final String WRONG_US_CENSUS_DATA_PATH = "./src/test/resources/USCensusDa.csv";

    int checkCensus;

    @Test
    public void givenIndianCensusAnalyser_StateCensusAndCode_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusAnalyserStateCensusAndCodeFile_WhenIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_FILE, WRONG_INDIAN_STATE_CODE_DATA_FILE);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndianCensusAnalyserStateCode_WhenIncorrectPath_ShouldThrowException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_PATH, WRONG_INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndianCensusAnalyserStateCensusAndCode_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH, WRONG_DELIMITER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusAnalyser_StateCensusAndCode_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH, WRONG_HEADER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusAnalyser_StateCensusAndCode_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            String sortedCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCodeCSV[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCodeCSV[].class);
            Assert.assertEquals("Andhra Pradesh", stateCSV[0].state);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusAnalyser_StateCensusAndCode_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            checkCensus = censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            String sortedCodeData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.STATECODE);
            IndianStateCodeCSV[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCodeCSV[].class);
            Assert.assertEquals("West Bengal", stateCSV[28].state);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusAnalyser_StateCensus_WhenSortedOnPopulation_ShouldReturnFirstReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CODE_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            IndianCensusData[] stateCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals(607688, stateCSV[0].population);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulation_ShouldReturnLastReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            IndianCensusData[] stateCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals(199812341, stateCSV[28].population);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedOnPopulationDensity_ShouldReturnFirstReversedSortedResult() {
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
    public void givenIndianStateCensusData_WhenSortedOnPopulationDensity_ShouldReturnLastReversedSortedResult() {
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
    public void givenIndianStateCensusData_WhenSortedOnAreaWise_ShouldReturnFirstReversedSortedResult() {
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
    public void givenIndianStateCensusData_WhenSortedOnAreaWise_ShouldReturnLastReversedSortedResult() {
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
    public void givenUSCensusAnalyserStateCensusFile_WhenIncorrect_ShouldReturnException() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            checkCensus = censusAnalyserProblem.loadCensusData(WRONG_US_CENSUS_DATA_PATH);
            Assert.assertEquals(51, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenUSCensus_WhenSortedOnPopulation_ShouldReturnFirstReversedSortedResult() {
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
    public void givenUSCensus_WhenSortedOnPopulation_ShouldReturnLastReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusCSV[50].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUS_StateCensus_WhenSortedOnDensity_ShouldReturnFirstReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.DENSITY);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUS_StateCensus_WhenSortedOnDensity_ShouldReturnLastReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.DENSITY);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("District of Columbia", censusCSV[50].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUS_StateCensus_WhenSortedOnStateArea_ShouldReturnFirstReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.AREA);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUS_StateCensus_WhenSortedOnStateArea_ShouldReturnLastReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.US);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusData(StateCensusAnalyser.SORTING_MODE.AREA);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Alaska", censusCSV[50].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIndia_StateCensus_WhenSortedOnPopulousStateAndDensity_ShouldReturnFirstReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusDataByPopulousStateWithDensity();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[50].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenIndia_StateCensus_WhenSortedOnPopulousStateAndDensity_ShouldReturnLastReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusDataByPopulousStateWithDensity();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Sikkim", censusCSV[0].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUS_StateCensus_WhenSortedOnPopulousStateAndDensity_ShouldReturnLastReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusDataByPopulousStateWithDensity();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusCSV[50].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUS_StateCensus_WhenSortedOnPopulousStateAndDensity_ShouldReturnFirstReversedSortedResult() {
        try {
            StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser(StateCensusAnalyser.COUNTRY.INDIA);
            censusAnalyserProblem.loadCensusData(US_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortCensusDataByPopulousStateWithDensity();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Wyoming", censusCSV[0].state);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }
}