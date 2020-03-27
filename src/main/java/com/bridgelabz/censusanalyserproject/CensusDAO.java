package com.bridgelabz.censusanalyserproject;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(IndianCensusData csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.area = csvStateCensus.getAreaInSqKm();
        this.density = csvStateCensus.getDensityPerSqKm();
    }

    public CensusDAO(IndianStateCode csvStateCode) {
        this.stateCode = csvStateCode.getStateCode();
    }
}