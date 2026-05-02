package com.airtribe.meditrack.strategy;

public class VIPBillingStrategy implements BillingStrategy {
    @Override
    public double calculateTotal(double baseAmount, double taxRate) {
        // VIPs pay no tax and a flat $50 handling fee instead
        return baseAmount + 50.0;
    }

    @Override
    public double calculateTax(double baseAmount, double taxRate) {
        // No tax for VIP
        return 0.0;
    }
}
