package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Specialization;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorService {
    private DataStore<Doctor> doctorStore;

    public DoctorService(DataStore<Doctor> doctorStore) {
        this.doctorStore = doctorStore;
    }

    public void addDoctor(Doctor doctor) {
        doctorStore.add(doctor);
    }

    public void removeDoctor(Doctor doctor) {
        doctorStore.remove(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorStore.getAll();
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorStore.getAll().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public List<Doctor> searchDoctors(String query) {
        return doctorStore.search(query);
    }

    // Java Streams Bonus: Filter by specialization
    public List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
        return doctorStore.getAll().stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    // Java Streams Bonus: Compute average fee
    public double getAverageConsultationFee() {
        return doctorStore.getAll().stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }
}
