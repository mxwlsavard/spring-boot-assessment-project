package com.oreilly.assessment.invoice.data;

import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

@Entity
public class Invoice {
    @Column(name="customer_id")
    private Long customerID;

    @Id
    @Column(name="invoice_id")
    private Long invoiceID;

    @Column(name="invoice_data")
    @Convert(converter = InvoiceDataConverter.class)
    private JsonNode invoiceData;

    public Invoice() {
    }

    public Invoice(Long customerID, Long invoiceID, JsonNode invoiceData) {
        this.customerID = customerID;
        this.invoiceID = invoiceID;
        this.invoiceData = invoiceData;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Long invoiceID) {
        this.invoiceID = invoiceID;
    }

    public JsonNode getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(JsonNode invoiceData) {
        this.invoiceData = invoiceData;
    }
}
