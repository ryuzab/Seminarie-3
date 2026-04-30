package se.kth.iv1350.repairebike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairebike.model.Customer;

/**
 * Stores customers instead of using a database.
 */
public class CustomerRegistry {
    private final List<Customer> customers = new ArrayList<>();

    /**
     * Creates a registry and adds example customers.
     */
    public CustomerRegistry() {
        customers.add(new Customer("Amina Ali", "0701234567", "amina@example.com",
                "Crescent", "Elina", "EB-1001"));
        customers.add(new Customer("Oskar Berg", "0707654321", "oskar@example.com",
                "Ecoride", "Ambassador", "EB-1002"));
    }

    /**
     * Finds a customer by phone number.
     *
     * @param phoneNumber Phone number to search for.
     * @return Found customer, or null if no customer was found.
     */
    public Customer findCustomerPhone(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return null;
    }
}
