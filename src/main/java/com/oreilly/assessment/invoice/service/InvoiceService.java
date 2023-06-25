package com.oreilly.assessment.invoice.service;

import com.oreilly.assessment.invoice.dao.InvoiceRepository;
import com.oreilly.assessment.invoice.data.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * a lot of people create a service interface
 * and a service impl class but that seems
 * a waste for simple classes like this.
 */
@Service
public class InvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Map<Long, String> findInvoiceTendersByCustomerID(Long id) {
        LOG.debug("finding invoice tenders by customer ID: " + id);

        List<Invoice> invoices = invoiceRepository.findAllByCustomerID(id);
        Map<Long, String> invoiceTenders = new HashMap<>();
        for(Invoice invoice: invoices) {
            invoiceTenders.put(invoice.getInvoiceID(), invoice.getInvoiceData().get("tenderDetails").get("type").asText());
        }
        return invoiceTenders;
    }

}
