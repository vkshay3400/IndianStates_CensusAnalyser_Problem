package com.bridgelabz.censusanalyserproject;

import com.opencsv.bean.CsvBindByName;

public class IndianCensusData {

    //SETTER
    public void setState(String state) {
        this.state = state;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    //GETTER
    public String getState() {
        return state;
    }

    public String getPopulation() {
        return population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public String population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public String areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public String densityPerSqKm;

    @Override
    public String toString() {
        return "CensusData{" +
                "state='" + state + '\'' +
                ", population='" + population + '\'' +
                ", areaInSqKm='" + areaInSqKm + '\'' +
                ", densityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}
