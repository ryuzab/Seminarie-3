package se.kth.iv1350.repairElectricBike.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairElectricBike.dto.CustomerData;
import se.kth.iv1350.repairElectricBike.dto.SummaryDTO;
import se.kth.iv1350.repairElectricBike.integration.CustomerRegistry;
import se.kth.iv1350.repairElectricBike.integration.Date;
import se.kth.iv1350.repairElectricBike.integration.Printer;
import se.kth.iv1350.repairElectricBike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairElectricBike.model.RepairOrderState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests Controller.
 */
public class ControllerTest {
    private Controller controller;
    private RepairOrderRegistry repairOrderRegistry;
    private CustomerRegistry customerRegistry;
    private Date date;
    private Printer printer;

    /**
     * Sets up a fresh environment before each test runs.
     */
    @BeforeEach
    public void setUp() {
        repairOrderRegistry = new RepairOrderRegistry();
        customerRegistry = new CustomerRegistry();
        date = new Date();
        printer = new Printer();
        controller = new Controller(repairOrderRegistry, customerRegistry, date, printer);
    }

    /**
     * Cleans up the environment after each test runs.
     */
    @AfterEach
    public void tearDown() {
        repairOrderRegistry = null;
        customerRegistry = null;
        date = null;
        printer = null;
        controller = null;
    }


    /**
     * Tests the information returned after finding a customer.
     * First method assertNotNull checks its emptiness,
     * Second method assertEquals checks the customers name.
     */
    @Test
    public void testGetCustomerReturnsCustomerData() {
        CustomerData customerData = controller.getCustomer("0707654321");
        assertNotNull(customerData, "Customer data should not be null.");
        assertEquals("Charlie Kirk", customerData.getName(), "Incorrect customer fetched.");
    }


    /**
     *  Tests GetRepairSummary's information. 
     * First method assertNotNull checks its emptiness, 
     * Second method assertEquals checks OrderID,
     * Third method assertEquals checks its amount of tasks.
     */
    @Test
    public void testGetRepairSummary() {
        int orderId = controller.startRepairOrder("0707654321", "Problem");
        controller.findRepairOrder(orderId);
        controller.addDiagnostic("Diagnostic");
        controller.addTasks(new String[] {"Task one", "Task two"});
        
        SummaryDTO summary = controller.getRepairSummary();
        assertNotNull(summary, "Summary should not be null.");
        assertEquals(orderId, summary.getOrderId(), "Order id not correct.");
        assertEquals(2, summary.getTasks().size(), "Number of tasks not correct.");
    }

    /**
     * Tests that rejecting a repair correctly changes its state to REJECTED.
     */
    @Test
    public void testRejectRepair() {
        int orderId = controller.startRepairOrder("0701234567", "Broken chain");
        controller.findRepairOrder(orderId);
        controller.rejectRepair();
        
        assertEquals(RepairOrderState.REJECTED, repairOrderRegistry.findOrder(orderId).getState(), "Repair order was not successfully rejected.");
    }
}