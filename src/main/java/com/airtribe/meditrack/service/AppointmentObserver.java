package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;

public interface AppointmentObserver {
    void onAppointmentScheduled(Appointment appointment);
    void onAppointmentCancelled(Appointment appointment);
}
