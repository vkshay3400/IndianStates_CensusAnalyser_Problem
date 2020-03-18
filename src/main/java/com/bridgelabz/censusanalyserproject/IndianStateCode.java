package com.bridgelabz.censusanalyserproject;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCode {

    //SETTER
    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    //GETTER
    public String getSrNo() {
        return srNo;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getTin() {
        return tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    @CsvBindByName(column = "SrNo",required = true)
    public String srNo;

    @CsvBindByName(column = "State",required = true)
    public String state;

    @CsvBindByName(column = "Name",required = false)
    public String name;

    @CsvBindByName(column = "TIN",required = true)
    public String tin;

    @CsvBindByName(column = "StateCode",required = true)
    public String stateCode;

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
//public class IndianStateCode {
//    //SETTER
//    public void setSrNo(String srNo) {
//        this.srNo = srNo;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setTin(String tin) {
//        this.tin = tin;
//    }
//
//    public void setStateCode(String stateCode) {
//        this.stateCode = stateCode;
//    }
//
//    //GETTER
//    public String getSrNo() {
//        return srNo;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getTin() {
//        return tin;
//    }
//
//    public String getStateCode() {
//        return stateCode;
//    }
//
//    @CsvBindByName(column = "SrNo",required = true)
//    public String srNo;
//
//    @CsvBindByName(column = "State",required = true)
//    public String state;
//
//    @CsvBindByName(column = "Name",required = true)
//    public String name;
//
//    @CsvBindByName(column = "TIN",required = true)
//    public String tin;
//
//    @CsvBindByName(column = "StateCode",required = true)
//    public String stateCode;
//
//    @Override
//    public String toString() {
//        return "com.bridgelabz.censusanalyserproject.IndianStateCode {"+
//                "SrNo='" + srNo + '\'' +
//                " ,State='" + state + '\'' +
//                " ,Name='" + name + '\'' +
//                " ,TIN='" + tin + '\'' +
//                " ,StateCode='" + stateCode + '\'' +
//                '}';
//    }
//}