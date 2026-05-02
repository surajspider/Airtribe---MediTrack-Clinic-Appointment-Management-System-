package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.Specialization;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
    private CSVUtil() {}

    public static void saveDoctorsToCSV(List<Doctor> doctors, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Name,Age,ContactNumber,Specialization,ConsultationFee\n");
            for (Doctor d : doctors) {
                writer.write(String.format("%s,%s,%d,%s,%s,%.2f\n",
                        d.getId(), d.getName(), d.getAge(), d.getContactNumber(),
                        d.getSpecialization().name(), d.getConsultationFee()));
            }
            System.out.println("Doctors saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving doctors to CSV: " + e.getMessage());
        }
    }

    public static List<Doctor> loadDoctorsFromCSV(String filePath) {
        List<Doctor> doctors = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return doctors;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Doctor d = new Doctor(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            parts[3],
                            Specialization.valueOf(parts[4]),
                            Double.parseDouble(parts[5])
                    );
                    doctors.add(d);
                }
            }
            System.out.println("Doctors loaded from " + filePath);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading doctors from CSV: " + e.getMessage());
        }
        return doctors;
    }

    // Generic serialization method for any object (Demonstrating File I/O & Persistence Bonus)
    public static void serializeData(Object data, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error serializing data: " + e.getMessage());
        }
    }

    public static Object deserializeData(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) return null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing data: " + e.getMessage());
        }
        return null;
    }
}
