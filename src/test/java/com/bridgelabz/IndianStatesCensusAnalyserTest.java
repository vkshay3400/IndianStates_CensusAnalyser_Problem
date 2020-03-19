package com.bridgelabz;

import com.bridgelabz.censusanalyserproject.IndianStatesCensusAnalyser;
import com.bridgelabz.censusanalyserproject.IndianStatesCodeAnalyser;
import com.bridgelabz.exception.MyExceptions;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IndianStatesCensusAnalyserTest {
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

    IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
    IndianStatesCodeAnalyser codeAnalyserProblem = new IndianStatesCodeAnalyser();

    @Test
    public void givenCensusAnalyserCensus_WhenImproper_ShouldThrowException() throws MyExceptions, IOException {
        int checkCensus = censusAnalyserProblem.loadIndianCensusData(INDIAN_STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, checkCensus);
    }

    @Test
    public void givenCensusAnalyserFile_WhenIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_FILE);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenIncorrectPath_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_INDIAN_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenDelimiterIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenHeaderIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int checkCensus = censusAnalyserProblem.loadIndianCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, checkCensus);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenImproper_ShouldThrowException() throws MyExceptions {
        int checkCode = codeAnalyserProblem.loadIndianStateCodeData(INDIAN_STATE_CODE_DATA_PATH);
        Assert.assertEquals(37, checkCode);
    }

    @Test
    public void givenCensusAnalyserStateCoderFile_WhenIncorrect_ShouldReturnException() throws MyExceptions {
        try {
            int checkCode = codeAnalyserProblem.loadIndianStateCodeData(WRONG_INDIAN_STATE_CODE_DATA_FILE);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenIncorrectPath_ShouldThrowException() throws MyExceptions, IOException {
        try {
            int checkCode = codeAnalyserProblem.loadIndianStateCodeData(WRONG_INDIAN_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenDelimiterIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int checkCode = codeAnalyserProblem.loadIndianStateCodeData(WRONG_DELIMITER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserStateCode_WhenHeaderIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int checkCode = codeAnalyserProblem.loadIndianStateCodeData(WRONG_HEADER_STATE_CODE_DATA_PATH);
            Assert.assertEquals(37, checkCode);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }
}