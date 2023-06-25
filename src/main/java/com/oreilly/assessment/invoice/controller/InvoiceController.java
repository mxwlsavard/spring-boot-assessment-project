package com.oreilly.assessment.invoice.controller;

import com.oreilly.assessment.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InvoiceController {
    //TODO add logs throughout application

    final private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/tenders/{id}")
    public Map<Long, String> findInvoiceTendersByCustomerID(@PathVariable Long id) {
        return invoiceService.findInvoiceTendersByCustomerID(id);
    }
}
