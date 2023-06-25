package com.oreilly.assessment.invoice.data;

import javax.persistence.*;

@Entity
public class Invoice {
    @Column(name="customer_id")
    private Long customerID;

    @Id
    @Column(name="invoice_id")
    private Long invoiceID;

//    @Column(name="invoice_data")
//    private String invoiceDataJSON;

//    @Transient //excludes field from the JPA model
    @Column(name="invoice_data")
    @Convert(converter = InvoiceData.InvoiceDataConverter.class)
    private InvoiceData invoiceData;

    public Invoice() {
    }

    public Invoice(Long customerID, Long invoiceID, InvoiceData invoiceData) {
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

//    public String getInvoiceDataJSON() {
//        return invoiceDataJSON;
//    }
//
//    public void setInvoiceDataJSON(String invoiceData) {
//        this.invoiceDataJSON = invoiceData;
//    }

    public InvoiceData getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(InvoiceData invoiceData) {
        this.invoiceData = invoiceData;
    }
}
