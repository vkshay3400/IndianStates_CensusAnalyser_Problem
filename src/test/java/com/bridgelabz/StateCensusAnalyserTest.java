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

    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusAnalyserCensus_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            int checkCensus = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusAnalyserFile_WhenIncorrect_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_INDIAN_STATE_CENSUS_DATA_FILE);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenIncorrectPath_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusAnalyserStateCode_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            int checkCode = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH, INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusAnalyserStateCoderFile_WhenIncorrect_ShouldReturnException() {
        try {
            int checkCode = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_INDIAN_STATE_CODE_DATA_FILE);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenIncorrectPath_ShouldThrowException() {
        try {
            int checkCode = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            int checkCode = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_DELIMITER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            int checkCode = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, WRONG_HEADER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndianCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndianCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeData_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CODE_DATA_PATH);
            String sortedCodeData = censusAnalyserProblem.getSortedCensusStateData();
            IndianStateCodeCSV[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCodeCSV[].class);
            Assert.assertEquals("AD", stateCSV[0].getStateCode());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeData_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CODE_DATA_PATH);
            String sortedCodeData = censusAnalyserProblem.getSortedCensusStateData();
            IndianStateCodeCSV[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCodeCSV[].class);
            Assert.assertEquals("WB", stateCSV[36].getStateCode());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulation_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationWiseSortedCensusData();
            IndianCensusData[] stateCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals(199812341, stateCSV[0].getPopulation());
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulation_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationWiseSortedCensusData();
            IndianCensusData[] stateCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals(607688, stateCSV[28].getPopulation());
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulationDensity_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationDensityWiseSortedCensusData();
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, stateCSV[0].densityPerSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulationDensity_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationDensityWiseSortedCensusData();
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(52, stateCSV[28].densityPerSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnAreaWise_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getAreaWiseSortedCensusData();
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, stateCSV[0].areaInSqKm, 0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnAreaWise_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.INDIA, INDIAN_STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getAreaWiseSortedCensusData();
            CensusDAO[] stateCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(142, stateCSV[28].areaInSqKm,0);
        } catch (MyExceptions e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSCensus_WhenNumberOfRecordMatches_ShouldReturnTrue() {
        try {
            int checkCensus = censusAnalyserProblem.loadCensusData(StateCensusAnalyser.COUNTRY.US, US_CENSUS_DATA_PATH);
            Assert.assertEquals(51, checkCensus);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }
}