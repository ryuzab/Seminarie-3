package se.kth.iv1350.repairebike.integration;

import se.kth.iv1350.repairebike.model.RepairOrder;

/**
 * Represents the external printer system.
 */
public class Printer {
    /**
     * Prints a repair order to System.out.
     *
     * @param repairOrder Repair order to print.
     */
    public void printRepairOrder(RepairOrder repairOrder) {
        System.out.println("\n--- REPAIR ORDER PRINTOUT ---");
        System.out.println(repairOrder.createSummary());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("-----------------------------\n");
    }
}
