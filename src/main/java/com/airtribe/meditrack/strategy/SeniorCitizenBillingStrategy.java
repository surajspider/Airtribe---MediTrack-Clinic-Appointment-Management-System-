package com.airtribe.meditrack.strategy;

public class SeniorCitizenBillingStrategy implements BillingStrategy {
    private static final double SENIOR_DISCOUNT = 0.20; // 20% discount

    @Override
    public double calculateTotal(double baseAmount, double taxRate) {
        double discountedBase = baseAmount - (baseAmount * SENIOR_DISCOUNT);
        return discountedBase + calculateTax(discountedBase, taxRate);
    }

    @Override
    public double calculateTax(double discountedBase, double taxRate) {
        return discountedBase * taxRate;
    }
}
