package se.kth.iv1350.repairElectricBike.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderState;
import se.kth.iv1350.repairElectricBike.model.RepairTask;

/**
 * The application's only controller. Handles calls from the view and coordinates model and integration objects.
 */
public class Controller {
    private final RepairOrderRegistry repairOrderRegistry;
    private final CustomerRegistry customerRegistry;
    private final Date date;
    private final Printer printer;
    private RepairOrder currentRepairOrder;

    /**
     * Creates a controller.
     *
     * @param repairOrderRegistry Registry for repair orders.
     * @param customerRegistry Registry for customers.
     * @param date Date helper.
     * @param printer Printer integration object.
     */
    public Controller(RepairOrderRegistry repairOrderRegistry, CustomerRegistry customerRegistry,
                      Date date, Printer printer) {
        this.repairOrderRegistry = repairOrderRegistry;
        this.customerRegistry = customerRegistry;
        this.date = date;
        this.printer = printer;
    }

    /**
     * Gets customer data by phone number.
     *
     * @param phoneNumber Customer phone number.
     * @return Customer data, or null if no customer was found.
     */
    public CustomerData getCustomer(String phoneNumber) {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return null;
        }
        Bike bike = customer.getBike();
        return new CustomerData(customer.getName(), customer.getPhoneNumber(), customer.getEmailAddress(),
                bike.getBrand(), bike.getModel(), bike.getSerialNumber());
    }


    /** 
     * Starts a new repair order for a customer.
     *
     * @param phoneNumber Customer phone number.
     * @param description Customer's problem description.
     * @return Created repair order id, or -1 if the customer was not found.
     */
    public int startRepairOrder(String phoneNumber, String description) {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return -1;
        }
        
        int newId = repairOrderRegistry.generateNextOrderId();
        
        // The description is required to create the object
        currentRepairOrder = new RepairOrder(newId, customer, customer.getBike(), description);
        repairOrderRegistry.addOrder(currentRepairOrder);
        
        return currentRepairOrder.getId();
    }

    /**
     * Finds one specific repair order by id and makes it the current repair order.
     *
     * @param orderId Repair order id.
     * @return true if the repair order was found, otherwise false.
     */
    public boolean findRepairOrder(int orderId) {
        RepairOrder foundOrder = repairOrderRegistry.findOrder(orderId);
        if (foundOrder == null) {
            return false;
        }
        currentRepairOrder = foundOrder;
        return true;
    }

    /**
     * Adds a technician diagnostic report to the current repair order.
     *
     * @param report Diagnostic report.
     */
    public void addDiagnostic(String report) {
        if (currentRepairOrder != null) {
            currentRepairOrder.addDiagnostic(report);
            repairOrderRegistry.updateOrder(currentRepairOrder);
        }
    }

    /**
     * Adds repair tasks to the current repair order and stores the updated order in the registry.
     *
     * @param taskDescriptions Task descriptions.
     */
    public void addTasks(String[] taskDescriptions) {
        if (currentRepairOrder == null) {
            return;
        }
        List<RepairTask> tasks = new ArrayList<>();
        
        float currentTaskCost = RepairTask.BASE_COST; 
        
        for (String description : taskDescriptions) {
            tasks.add(new RepairTask(description, currentTaskCost));
            currentTaskCost += RepairTask.ADDITIONAL_COST; 
        }
        currentRepairOrder.addTasks(tasks);
        repairOrderRegistry.updateOrder(currentRepairOrder);
    }

    /**
     * Gets a summary for the current repair order.
     *
     * @return Repair summary, or null if there is no current repair order.
     */
    public SummaryDTO getRepairSummary() {
        if (currentRepairOrder == null) {
            return null;
        }
        List<String> taskTexts = new ArrayList<>();
        for (RepairTask task : currentRepairOrder.getTasks()) {
            taskTexts.add(task.toString());
        }
        return new SummaryDTO(currentRepairOrder.getId(), currentRepairOrder.getProblemDescription(),
                currentRepairOrder.getDiagnosticReport(), taskTexts, currentRepairOrder.calculateTotalCost());
    }

    /**
     * Accepts the current repair order and prints it.
     */
    public void acceptRepair() {
        if (currentRepairOrder != null) {
            currentRepairOrder.changeState(RepairOrderState.ACCEPTED);
            repairOrderRegistry.updateOrder(currentRepairOrder);
            printer.print(currentRepairOrder);
        }
    }

    /**
     * Rejects the current repair order.
     */
    public void rejectRepair() {
        if (currentRepairOrder != null) {
            currentRepairOrder.changeState(RepairOrderState.REJECTED);
            repairOrderRegistry.updateOrder(currentRepairOrder);
        }
    }

    /**
     * Gets the date used by this controller.
     *
     * @return Today's date.
     */
    public String getCurrentDate() {
        return date.getDate();
    }
}
