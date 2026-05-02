package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.interfaces.Payable;
import java.io.Serializable;
import java.util.Date;

public abstract class Bill implements Payable, Serializable {
    private static final long serialVersionUID = 1L;

    protected String billId;
    protected Appointment appointment;
    protected Date billingDate;

    public Bill(String billId, Appointment appointment) {
        this.billId = billId;
        this.appointment = appointment;
        this.billingDate = new Date();
    }

    public String getBillId() {
        return billId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    // Abstract method to demonstrate polymorphism in billing calculation
    public abstract double calculateBaseAmount();

    @Override
    public BillSummary generateBill() {
        double baseAmount = calculateBaseAmount();
        double tax = baseAmount * Constants.TAX_RATE;
        double total = baseAmount + tax;

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
