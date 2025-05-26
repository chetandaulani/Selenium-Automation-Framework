package com.chetandaulani.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerIdManager {
    private static final List<String> availableCustomerIds = Collections.synchronizedList(new ArrayList<>());
    private static final List<String> inUseCustomerIds = Collections.synchronizedList(new ArrayList<>());
    private static final Map<Long, String> threadCustomerMap = new ConcurrentHashMap<>();  // Map to track thread-ID -> customer-ID relationship

    static {
        availableCustomerIds.add("CUST01");
        availableCustomerIds.add("CUST02");
        availableCustomerIds.add("CUST03");
        availableCustomerIds.add("CUST04");
    }

    public synchronized static String getCustomerId() {
        if (!availableCustomerIds.isEmpty()) {
            String customerId = availableCustomerIds.remove(0);
            inUseCustomerIds.add(customerId);
            threadCustomerMap.put(Thread.currentThread().getId(), customerId); // Map thread to customer ID
            return customerId;
        } else {
            return null;
        }
    }

    public synchronized static void releaseCustomerId() {
        long threadId = Thread.currentThread().getId();  
        String customerId = threadCustomerMap.remove(threadId);  

        if (customerId != null) {
            inUseCustomerIds.remove(customerId);  
            availableCustomerIds.add(customerId); 
            System.out.println("Released Customer ID: " + customerId + " from Thread ID: " + threadId);
        } else {
            System.out.println("No Customer ID to release for Thread ID: " + threadId);
        }
    }
}
