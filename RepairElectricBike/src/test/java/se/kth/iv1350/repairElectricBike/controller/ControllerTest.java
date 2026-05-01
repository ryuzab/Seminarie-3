package se.kth.iv1350.repairElectricBike.controller;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.repairElectricBike.controller.Controller;
import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests Controller.
 */
public class ControllerTest {
    /**
     * Tests that customer data can be fetched without exposing Customer.
     */
    @Test
    public void testGetCustomerReturnsCustomerData() {
        Controller controller = new Controller (new RepairOrderRegistry(), new CustomerRegistry(), new Date(), new Printer());
        CustomerData customerData = controller.getCustomer("0707654321");
        assertNotNull(customerData);
        assertEquals("Charlie Kirk", customerData.getName());
    }

    /**
     * Tests that a summary is created after diagnostic and tasks have been added.
     */
    @Test
    public void testGetRepairSummary() {
        Controller controller = new Controller(new RepairOrderRegistry(), new CustomerRegistry(), new Date(), new Printer());
        
        int orderId = controller.startRepairOrder("0707654321", "Problem");
        
        controller.findRepairOrder(orderId);
        controller.addDiagnostic("Diagnostic");
        controller.addTasks(new String[] {"Task one", "Task two"});
        
        SummaryDTO summary = controller.getRepairSummary();
        assertNotNull(summary, "Summary should not be null.");
        assertEquals(orderId, summary.getOrderId(), "Order id not correct.");
        assertEquals(2, summary.getTasks().size(), "Number of tasks not correct.");
    }
}
