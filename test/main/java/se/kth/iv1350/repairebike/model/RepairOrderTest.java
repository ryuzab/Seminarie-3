package se.kth.iv1350.repairebike.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests RepairOrder.
 */
public class RepairOrderTest {
    /**
     * Tests that total cost is calculated from the number of tasks.
     */
    @Test
    public void testCalculateTotalCost() {
        Customer customer = new Customer("Test", "123", "test@example.com", "Brand", "Model", "Serial");
        RepairOrder order = new RepairOrder(1, customer);
        order.addTasks(List.of("Task one", "Task two"));
        assertEquals(1000, order.calculateTotalCost(), "Two tasks should cost 1000 SEK.");
    }

    /**
     * Tests that accepting a repair order changes its state.
     */
    @Test
    public void testAcceptChangesState() {
        Customer customer = new Customer("Test", "123", "test@example.com", "Brand", "Model", "Serial");
        RepairOrder order = new RepairOrder(1, customer);
        order.accept();
        assertEquals(RepairOrderState.ACCEPTED, order.getState(), "Order should be accepted.");
    }
}
