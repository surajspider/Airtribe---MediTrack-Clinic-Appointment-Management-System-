package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static DoctorService doctorService;
    private static PatientService patientService;
    private static AppointmentService appointmentService;
    private static final String DATA_FILE = "meditrack_data.ser";

    public static void main(String[] args) {
        boolean loadData = false;
        if (args.length > 0 && "--loadData".equals(args[0])) {
            loadData = true;
        }

        initializeSystem(loadData);
        runConsoleMenu();
    }

    @SuppressWarnings("unchecked")
    private static void initializeSystem(boolean loadData) {
        DataStore<Doctor> doctorStore = new DataStore<>();
        DataStore<Patient> patientStore = new DataStore<>();
        DataStore<Appointment> appointmentStore = new DataStore<>();

        if (loadData) {
            Object[] data = (Object[]) CSVUtil.deserializeData(DATA_FILE);
            if (data != null) {
                doctorStore = (DataStore<Doctor>) data[0];
                patientStore = (DataStore<Patient>) data[1];
                appointmentStore = (DataStore<Appointment>) data[2];
                System.out.println("Data loaded successfully from " + DATA_FILE);
            } else {
                System.out.println("No saved data found. Starting fresh.");
            }
        }

        doctorService = new DoctorService(doctorStore);
        patientService = new PatientService(patientStore);
        appointmentService = new AppointmentService(appointmentStore);

        // Register Observer
        appointmentService.addObserver(new ConsoleNotificationObserver());
    }

    private static void saveData() {
        Object[] data = {
                doctorService.getAllDoctors() instanceof DataStore ? doctorService.getAllDoctors() : createStoreFromList(doctorService.getAllDoctors()),
                patientService.getAllPatients() instanceof DataStore ? patientService.getAllPatients() : createStoreFromList(patientService.getAllPatients()),
                appointmentService.getAllAppointments() instanceof DataStore ? appointmentService.getAllAppointments() : createStoreFromList(appointmentService.getAllAppointments())
        };
        CSVUtil.serializeData(data, DATA_FILE);
        System.out.println("Data saved successfully.");
    }

    private static <T> DataStore<T> createStoreFromList(List<T> list) {
        DataStore<T> store = new DataStore<>();
        list.forEach(store::add);
        return store;
    }

    private static void runConsoleMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MediTrack Clinic System ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Generate Bill");
            System.out.println("6. Search Doctors");
            System.out.println("7. System Analytics");
            System.out.println("8. Save and Exit");
            System.out.println("9. Smart Doctor Recommendation (AI)");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1":
                        System.out.print("Enter Doctor Name: ");
                        String dName = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int dAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Contact (10 digits): ");
                        String dContact = scanner.nextLine();
                        System.out.print("Enter Specialization (CARDIOLOGY, DERMATOLOGY, NEUROLOGY, ORTHOPEDICS, PEDIATRICS, GENERAL_MEDICINE): ");
                        Specialization spec = Specialization.valueOf(scanner.nextLine().toUpperCase());
                        System.out.print("Enter Consultation Fee: ");
                        double fee = Double.parseDouble(scanner.nextLine());

                        Doctor doctor = new Doctor(IdGenerator.getInstance().generateDoctorId(), dName, dAge, dContact, spec, fee);
                        doctorService.addDoctor(doctor);
                        System.out.println("Doctor added successfully.");
                        break;
                    case "2":
                        System.out.print("Enter Patient Name: ");
                        String pName = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int pAge = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Contact (10 digits): ");
                        String pContact = scanner.nextLine();
                        System.out.print("Enter Medical History: ");
                        String history = scanner.nextLine();

                        Patient patient = new Patient(IdGenerator.getInstance().generatePatientId(), pName, pAge, pContact, history);
                        patientService.addPatient(patient);
                        System.out.println("Patient added successfully.");
                        break;
                    case "3":
                        System.out.print("Enter Doctor ID: ");
                        String docId = scanner.nextLine();
                        System.out.print("Enter Patient ID: ");
                        String patId = scanner.nextLine();

                        Doctor targetDoctor = doctorService.getDoctorById(docId).orElse(null);
                        Patient targetPatient = patientService.getPatientById(patId).orElse(null);

                        if (targetDoctor != null && targetPatient != null) {
                            Appointment appointment = new Appointment(
                                    IdGenerator.getInstance().generateAppointmentId(),
                                    targetPatient, targetDoctor, new Date());
                            appointmentService.scheduleAppointment(appointment);
                        } else {
                            System.out.println("Invalid Doctor ID or Patient ID.");
                        }
                        break;
                    case "4":
                        System.out.print("Enter Appointment ID to cancel: ");
                        String appIdToCancel = scanner.nextLine();
                        appointmentService.cancelAppointment(appIdToCancel);
                        break;
                    case "5":
                        System.out.print("Enter Appointment ID for billing: ");
                        String appToBill = scanner.nextLine();
                        Appointment billingApp = appointmentService.getAllAppointments().stream()
                                .filter(a -> a.getAppointmentId().equals(appToBill))
                                .findFirst().orElse(null);

                        if (billingApp != null) {
                            System.out.print("Discount Type (STANDARD/DISCOUNTED): ");
                            String billType = scanner.nextLine();
                            double discount = 0.0;
                            if ("DISCOUNTED".equalsIgnoreCase(billType)) {
                                System.out.print("Enter Discount Percentage: ");
                                discount = Double.parseDouble(scanner.nextLine());
                            }

                            Bill bill = BillFactory.createBill(billingApp, billType, discount);

                            System.out.println("Select Billing Strategy:");
                            System.out.println("1. Standard Tax (18%)");
                            System.out.println("2. Senior Citizen (20% Discount + Tax)");
                            System.out.println("3. VIP (No Tax, $50 Handling)");
                            System.out.print("Choice: ");
                            String stratChoice = scanner.nextLine();
                            
                            if ("2".equals(stratChoice)) {
                                bill.setBillingStrategy(new com.airtribe.meditrack.strategy.SeniorCitizenBillingStrategy());
                            } else if ("3".equals(stratChoice)) {
                                bill.setBillingStrategy(new com.airtribe.meditrack.strategy.VIPBillingStrategy());
                            } else {
                                bill.setBillingStrategy(new com.airtribe.meditrack.strategy.StandardBillingStrategy());
                            }

                            BillSummary summary = bill.generateBill();
                            System.out.println("\nGenerated Bill:");
                            System.out.println(summary.toString());
                        } else {
                            System.out.println("Appointment not found.");
                        }
                        break;
                    case "6":
                        System.out.print("Enter search query (name, id, specialization): ");
                        String query = scanner.nextLine();
                        List<Doctor> foundDocs = doctorService.searchDoctors(query);
                        System.out.println("Found " + foundDocs.size() + " doctors.");
                        foundDocs.forEach(Doctor::displayDetails);
                        break;
                    case "7":
                        appointmentService.printAnalytics();
                        System.out.println("Average Consultation Fee: $" + doctorService.getAverageConsultationFee());
                        break;
                    case "8":
                        saveData();
                        System.out.println("Exiting System. Goodbye!");
                        return;
                    case "9":
                        System.out.print("Please describe your symptoms: ");
                        String symptoms = scanner.nextLine();
                        Specialization recommended = AIHelper.recommendSpecialization(symptoms);
                        System.out.println("\n[AI Recommendation] Based on your symptoms, we recommend: " + recommended);
                        List<Doctor> recommendedDoctors = doctorService.getDoctorsBySpecialization(recommended);
                        if (recommendedDoctors.isEmpty()) {
                            System.out.println("Currently, there are no doctors available for " + recommended);
                        } else {
                            System.out.println("Here are the available doctors for your condition:");
                            recommendedDoctors.forEach(Doctor::displayDetails);
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (AppointmentNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
