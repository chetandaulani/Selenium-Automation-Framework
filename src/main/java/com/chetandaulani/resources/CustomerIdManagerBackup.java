package com.chetandaulani.resources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerIdManagerBackup {
    private static final List<String> availableCustomerIds = Collections.synchronizedList(new ArrayList<>());
    private static final List<String> inUseCustomerIds = Collections.synchronizedList(new ArrayList<>());

    static {
        availableCustomerIds.add("CUST01");
        availableCustomerIds.add("CUST02");
        availableCustomerIds.add("CUST03");
        availableCustomerIds.add("CUST04");
    }

    // Fetch and lock a customer ID
    public synchronized static String getCustomerId() {
        if (!availableCustomerIds.isEmpty()) {
            String customerId = availableCustomerIds.remove(0);
            inUseCustomerIds.add(customerId);
            return customerId;
        } else {
            return null;
        }
    }

    // Release and return the customer ID
    public synchronized static void releaseCustomerId(String customerId) {
        if (inUseCustomerIds.remove(customerId)) {
            availableCustomerIds.add(customerId);
            System.out.println("Released Customer ID: " + customerId);
        }
    }
}
