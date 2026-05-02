package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public class Validator {
    private Validator() {} // Prevent instantiation

    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidDataException("Invalid age provided: " + age);
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be null or empty.");
        }
    }

    public static void validateContact(String contact) {
        if (contact == null || !contact.matches("\\d{10}")) {
            throw new InvalidDataException("Contact number must be exactly 10 digits.");
        }
    }
}
