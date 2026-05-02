package com.airtribe.meditrack.constants;

public class Constants {
    // Application-wide configs
    public static final double TAX_RATE = 0.18; // 18% tax
    public static final String DATA_FILE_PATH = "data/";

    // Static block to demonstrate static initialization as per requirements
    static {
        System.out.println("[INFO] System Constants Loaded. Tax Rate: " + (TAX_RATE * 100) + "%");
    }

    private Constants() {
        // Prevent instantiation
    }
}
