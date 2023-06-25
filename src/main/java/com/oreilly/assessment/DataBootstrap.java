package com.oreilly.assessment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oreilly.assessment.invoice.dao.InvoiceRepository;
import com.oreilly.assessment.invoice.data.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(DataBootstrap.class);

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public DataBootstrap(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            invoiceRepository.save(new Invoice(1L, 54L, mapper.readTree("{\"time\": \"19:53\", \"tenderDetails\": {\"amount\": 23.43, \"type\": \"cash\"}, \"storeNumber\":\"999\"}")));
            invoiceRepository.save(new Invoice(2L, 55L, mapper.readTree("{\"time\": \"12:00\", \"tenderDetails\": {\"amount\": 4.95, \"type\": \"cash\"}, \"storeNumber\":\"999\"}")));
            invoiceRepository.save(new Invoice(2L, 56L, mapper.readTree("{\"time\": \"08:49\", \"tenderDetails\": {\"amount\": 100.12, \"type\": \"credit\"}, \"storeNumber\":\"999\"}")));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOG.debug("repository loaded: " + (invoiceRepository.findAllByCustomerID(2L).size() == 2));

//        TenderDetails tenderDetails = new TenderDetails(1.2, "cash");
//        invoiceRepository.save(new Invoice(1L, 54L, new InvoiceData(LocalTime.now(), tenderDetails, 999)));//"{\"time\": 19:53, \"tenderDetails\": {\"amount\": 23.43, \"type\": \"cash\"}, storeNumber:\"999\"}"));
//        invoiceRepository.save(new Invoice(2L, 55L, new InvoiceData(LocalTime.now(), tenderDetails, 999)));
//        invoiceRepository.save(new Invoice(2L, 56L, new InvoiceData(LocalTime.now(), tenderDetails, 999)));
//        LOG.debug("repository loaded: " + (invoiceRepository.findAllByCustomerID(2L).size() == 2));

    }
}
