package se.kth.iv1350.repairebike.dto;

/**
 * Data transfer object containing customer and bike data shown outside the model.
 */
public class CustomerData {
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String bikeBrand;
    private final String bikeModel;
    private final String serialNumber;

    /**
     * Creates a customer data object.
     *
     * @param name Customer name.
     * @param phoneNumber Customer phone number.
     * @param email Customer email address.
     * @param bikeBrand Bike brand.
     * @param bikeModel Bike model.
     * @param serialNumber Bike serial number.
     */
    public CustomerData(String name, String phoneNumber, String email,
                        String bikeBrand, String bikeModel, String serialNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.serialNumber = serialNumber;
    }

    /**
     * @return Customer name.
     */
    public String getName() { return name; }

    /**
     * @return Customer phone number.
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * @return Customer email address.
     */
    public String getEmail() { return email; }

    /**
     * @return Bike brand.
     */
    public String getBikeBrand() { return bikeBrand; }

    /**
     * @return Bike model.
     */
    public String getBikeModel() { return bikeModel; }

    /**
     * @return Bike serial number.
     */
    public String getSerialNumber() { return serialNumber; }

    /**
     * @return Printable customer and bike information.
     */
    @Override
    public String toString() {
        return name + ", " + phoneNumber + ", " + email + ", " + bikeBrand + " " + bikeModel
                + ", serial: " + serialNumber;
    }
}
