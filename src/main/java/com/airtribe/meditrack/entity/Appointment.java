package com.airtribe.meditrack.entity;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private Date appointmentDate;
    private AppointmentStatus status;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, Date appointmentDate) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.status = AppointmentStatus.PENDING;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public Appointment clone() {
        try {
            // Shallow copy: Patient and Doctor references remain the same
            Appointment cloned = (Appointment) super.clone();
            // Date is mutable, so we make a deep copy of the Date object
            if (this.appointmentDate != null) {
                cloned.appointmentDate = new Date(this.appointmentDate.getTime());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed", e);
        }
    }

    @Override
    public String toString() {
        return String.format("Appointment[ID: %s, Patient: %s, Doctor: %s, Date: %s, Status: %s]",
                appointmentId, patient.getName(), doctor.getName(), appointmentDate, status);
    }
}
