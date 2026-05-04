package se.kth.iv1350.repairElectricBike.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests RepairOrder.
 */
public class RepairOrderTest {
    private Customer customer;
    private RepairOrder order;

    /**
     * Sets up a fresh Customer and RepairOrder before each test.
     */
    @BeforeEach
    public void setUp() {
        customer = new Customer("Tung", "070", "a@b.se", new Bike("A", "B", "C"));
        order = new RepairOrder(1, customer, customer.getBike(), "Problem");
    }

    /**
     * Cleans up the environment after each test.
     */
    @AfterEach
    public void tearDown() {
        customer = null;
        order = null;
    }

    /**
     * Tests that total cost is calculated from all tasks.
     */
    @Test
    public void testCalculateTotalCost() {
        order.addTasks(List.of(new RepairTask("Task 1", 300), new RepairTask("Task 2", 300)));
        assertEquals(300, order.calculateTotalCost(), "calculateTotalCost gave incorrect answer.");
    }

    /**
     * Tests that adding tasks makes the order ready for approval.
     */
    @Test
    public void testAddTasksChangesState() {
        order.addTasks(List.of(new RepairTask("Task", 300)));
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, order.getState(), "States not properly changed.");
    }
}