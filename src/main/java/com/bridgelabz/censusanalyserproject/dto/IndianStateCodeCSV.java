package com.bridgelabz.censusanalyserproject.dto;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCodeCSV {

    public IndianStateCodeCSV() {
    }

    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "TIN", required = true)
    public int tin;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;
}