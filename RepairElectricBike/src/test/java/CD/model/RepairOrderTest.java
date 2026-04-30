package CD.model;

import CD.Model.Bike;
import CD.Model.Customer;
import CD.Model.RepairOrder;
import CD.Model.RepairOrderState;
import CD.Model.RepairTask;
import org.junit.jupiter.api.Test;

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
        assertEquals(300, order.calculateTotalCost());
    }

    /**
     * Tests that adding tasks makes the order ready for approval.
     */
    @Test
    public void testAddTasksChangesState() {
        Customer customer = new Customer("Tung", "070", "a@b.se", new Bike("A", "B", "C"));
        RepairOrder order = new RepairOrder(1, customer, customer.getBike(), "Problem");
        order.addTasks(List.of(new RepairTask("Task", 100)));
        assertEquals(RepairOrderState.READY_FOR_APPROVAL, order.getState());
    }
}
