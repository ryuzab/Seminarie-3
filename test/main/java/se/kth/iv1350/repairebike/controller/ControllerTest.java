package se.kth.iv1350.repairebike.controller;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairebike.dto.SummaryDTO;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests Controller.
 */
public class ControllerTest {
    /**
     * Tests that tasks are stored and included in the summary.
     */
    @Test
    public void testAddTasksIncludedInSummary() {
        Controller controller = new Controller(new CustomerRegistry(), new RepairOrderRegistry(), new Printer());
        controller.startRepairOrder("0701234567");
        controller.addProblemDescription("Problem");
        controller.addDiagnostic("Diagnostic");
        controller.addTasks(List.of("Task one", "Task two"));
        SummaryDTO summary = controller.getRepairSummary();
        assertEquals(2, summary.getTasks().size(), "Summary should contain two tasks.");
        assertEquals(1000, summary.getTotalCost(), "Two tasks should cost 1000 SEK.");
    }

    /**
     * Tests that an order can be found after tasks have been added.
     */
    @Test
    public void testOrderStoredAfterTasks() {
        Controller controller = new Controller(new CustomerRegistry(), new RepairOrderRegistry(), new Printer());
        int orderId = controller.startRepairOrder("0701234567");
        controller.addTasks(List.of("Task one"));
        assertNotNull(controller.findRepairOrder(orderId), "Order should be stored after tasks are added.");
    }
}
