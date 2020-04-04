package com.bridgelabz.censusanalyserproject.statecensus.dto;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusDataCSV {
    public IndianCensusDataCSV() {
    }

    public IndianCensusDataCSV(String state, String stateCode, int population, Double areaInSqKm, Double densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public double densityPerSqKm;
}