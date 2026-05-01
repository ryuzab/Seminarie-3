package se.kth.iv1350.repairElectricBike.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairElectricBike.model.Bike;
import se.kth.iv1350.repairElectricBike.model.Customer;
import se.kth.iv1350.repairElectricBike.model.RepairOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests RepairOrderRegistry.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private Customer customer;
    private RepairOrder order;

    /**
     * Sets up a fresh registry, customer, and order before each test.
     */
    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        customer = new Customer("Anna", "070", "a@b.se", new Bike("A", "B", "C"));
        order = new RepairOrder(1, customer, customer.getBike(), "Problem");
    }

    /**
     * Cleans up the environment after each test.
     */
    @AfterEach
    public void tearDown() {
        registry = null;
        customer = null;
        order = null;
    }

    /**
     * Tests finding a stored repair order by id.
     */
    @Test
    public void testFindOrder() {
        registry.addOrder(order);
        assertEquals(order, registry.findOrder(1), "Order not found.");
    }

    /**
     * Tests that an unknown id returns null.
     */
    @Test
    public void testFindMissingOrderReturnsNull() {
        assertNull(registry.findOrder(999), "Unknown id should return null.");
    }
}