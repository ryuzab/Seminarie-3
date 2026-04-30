package se.kth.iv1350.repairebike.controller;

import java.util.List;

import se.kth.iv1350.repairebike.dto.CustomerData;
import se.kth.iv1350.repairebike.dto.SummaryDTO;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairebike.model.Customer;
import se.kth.iv1350.repairebike.model.RepairOrder;

/**
 * Handles all calls from the view and coordinates the model and integration layers.
 */
public class Controller {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final Printer printer;
    private RepairOrder currentRepairOrder;

    /**
     * Creates a controller.
     *
     * @param customerRegistry Registry for customers.
     * @param repairOrderRegistry Registry for repair orders.
     * @param printer Printer used for repair order printouts.
     */
    public Controller(CustomerRegistry customerRegistry, RepairOrderRegistry repairOrderRegistry, Printer printer) {
        this.customerRegistry = customerRegistry;
        this.repairOrderRegistry = repairOrderRegistry;
        this.printer = printer;
    }

    /**
     * Gets customer data for a phone number.
     *
     * @param phoneNumber Phone number to search for.
     * @return Customer data, or null if no customer was found.
     */
    public CustomerData getCustomer(String phoneNumber) {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return null;
        }
        return customer.toCustomerData();
    }

    /**
     * Starts a new repair order for a customer.
     *
     * @param phoneNumber Customer phone number.
     * @return The created repair order id, or -1 if no customer was found.
     */
    public int startRepairOrder(String phoneNumber) {
        Customer customer = customerRegistry.findCustomerPhone(phoneNumber);
        if (customer == null) {
            return -1;
        }
        currentRepairOrder = new RepairOrder(repairOrderRegistry.createNewOrderId(), customer);
        return currentRepairOrder.getOrderId();
    }

    /**
     * Adds a problem description to the current repair order.
     *
     * @param description Problem description.
     */
    public void addProblemDescription(String description) {
        currentRepairOrder.addProblemDescription(description);
    }

    /**
     * Adds a diagnostic report to the current repair order.
     *
     * @param report Diagnostic report.
     */
    public void addDiagnostic(String report) {
        currentRepairOrder.addDiagnostic(report);
    }

    /**
     * Adds tasks and stores the order in the registry when the technician has entered tasks.
     *
     * @param tasks Repair tasks.
     */
    public void addTasks(List<String> tasks) {
        currentRepairOrder.addTasks(tasks);
        repairOrderRegistry.updateRepairOrder(currentRepairOrder);
    }

    /**
     * Gets summary data for the current repair order.
     *
     * @return Summary data.
     */
    public SummaryDTO getRepairSummary() {
        return currentRepairOrder.createSummary();
    }

    /**
     * Finds a specific repair order.
     *
     * @param orderId Repair order id.
     * @return Summary data for the order, or null if no order was found.
     */
    public SummaryDTO findRepairOrder(int orderId) {
        RepairOrder order = repairOrderRegistry.findRepairOrder(orderId);
        if (order == null) {
            return null;
        }
        currentRepairOrder = order;
        return order.createSummary();
    }

    /**
     * Accepts the current repair order and prints it.
     */
    public void acceptRepair() {
        currentRepairOrder.accept();
        repairOrderRegistry.updateRepairOrder(currentRepairOrder);
        printer.printRepairOrder(currentRepairOrder);
    }

    /**
     * Rejects the current repair order and stores the rejected state.
     */
    public void rejectRepair() {
        currentRepairOrder.reject();
        repairOrderRegistry.updateRepairOrder(currentRepairOrder);
    }
}
