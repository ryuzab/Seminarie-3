package CD.controller;

import CD.Controller.Controller;
import CD.DTO.CustomerData;
import CD.DTO.SummaryDTO;
import CD.Integration.CustomerRegistry;
import CD.Integration.Date;
import CD.Integration.Printer;
import CD.Integration.RepairOrderRegistry;
import org.junit.jupiter.api.Test;

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
        Controller controller = new Controller(new RepairOrderRegistry(), new CustomerRegistry(), new Date(), new Printer());
        CustomerData customerData = controller.getCustomer("0701234567");
        assertNotNull(customerData);
        assertEquals("Anna Andersson", customerData.getName());
    }

    /**
     * Tests that a summary is created after diagnostic and tasks have been added.
     */
    @Test
    public void testGetRepairSummary() {
        Controller controller = new Controller(new RepairOrderRegistry(), new CustomerRegistry(), new Date(), new Printer());
        
        int orderId = controller.startRepairOrder("0701234567", "Problem");
        
        controller.findRepairOrder(orderId);
        controller.addDiagnostic("Diagnostic");
        controller.addTasks(new String[] {"Task one", "Task two"});
        
        SummaryDTO summary = controller.getRepairSummary();
        assertNotNull(summary);
        assertEquals(orderId, summary.getOrderId());
        assertEquals(2, summary.getTasks().size());
    }
}
