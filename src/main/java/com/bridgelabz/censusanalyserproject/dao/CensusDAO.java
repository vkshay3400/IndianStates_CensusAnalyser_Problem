package com.bridgelabz.censusanalyserproject.dao;

import com.bridgelabz.censusanalyserproject.dto.IndianCensusData;
import com.bridgelabz.censusanalyserproject.dto.USCensusCSV;
import com.bridgelabz.censusanalyserproject.service.StateCensusAnalyser;

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

    public CensusDAO(IndianCensusData csvStateCensus) {
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

    public static Comparator<CensusDAO> getSortComparator(StateCensusAnalyser.SORTING_MODE mode) {
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.POPULATION))
            return Comparator.comparing(census -> census.population);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.DENSITY))
            return Comparator.comparing(census -> census.densityPerSqKm);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.AREA))
            return Comparator.comparing(census -> census.areaInSqKm);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        return null;
    }

    public Object getCensusDTO(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new IndianCensusData(state, stateCode, population, areaInSqKm, densityPerSqKm);
        if (country.equals(StateCensusAnalyser.COUNTRY.US))
            return new USCensusCSV(stateCode, state, population, areaInSqKm, densityPerSqKm);
        return null;
    }
}