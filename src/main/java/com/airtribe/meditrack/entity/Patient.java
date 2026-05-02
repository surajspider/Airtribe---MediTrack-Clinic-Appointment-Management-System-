package com.airtribe.meditrack.entity;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person implements Cloneable {
    private static final long serialVersionUID = 1L;

    private String medicalHistory;
    private List<String> currentMedications; // Used to demonstrate deep cloning

    public Patient(String id, String name, int age, String contactNumber, String medicalHistory) {
        super(id, name, age, contactNumber);
        this.medicalHistory = medicalHistory;
        this.currentMedications = new ArrayList<>();
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<String> getCurrentMedications() {
        return currentMedications;
    }

    public void addMedication(String medication) {
        this.currentMedications.add(medication);
    }

    @Override
    public void displayDetails() {
        System.out.printf("Patient [ID: %s, Name: %s, Age: %d, Contact: %s, History: %s, Meds: %d]%n",
                id, name, age, contactNumber, medicalHistory, currentMedications.size());
    }

    /**
     * Demonstrates Deep Copy. The list of current medications is explicitly cloned
     * so that modifications to the clone's list do not affect the original object.
     */
    @Override
    public Patient clone() {
        try {
            // Shallow copy via Object.clone()
            Patient cloned = (Patient) super.clone();
            // Deep copy of the mutable collection
            cloned.currentMedications = new ArrayList<>(this.currentMedications);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed", e);
        }
    }
}
