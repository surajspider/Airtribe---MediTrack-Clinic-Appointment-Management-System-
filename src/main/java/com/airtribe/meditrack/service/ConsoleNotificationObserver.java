package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public class ConsoleNotificationObserver implements AppointmentObserver {
    @Override
    public void onAppointmentScheduled(Appointment appointment) {
        System.out.println("\n[NOTIFICATION] Reminder: Appointment scheduled successfully.");
        System.out.println("Details: " + appointment.toString() + "\n");
    }

    @Override
    public void onAppointmentCancelled(Appointment appointment) {
        System.out.println("\n[NOTIFICATION] Alert: Appointment has been cancelled.");
        System.out.println("Details: " + appointment.toString() + "\n");
    }
}
