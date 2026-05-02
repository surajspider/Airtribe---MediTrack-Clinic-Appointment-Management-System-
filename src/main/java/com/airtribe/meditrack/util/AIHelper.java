package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Specialization;

import java.util.HashMap;
import java.util.Map;

public class AIHelper {
    private static final Map<String, Specialization> SYMPTOM_MAP = new HashMap<>();

    static {
        // Keyword mappings for Rule-based AI Recommendation
        
        // Cardiology
        SYMPTOM_MAP.put("heart", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("chest", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("blood pressure", Specialization.CARDIOLOGY);
        SYMPTOM_MAP.put("bp", Specialization.CARDIOLOGY);
        
        // Dermatology
        SYMPTOM_MAP.put("skin", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("rash", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("acne", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("hair", Specialization.DERMATOLOGY);
        SYMPTOM_MAP.put("itch", Specialization.DERMATOLOGY);
        
        // Neurology
        SYMPTOM_MAP.put("headache", Specialization.NEUROLOGY);
        SYMPTOM_MAP.put("brain", Specialization.NEUROLOGY);
        SYMPTOM_MAP.put("migraine", Specialization.NEUROLOGY);
        SYMPTOM_MAP.put("dizzy", Specialization.NEUROLOGY);
        SYMPTOM_MAP.put("numb", Specialization.NEUROLOGY);
        
        // Orthopedics
        SYMPTOM_MAP.put("bone", Specialization.ORTHOPEDICS);
        SYMPTOM_MAP.put("joint", Specialization.ORTHOPEDICS);
        SYMPTOM_MAP.put("fracture", Specialization.ORTHOPEDICS);
        SYMPTOM_MAP.put("back pain", Specialization.ORTHOPEDICS);
        SYMPTOM_MAP.put("knee", Specialization.ORTHOPEDICS);
        SYMPTOM_MAP.put("muscle", Specialization.ORTHOPEDICS);
        
        // Pediatrics
        SYMPTOM_MAP.put("child", Specialization.PEDIATRICS);
        SYMPTOM_MAP.put("kid", Specialization.PEDIATRICS);
        SYMPTOM_MAP.put("baby", Specialization.PEDIATRICS);
        SYMPTOM_MAP.put("infant", Specialization.PEDIATRICS);
        
        // General Medicine
        SYMPTOM_MAP.put("cold", Specialization.GENERAL_MEDICINE);
        SYMPTOM_MAP.put("cough", Specialization.GENERAL_MEDICINE);
        SYMPTOM_MAP.put("fever", Specialization.GENERAL_MEDICINE);
        SYMPTOM_MAP.put("flu", Specialization.GENERAL_MEDICINE);
        SYMPTOM_MAP.put("weak", Specialization.GENERAL_MEDICINE);
        SYMPTOM_MAP.put("stomach", Specialization.GENERAL_MEDICINE);
    }

    private AIHelper() {}

    /**
     * Recommends a Specialization based on the provided symptoms string.
     * Uses basic rule-based keyword matching.
     * 
     * @param input User's described symptoms.
     * @return Recommended Specialization, defaults to GENERAL_MEDICINE.
     */
    public static Specialization recommendSpecialization(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Specialization.GENERAL_MEDICINE;
        }

        String lowerInput = input.toLowerCase();
        
        for (Map.Entry<String, Specialization> entry : SYMPTOM_MAP.entrySet()) {
            if (lowerInput.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        
        // Default recommendation if no specific symptom is recognized
        return Specialization.GENERAL_MEDICINE;
    }
}
