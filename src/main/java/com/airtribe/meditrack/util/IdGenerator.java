package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton class to generate unique IDs across the system.
 * Uses lazy initialization and thread-safe AtomicInteger.
 */
public class IdGenerator {
    private static IdGenerator instance;

    private AtomicInteger doctorCounter;
    private AtomicInteger patientCounter;
    private AtomicInteger appointmentCounter;
    private AtomicInteger billCounter;

    private IdGenerator() {
        doctorCounter = new AtomicInteger(100);
        patientCounter = new AtomicInteger(100);
        appointmentCounter = new AtomicInteger(100);
        billCounter = new AtomicInteger(100);
    }

    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public String generateDoctorId() {
        return "DOC" + doctorCounter.incrementAndGet();
    }

    public String generatePatientId() {
        return "PAT" + patientCounter.incrementAndGet();
    }

    public String generateAppointmentId() {
        return "APP" + appointmentCounter.incrementAndGet();
    }

    public String generateBillId() {
        return "BIL" + billCounter.incrementAndGet();
    }
}
