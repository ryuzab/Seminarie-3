package se.kth.iv1350.repairElectricBike.model;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;
import se.kth.iv1350.repairElectricBike.model.RepairOrderState;
import se.kth.iv1350.repairElectricBike.model.RepairTask;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests RepairOrder.
 */
public class RepairOrderTest {
    /**
     * Tests that total cost is calculated from all tasks.
     */
    @Test
    public void testCalculateTotalCost() {
        Customer customer = new Customer("Tung", "070", "a@b.se", new Bike("A", "B", "C"));
        RepairOrder order = new RepairOrder(1, customer, customer.getBike(), "Problem");
        order.addTasks(List.of(new RepairTask("Task 1", 100), new RepairTask("Task 2", 200)));
        assertEquals(300, order.calculateTotalCost(), "calculateTotalCost gave incorrect answer.");
    }

    /**
     * Tests that adding tasks makes the order ready for approval.
     */
    @Test
    public void testAddTasksChangesState() {
        Customer customer = new Customer("Tung", "070", "a@b.se", new Bike("A", "B", "C"));
        RepairOrder order = new RepairOrder(1, customer, customer.getBike(), "Problem");
        order.addTasks(List.of(new RepairTask("Task", 100)));
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, order.getState(), "States not properly changed.");
    }
}
