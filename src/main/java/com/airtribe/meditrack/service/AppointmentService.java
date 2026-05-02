package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.AppointmentStatus;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppointmentService {
    private DataStore<Appointment> appointmentStore;
    private List<AppointmentObserver> observers;

    public AppointmentService(DataStore<Appointment> appointmentStore) {
        this.appointmentStore = appointmentStore;
        this.observers = new ArrayList<>();
    }

    public void addObserver(AppointmentObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(AppointmentObserver observer) {
        this.observers.remove(observer);
    }

    public void scheduleAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointmentStore.add(appointment);
        notifyScheduled(appointment);
    }

    public void cancelAppointment(String appointmentId) throws AppointmentNotFoundException {
        Optional<Appointment> appointmentOpt = appointmentStore.getAll().stream()
                .filter(a -> a.getAppointmentId().equals(appointmentId))
                .findFirst();

        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(AppointmentStatus.CANCELLED);
            notifyCancelled(appointment);
        } else {
            throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found.");
        }
    }

    public List<Appointment> getAllAppointments() {
        return appointmentStore.getAll();
    }

    // Streams Analytics: Appointments per doctor
    public void printAnalytics() {
        System.out.println("--- Appointment Analytics ---");
        Map<Doctor, Long> appointmentsPerDoctor = appointmentStore.getAll().stream()
                .collect(Collectors.groupingBy(Appointment::getDoctor, Collectors.counting()));

        appointmentsPerDoctor.forEach((doctor, count) -> {
            System.out.println("Doctor: " + doctor.getName() + " - Appointments: " + count);
        });
        System.out.println("-----------------------------");
    }

    private void notifyScheduled(Appointment appointment) {
        for (AppointmentObserver observer : observers) {
            observer.onAppointmentScheduled(appointment);
        }
    }

    private void notifyCancelled(Appointment appointment) {
        for (AppointmentObserver observer : observers) {
            observer.onAppointmentCancelled(appointment);
        }
    }
}
