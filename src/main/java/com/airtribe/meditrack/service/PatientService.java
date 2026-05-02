package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.Optional;

public class PatientService {
    private DataStore<Patient> patientStore;

    public PatientService(DataStore<Patient> patientStore) {
        this.patientStore = patientStore;
    }

    public void addPatient(Patient patient) {
        patientStore.add(patient);
    }

    public void removePatient(Patient patient) {
        patientStore.remove(patient);
    }

    public List<Patient> getAllPatients() {
        return patientStore.getAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return patientStore.getAll().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Polymorphism: Overloaded searchPatient methods
    public List<Patient> searchPatient(String name) {
        return patientStore.getAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    public Optional<Patient> searchPatientById(String id) {
        return patientStore.getAll().stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public List<Patient> searchPatient(int age) {
        return patientStore.getAll().stream()
                .filter(p -> p.getAge() == age)
                .collect(java.util.stream.Collectors.toList());
    }

    // Using the generic interface search
    public List<Patient> searchPatientByQuery(String query) {
        return patientStore.search(query);
    }
}
