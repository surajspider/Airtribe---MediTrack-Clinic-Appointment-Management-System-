package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.strategy.BillingStrategy;
import com.airtribe.meditrack.strategy.StandardBillingStrategy;
import java.io.Serializable;
import java.util.Date;

public abstract class Bill implements Payable, Serializable {
    private static final long serialVersionUID = 1L;

    protected String billId;
    protected Appointment appointment;
    protected Date billingDate;
    protected BillingStrategy strategy;

    public Bill(String billId, Appointment appointment) {
        this.billId = billId;
        this.appointment = appointment;
        this.billingDate = new Date();
        this.strategy = new StandardBillingStrategy(); // Default strategy
    }

    public void setBillingStrategy(BillingStrategy strategy) {
        this.strategy = strategy;
    }

    public String getBillId() {
        return billId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    // Abstract method to demonstrate polymorphism in base billing calculation
    public abstract double calculateBaseAmount();

    @Override
    public BillSummary generateBill() {
        double baseAmount = calculateBaseAmount();
        
        // Delegate tax and total calculation to the Strategy
        double tax = strategy.calculateTax(baseAmount, Constants.TAX_RATE);
        double total = strategy.calculateTotal(baseAmount, Constants.TAX_RATE);

        return new BillSummary(
                billId,
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                baseAmount,
                tax,
                total,
                billingDate
        );
    }
}
