package com.airtribe.meditrack.entity;

public class StandardBill extends Bill {
    private static final long serialVersionUID = 1L;

    public StandardBill(String billId, Appointment appointment) {
        super(billId, appointment);
    }

    @Override
    public double calculateBaseAmount() {
        // Base amount is just the doctor's consultation fee
        return appointment.getDoctor().getConsultationFee();
    }
}
