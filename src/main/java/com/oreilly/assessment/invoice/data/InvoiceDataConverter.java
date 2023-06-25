package com.oreilly.assessment.invoice.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
class InvoiceDataConverter implements AttributeConverter<JsonNode, String> {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceDataConverter.class);

    @Override
    public String convertToDatabaseColumn(JsonNode invoiceData) {
        ObjectWriter writer = new ObjectMapper().registerModule(new JavaTimeModule()).writer();
        try {
            return writer.writeValueAsString(invoiceData);
        } catch (JsonProcessingException e) {
            //TODO find better exception to throw
            LOG.error("error marshalling invoice data into json string");
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            //TODO find better exception to throw
            LOG.error("error unmarshalling invoice data json string into JsonNode object");
            throw new RuntimeException(e);
        }
    }
}