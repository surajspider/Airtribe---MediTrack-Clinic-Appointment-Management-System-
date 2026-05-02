package com.airtribe.meditrack.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Immutable class representing a bill summary.
 * All fields are final, no setters are provided, making it thread-safe.
 */
public final class BillSummary implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String billId;
    private final String patientId;
    private final String doctorId;
    private final double amount;
    private final double tax;
    private final double totalAmount;
    private final Date billingDate;

    public BillSummary(String billId, String patientId, String doctorId, double amount, double tax, double totalAmount, Date billingDate) {
        this.billId = billId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.amount = amount;
        this.tax = tax;
        this.totalAmount = totalAmount;
        // Date is mutable, so we make a defensive copy
        this.billingDate = new Date(billingDate.getTime());
    }

    public String getBillId() {
        return billId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public double getAmount() {
        return amount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getBillingDate() {
        // Return a copy to maintain immutability
        return new Date(billingDate.getTime());
    }

    @Override
    public String toString() {
        return String.format("BillSummary[ID: %s, Patient: %s, Total: $%.2f, Date: %s]", billId, patientId, totalAmount, billingDate);
    }
}
