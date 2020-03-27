package com.bridgelabz.censusanalyserproject;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCode {

    @CsvBindByName(column = "SrNo",required = true)
    private int srNo;

    @CsvBindByName(column = "State",required = true)
    private String state;

    @CsvBindByName(column = "Name",required = true)
    private String name;

    @CsvBindByName(column = "TIN",required = true)
    private int tin;

    @CsvBindByName(column = "StateCode",required = true)
    private String stateCode;

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public int getSrNo() {
        return srNo;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public int getTin() {
        return tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    @Override
    public String toString() {
        return "com.bridgelabz.censusanalyserproject.IndianStateCode {"+
                "SrNo='" + srNo + '\'' +
                " ,State='" + state + '\'' +
                " ,Name='" + name + '\'' +
                " ,TIN='" + tin + '\'' +
                " ,StateCode='" + stateCode + '\'' +
                '}';
    }
}