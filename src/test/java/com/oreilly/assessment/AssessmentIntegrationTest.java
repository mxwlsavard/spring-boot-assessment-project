package com.oreilly.assessment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oreilly.assessment.invoice.dao.InvoiceRepository;
import com.oreilly.assessment.invoice.data.Invoice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssessmentIntegrationTest {
    private static final Logger LOG = LoggerFactory.getLogger(AssessmentIntegrationTest.class);

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private  InvoiceRepository invoiceRepository;

    @BeforeAll
    public void loadDB() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            invoiceRepository.save(new Invoice(100L, 54L, mapper.readTree("{\"time\": \"19:53\", \"tenderDetails\": {\"amount\": 23.43, \"type\": \"cash\"}, \"storeNumber\":\"999\"}")));
            invoiceRepository.save(new Invoice(200L, 55L, mapper.readTree("{\"time\": \"12:00\", \"tenderDetails\": {\"amount\": 4.95, \"type\": \"cash\"}, \"storeNumber\":\"999\"}")));
            invoiceRepository.save(new Invoice(200L, 56L, mapper.readTree("{\"time\": \"08:49\", \"tenderDetails\": {\"amount\": 100.12, \"type\": \"credit\"}, \"storeNumber\":\"999\"}")));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getMultipleTenders() {
        long id = 200L;
        JsonNode expected;
        JsonNode actual;
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/customers/" + id + "/tenders", String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            actual = mapper.readTree(response);
            expected = mapper.readTree("{\"55\":\"cash\",\"56\":\"credit\"}");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        assertTrue(expected.equals(actual));
    }

    @Test
    public void getForCustomerWithNoTenders() {
        long id = 0L;
        JsonNode expected;
        JsonNode actual;
        String response = this.restTemplate.getForObject("http://localhost:" + port + "/customers/" + id + "/tenders", String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            actual = mapper.readTree(response);
            expected = mapper.readTree("{}"); //returns empty object
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        assertTrue(expected.equals(actual));
    }
}
