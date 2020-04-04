package com.bridgelabz.censusanalyserproject.censusdao;

import com.bridgelabz.censusanalyserproject.statecensus.dto.IndianCensusDataCSV;
import com.bridgelabz.censusanalyserproject.statecensus.dto.USCensusCSV;
import com.bridgelabz.censusanalyserproject.service.IndiaUSStateCensusAnalyser;

import java.util.Comparator;

public class CensusDAO {
    public int population;
    public double areaInSqKm;
    public double densityPerSqKm;
    public int tin;
    public int srNo;
    public String state;
    public String stateCode;

    public int getPopulation() {
        return population;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public CensusDAO(IndianCensusDataCSV csvStateCensus) {
        this.state = csvStateCensus.state;
        this.population = csvStateCensus.population;
        this.areaInSqKm = csvStateCensus.areaInSqKm;
        this.densityPerSqKm = csvStateCensus.densityPerSqKm;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        this.state = usCensusCSV.state;
        this.stateCode = usCensusCSV.stateId;
        this.areaInSqKm = usCensusCSV.totalArea;
        this.densityPerSqKm = usCensusCSV.densityPerSqKm;
        this.population = usCensusCSV.population;
    }

    public static Comparator<CensusDAO> getSortComparator(IndiaUSStateCensusAnalyser.SORTING_MODE mode) {
        if (mode.equals(IndiaUSStateCensusAnalyser.SORTING_MODE.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(IndiaUSStateCensusAnalyser.SORTING_MODE.POPULATION))
            return Comparator.comparing(census -> census.population);
        if (mode.equals(IndiaUSStateCensusAnalyser.SORTING_MODE.DENSITY))
            return Comparator.comparing(census -> census.densityPerSqKm);
        if (mode.equals(IndiaUSStateCensusAnalyser.SORTING_MODE.AREA))
            return Comparator.comparing(census -> census.areaInSqKm);
        if (mode.equals(IndiaUSStateCensusAnalyser.SORTING_MODE.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        return null;
    }

    public Object getCensusDTO(IndiaUSStateCensusAnalyser.COUNTRY country) {
        if (country.equals(IndiaUSStateCensusAnalyser.COUNTRY.INDIA))
            return new IndianCensusDataCSV(state, stateCode, population, areaInSqKm, densityPerSqKm);
        if (country.equals(IndiaUSStateCensusAnalyser.COUNTRY.US))
            return new USCensusCSV(stateCode, state, population, areaInSqKm, densityPerSqKm);
        return null;
    }
}