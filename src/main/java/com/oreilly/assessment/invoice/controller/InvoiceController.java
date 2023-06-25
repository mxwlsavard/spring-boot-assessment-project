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

    @GetMapping("/customers/{id}/tenders/")
    public Map<Long, String> findInvoiceTendersByCustomerID(@PathVariable Long id) {
        LOG.debug("Received invoice tenders by customer ID request: " + id);

        return invoiceService.findInvoiceTendersByCustomerID(id);
    }
}
