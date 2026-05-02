package com.airtribe.meditrack.strategy;

/**
 * Strategy interface for calculating the final billing amount.
 */
public interface BillingStrategy {
    /**
     * Calculates the final total amount including any taxes or discounts.
     * 
     * @param baseAmount The base consultation fee.
     * @param taxRate The standard tax rate percentage (e.g., 0.18 for 18%).
     * @return The final total amount.
     */
    double calculateTotal(double baseAmount, double taxRate);
    
    /**
     * Calculates the tax amount specifically for reporting.
     * 
     * @param baseAmount The base consultation fee.
     * @param taxRate The standard tax rate percentage.
     * @return The calculated tax amount.
     */
    double calculateTax(double baseAmount, double taxRate);
}
