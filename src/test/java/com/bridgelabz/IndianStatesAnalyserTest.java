package com.bridgelabz;

import com.bridgelabz.censusanalyserproject.IndianCensusData;
import com.bridgelabz.censusanalyserproject.IndianStateCode;
import com.bridgelabz.censusanalyserproject.IndianStatesAnalyser;
import com.bridgelabz.exception.MyExceptions;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IndianStatesAnalyserTest {
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

    IndianStatesAnalyser censusAnalyserProblem = new IndianStatesAnalyser();

    @Test
    public void givenCensusAnalyserCensus_WhenImproper_ShouldThrowException() {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusAnalyserFile_WhenIncorrect_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_FILE);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenIncorrectPath_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenImproper_ShouldThrowException() {
        try {
            int checkCode = censusAnalyserProblem.loadIndianStateCodeData(INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCensusAnalyserStateCoderFile_WhenIncorrect_ShouldReturnException() {
        try {
            int checkCode = censusAnalyserProblem.loadIndianStateCodeData(WRONG_INDIAN_STATE_CODE_DATA_FILE);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenIncorrectPath_ShouldThrowException() {
        try {
            int checkCode = censusAnalyserProblem.loadIndianStateCodeData(WRONG_INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenDelimiterIncorrect_ShouldReturnException() {
        try {
            int checkCode = censusAnalyserProblem.loadIndianStateCodeData(WRONG_DELIMITER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenHeaderIncorrect_ShouldReturnException() {
        try {
            int checkCode = censusAnalyserProblem.loadIndianStateCodeData(WRONG_HEADER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData(INDIAN_STATE_CENSUS_DATA_PATH);
            IndianCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData(INDIAN_STATE_CENSUS_DATA_PATH);
            IndianCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, IndianCensusData[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].getState());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeData_WhenSortedStateWise_ShouldReturnFirstSortedResult() {
        try {
            String sortedCodeData = censusAnalyserProblem.getSortedStateCodeData(INDIAN_STATE_CODE_DATA_PATH);
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCode[].class);
            Assert.assertEquals("AD", stateCSV[0].getStateCode());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCodeData_WhenSortedStateWise_ShouldReturnLastSortedResult() {
        try {
            String sortedCodeData = censusAnalyserProblem.getSortedStateCodeData(INDIAN_STATE_CODE_DATA_PATH);
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedCodeData, IndianStateCode[].class);
            Assert.assertEquals("WB", stateCSV[36].getStateCode());
        } catch (MyExceptions e) {
            e.printStackTrace();
        }
    }
}