package com.oreilly.assessment.invoice.controller;

import com.oreilly.assessment.invoice.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InvoiceController {
    private static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    final private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /*
    This application assumes there is only 1 tender used per invoice

    This endpoint returns a response like:
    {
        "55": "cash",
        "56": "cash"
    }
    I wrote this to *literally* match the requirements (as I interpreted them) but I think a better design
    would be to return the entire invoice entity from an /invoices endpoint and leave it up to the client
    to parse the tender field. You could let the client filter out fields it doesn't need by doing
    something like this https://iamvickyav.medium.com/spring-boot-dynamically-ignore-fields-while-converting-java-object-to-json-e8d642088f55
     */
    @GetMapping("/customers/{id}/tenders")
    public Map<Long, String> findInvoiceTendersByCustomerID(@PathVariable Long id) {
        LOG.debug("Received invoice tenders by customer ID request: " + id);

        return invoiceService.findInvoiceTendersByCustomerID(id);
    }
}
