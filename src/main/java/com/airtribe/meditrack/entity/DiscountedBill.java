package com.airtribe.meditrack.entity;

public class DiscountedBill extends Bill {
    private static final long serialVersionUID = 1L;
    private double discountPercentage;

    public DiscountedBill(String billId, Appointment appointment, double discountPercentage) {
        super(billId, appointment);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateBaseAmount() {
        double fee = appointment.getDoctor().getConsultationFee();
        return fee - (fee * (discountPercentage / 100));
    }
}
