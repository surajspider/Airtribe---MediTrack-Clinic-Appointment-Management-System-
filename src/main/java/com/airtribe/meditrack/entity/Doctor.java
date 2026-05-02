package com.airtribe.meditrack.entity;

public class Doctor extends Person {
    private static final long serialVersionUID = 1L;

    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, int age, String contactNumber, Specialization specialization, double consultationFee) {
        super(id, name, age, contactNumber);
        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public void displayDetails() {
        System.out.printf("Doctor [ID: %s, Name: %s, Age: %d, Contact: %s, Spec: %s, Fee: $%.2f]%n",
                id, name, age, contactNumber, specialization, consultationFee);
    }
}
