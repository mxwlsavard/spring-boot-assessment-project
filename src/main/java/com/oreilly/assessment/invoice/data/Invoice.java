package com.oreilly.assessment.invoice.data;

import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

@Entity
public class Invoice {
    @Column(name="customer_id")
    private long customerID;

    @Id
    @Column(name="invoice_id")
    private long invoiceID;

    @Column(name="invoice_data")
    @Convert(converter = InvoiceDataConverter.class)
    private JsonNode invoiceData;

    public Invoice() {
    }

    public Invoice(long customerID, long invoiceID, JsonNode invoiceData) {
        this.customerID = customerID;
        this.invoiceID = invoiceID;
        this.invoiceData = invoiceData;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(long invoiceID) {
        this.invoiceID = invoiceID;
    }

    public JsonNode getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(JsonNode invoiceData) {
        this.invoiceData = invoiceData;
    }
}
