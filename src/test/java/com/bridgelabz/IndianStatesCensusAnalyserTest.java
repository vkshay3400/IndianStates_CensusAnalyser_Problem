package com.bridgelabz;

import com.bridgelabz.censusanalyserproject.IndianStatesCensusAnalyser;
import com.bridgelabz.exception.MyExceptions;
import org.junit.Assert;
import org.junit.Test;

public class IndianStatesCensusAnalyserTest {
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();

    @Test
    public void CensusAnalyserProblemTest() throws MyExceptions {
        IndianStatesCensusAnalyser censusAnalyserProblem = new IndianStatesCensusAnalyser();
        int recordCheck = censusAnalyserProblem.loadIndianCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, recordCheck);
    }
}