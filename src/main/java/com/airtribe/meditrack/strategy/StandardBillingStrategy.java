package com.airtribe.meditrack.strategy;

public class StandardBillingStrategy implements BillingStrategy {
    @Override
    public double calculateTotal(double baseAmount, double taxRate) {
        return baseAmount + calculateTax(baseAmount, taxRate);
    }

    @Override
    public double calculateTax(double baseAmount, double taxRate) {
        return baseAmount * taxRate;
    }
}
