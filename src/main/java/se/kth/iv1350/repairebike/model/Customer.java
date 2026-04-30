package se.kth.iv1350.repairebike.model;

import se.kth.iv1350.repairebike.dto.CustomerData;

/**
 * Represents a customer and the customer's electric bike.
 */
public class Customer {
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String bikeBrand;
    private final String bikeModel;
    private final String serialNumber;

    /**
     * Creates a customer.
     *
     * @param name Customer name.
     * @param phoneNumber Customer phone number.
     * @param email Customer email.
     * @param bikeBrand Bike brand.
     * @param bikeModel Bike model.
     * @param serialNumber Bike serial number.
     */
    public Customer(String name, String phoneNumber, String email,
                    String bikeBrand, String bikeModel, String serialNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.serialNumber = serialNumber;
    }

    /**
     * @return Customer phone number.
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * @return A data transfer object for this customer.
     */
    public CustomerData toCustomerData() {
        return new CustomerData(name, phoneNumber, email, bikeBrand, bikeModel, serialNumber);
    }
}
