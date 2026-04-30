package CD.integration;

import CD.Integration.RepairOrderRegistry;
import CD.Model.Bike;
import CD.Model.Customer;
import CD.Model.RepairOrder;
import org.junit.jupiter.api.Test;

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
        assertEquals(order, registry.findOrder(7));
    }

    /**
     * Tests that an unknown id returns null.
     */
    @Test
    public void testFindMissingOrderReturnsNull() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        assertNull(registry.findOrder(999));
    }
}
