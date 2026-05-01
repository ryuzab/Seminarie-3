package se.kth.iv1350.repairElectricBike.integration;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests RepairOrderRegistry.
 */
public class RepairOrderRegistryTest {
    /**
     * Tests finding a stored repair order by id.
     */
    @Test
    public void testFindOrder() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        Customer customer = new Customer("Anna", "070", "a@b.se", new Bike("A", "B", "C"));
        RepairOrder order = new RepairOrder(1, customer, customer.getBike(), "Problem");
        registry.addOrder(order);
        assertEquals(order, registry.findOrder(1), "Order not found.");
    }

    /**
     * Tests that an unknown id returns null.
     */
    @Test
    public void testFindMissingOrderReturnsNull() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        assertNull(registry.findOrder(999), "Unknown id should return null.");
    }
}
