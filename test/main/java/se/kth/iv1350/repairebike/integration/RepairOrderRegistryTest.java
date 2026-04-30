package se.kth.iv1350.repairebike.integration;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairebike.model.Customer;
import se.kth.iv1350.repairebike.model.RepairOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Tests RepairOrderRegistry.
 */
public class RepairOrderRegistryTest {
    /**
     * Tests that a specific repair order can be found by id.
     */
    @Test
    public void testFindRepairOrderById() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        Customer customer = new Customer("Test", "123", "test@example.com", "Brand", "Model", "Serial");
        RepairOrder order = new RepairOrder(10, customer);
        registry.addOrder(order);
        assertSame(order, registry.findRepairOrder(10), "The same order should be found by id.");
    }

    /**
     * Tests that several repair orders can belong to the same customer.
     */
    @Test
    public void testFindRepairOrdersForSameCustomer() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        Customer customer = new Customer("Test", "123", "test@example.com", "Brand", "Model", "Serial");
        registry.addOrder(new RepairOrder(1, customer));
        registry.addOrder(new RepairOrder(2, customer));
        assertEquals(2, registry.findRepairOrders("123").size(), "Customer should have two repair orders.");
    }
}
