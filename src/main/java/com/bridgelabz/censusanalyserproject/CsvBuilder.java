package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CsvBuilderException;
import com.bridgelabz.exception.MyExceptions;
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
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CsvBuilderException {
        return this.getCSVBean(reader,csvClass).parse();
    }

    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CsvBuilderException {
        return this.getCSVBean(reader,csvClass).iterator();
    }
}