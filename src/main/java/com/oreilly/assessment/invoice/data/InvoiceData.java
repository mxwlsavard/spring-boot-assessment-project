package com.oreilly.assessment.invoice.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.Serializable;
import java.time.LocalTime;

public class InvoiceData implements Serializable {
    private LocalTime time;
    private TenderDetails tenderDetails;
    private int storeNumber;

    public InvoiceData() {
    }

    public InvoiceData(LocalTime time, TenderDetails tenderDetails, int storeNumber) {
        this.time = time;
        this.tenderDetails = tenderDetails;
        this.storeNumber = storeNumber;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public TenderDetails getTenderDetails() {
        return tenderDetails;
    }

    public void setTenderDetails(TenderDetails tenderDetails) {
        this.tenderDetails = tenderDetails;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    @Converter
    static class InvoiceDataConverter implements AttributeConverter<InvoiceData, String> {

        @Override
        public String convertToDatabaseColumn(InvoiceData invoiceData) {
            ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).writer();
            try {
                return writer.writeValueAsString(invoiceData);
            } catch (JsonProcessingException e) {
                //TODO find better exception to throw
                throw new RuntimeException(e);
            }
        }

        @Override
        public InvoiceData convertToEntityAttribute(String jsonString) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            try {
                return mapper.readValue(jsonString, InvoiceData.class);
            } catch (JsonProcessingException e) {
                //TODO find better exception to throw
                throw new RuntimeException(e);
            }
        }
    }
}