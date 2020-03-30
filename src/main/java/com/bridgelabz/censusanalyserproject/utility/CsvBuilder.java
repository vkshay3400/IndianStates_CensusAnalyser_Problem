package com.bridgelabz.censusanalyserproject.utility;

import com.bridgelabz.censusanalyserproject.exception.CsvBuilderException;
import com.bridgelabz.censusanalyserproject.exception.MyExceptions;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CsvBuilder implements IcsvBuilder {
    public <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CsvBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(MyExceptions.Exception_Type.FILE_NOT_FOUND, "Wrong file");
        }
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) {
        try {
            return this.getCSVBean(reader, csvClass).parse();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) {
        try {
            return this.getCSVBean(reader, csvClass).iterator();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return null;
    }
}