package com.bridgelabz;

import com.bridgelabz.censusanalyserproject.IndianStatesCensusAnalyser;
import com.bridgelabz.censusanalyserproject.IndianStatesCodeAnalyser;
import com.bridgelabz.exception.MyExceptions;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IndianStatesCensusAnalyserTest {
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_DATA_PATH = "./src/test/resources/StateCensusData.sh";
    private static final String INDIAN_STATE_CODE_DATA_PATH = "./src/test/resources/StateCode.csv";

    IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
    IndianStatesCodeAnalyser codeAnalyserProblem = new IndianStatesCodeAnalyser();

    @Test
    public void givenCensusAnalyserProblemTest() throws MyExceptions, IOException {
        IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
        int recordCheck = censusAnalyserProblem.loadIndianCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, recordCheck);
    }

    @Test
    public void givenCensusAnalyser_WhenIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int recordCheck = censusAnalyserProblem.loadIndianCensusData(WRONG_STATE_CENSUS_DATA_PATH);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenIncorrectPath_ShouldReturnException() throws MyExceptions, IOException {
        try {
            IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
            int recordCheck = censusAnalyserProblem.loadIndianCensusData(WRONG_DATA_PATH);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.PATH_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenCensusAnalyser_WhenDelimiterIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
            int recordCheck = censusAnalyserProblem.loadIndianCensusData(STATE_CENSUS_DATA_PATH);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenCensusAnalyserCode_WhenImproper_ShouldThrowException() throws MyExceptions, IOException {
        int checkCode = codeAnalyserProblem.loadIndianStateCodeData(INDIAN_STATE_CODE_DATA_PATH);
       Assert.assertEquals(37,INDIAN_STATE_CODE_DATA_PATH);
    }
}