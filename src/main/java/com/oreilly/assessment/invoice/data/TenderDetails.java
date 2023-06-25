package com.oreilly.assessment.invoice.data;

import java.io.Serializable;

public class TenderDetails implements Serializable {
    private double amount;
    private String type;

    public TenderDetails() {
    }

    public TenderDetails(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
