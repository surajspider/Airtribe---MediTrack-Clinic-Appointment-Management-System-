package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.CSVUtil;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Running Manual Tests...\n");

        testCloning();
        testCSVUtility();

        System.out.println("\nAll tests completed.");
    }

    private static void testCloning() {
        System.out.println("--- Test: Deep Copy Cloning ---");
        Patient original = new Patient("P1", "John Doe", 30, "1234567890", "None");
        original.addMedication("Paracetamol");

        Patient clone = original.clone();
        clone.addMedication("Ibuprofen");

        System.out.println("Original Medications: " + original.getCurrentMedications());
        System.out.println("Clone Medications: " + clone.getCurrentMedications());
        
        if (!original.getCurrentMedications().contains("Ibuprofen")) {
            System.out.println("SUCCESS: Deep cloning verified.");
        } else {
            System.err.println("FAILURE: Shallow copy detected!");
        }
    }

    private static void testCSVUtility() {
        System.out.println("\n--- Test: File I/O CSV ---");
        // We will just do a basic test using Serialization since we used it.
        List<String> sampleData = new ArrayList<>();
        sampleData.add("Test1");
        sampleData.add("Test2");

        String path = "test_io.ser";
        CSVUtil.serializeData(sampleData, path);
        
        @SuppressWarnings("unchecked")
        List<String> loaded = (List<String>) CSVUtil.deserializeData(path);
        if (loaded != null && loaded.size() == 2 && loaded.get(0).equals("Test1")) {
            System.out.println("SUCCESS: File I/O Serialization verified.");
        } else {
            System.err.println("FAILURE: Serialization/Deserialization failed.");
        }

        // Cleanup
        new java.io.File(path).delete();
    }
}
