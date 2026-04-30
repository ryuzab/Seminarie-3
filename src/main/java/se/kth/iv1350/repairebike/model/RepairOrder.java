package se.kth.iv1350.repairebike.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairebike.dto.SummaryDTO;

/**
 * Represents a repair order for one electric bike repair.
 */
public class RepairOrder {
    private final int orderId;
    private final Customer customer;
    private String problemDescription = "";
    private String diagnosticReport = "";
    private final List<String> tasks = new ArrayList<>();
    private RepairOrderState state = RepairOrderState.CREATED;

    /**
     * Creates a repair order.
     *
     * @param orderId Unique order id.
     * @param customer Customer who owns the bike.
     */
    public RepairOrder(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    /**
     * @return Repair order id.
     */
    public int getOrderId() { return orderId; }

    /**
     * @return The customer belonging to this order.
     */
    public Customer getCustomer() { return customer; }

    /**
     * Adds a problem description to the order.
     *
     * @param problemDescription The customer's description of the problem.
     */
    public void addProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    /**
     * Adds the technician's diagnostic report.
     *
     * @param diagnosticReport The diagnostic report.
     */
    public void addDiagnostic(String diagnosticReport) {
        this.diagnosticReport = diagnosticReport;
    }

    /**
     * Adds repair tasks to the order.
     *
     * @param newTasks Tasks needed for the repair.
     */
    public void addTasks(List<String> newTasks) {
        tasks.addAll(newTasks);
    }

    /**
     * Accepts the repair order.
     */
    public void accept() {
        state = RepairOrderState.ACCEPTED;
    }

    /**
     * Rejects the repair order.
     */
    public void reject() {
        state = RepairOrderState.REJECTED;
    }

    /**
     * @return Current state of the repair order.
     */
    public RepairOrderState getState() { return state; }

    /**
     * @return Total estimated cost.
     */
    public int calculateTotalCost() {
        return tasks.size() * 500;
    }

    /**
     * @return Summary data for this repair order.
     */
    public SummaryDTO createSummary() {
        return new SummaryDTO(orderId, problemDescription, diagnosticReport, tasks, calculateTotalCost());
    }
}
