package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.DiscountedBill;
import com.airtribe.meditrack.entity.StandardBill;

/**
 * Factory pattern for creating different types of bills.
 */
public class BillFactory {
    private BillFactory() {}

    public static Bill createBill(Appointment appointment, String type, double discountPercentage) {
        String billId = IdGenerator.getInstance().generateBillId();
        
        if ("DISCOUNTED".equalsIgnoreCase(type)) {
            return new DiscountedBill(billId, appointment, discountPercentage);
        } else {
            return new StandardBill(billId, appointment);
        }
    }
}
