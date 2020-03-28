package com.bridgelabz.censusanalyserproject;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;

    public CensusDAO(IndianCensusData csvIndiaCensus) {
        this.state = csvIndiaCensus.getState();
        this.population = csvIndiaCensus.getPopulation();
        this.area = csvIndiaCensus.getAreaInSqKm();
        this.density = csvIndiaCensus.getDensityPerSqKm();
    }

    public CensusDAO(IndianStateCode csvStateCode) {
        this.state = csvStateCode.getStateCode();
    }
}