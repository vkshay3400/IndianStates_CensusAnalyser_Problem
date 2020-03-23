package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CsvBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder {
    <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CsvBuilderException;

    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CsvBuilderException;
}