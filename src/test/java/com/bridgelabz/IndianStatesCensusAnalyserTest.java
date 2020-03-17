package com.bridgelabz;

import com.bridgelabz.censusanalyserproject.IndianStatesCensusAnalyser;
import com.bridgelabz.exception.MyExceptions;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IndianStatesCensusAnalyserTest {
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_DATA_PATH = "./src/test/resources/StateCensusData.sh";
    IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();

    @Test
    public void CensusAnalyserProblemTest() throws MyExceptions, IOException {
        IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
        int recordCheck = censusAnalyserProblem.loadIndianCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, recordCheck);
    }

    @Test
    public void CensusAnalyser_WhenIncorrect_ShouldReturnException() throws MyExceptions, IOException {
        try {
            int recordCheck = censusAnalyserProblem.loadIndianCensusData(WRONG_STATE_CENSUS_DATA_PATH);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void CensusAnalyser_WhenIncorrectPath_ShouldReturnException() throws MyExceptions, IOException {
        try {
            IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
            int recordCheck = censusAnalyserProblem.loadIndianCensusData(WRONG_DATA_PATH);
        } catch (MyExceptions e) {
            Assert.assertEquals(MyExceptions.Exception.PATH_NOT_FOUND, e.type);
        }
    }
}