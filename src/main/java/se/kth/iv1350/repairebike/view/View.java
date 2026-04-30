package se.kth.iv1350.repairebike.view;

import java.util.List;

import se.kth.iv1350.repairebike.controller.Controller;
import se.kth.iv1350.repairebike.dto.CustomerData;
import se.kth.iv1350.repairebike.dto.SummaryDTO;

/**
 * Replaces the user interface with hard-coded calls to the controller.
 */
public class View {
    private final Controller controller;

    /**
     * Creates a view.
     *
     * @param controller Controller used by this view.
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Runs a hard-coded example flow and prints everything returned by the controller.
     */
    public void runExampleFlow() {
        String phoneNumber = "0701234567";

        CustomerData customerData = controller.getCustomer(phoneNumber);
        System.out.println("Customer found:");
        System.out.println(customerData);

        int orderId = controller.startRepairOrder(phoneNumber);
        System.out.println("Created repair order id: " + orderId);

        controller.addProblemDescription("Battery loses power after a short ride.");
        controller.addDiagnostic("Battery cells are worn and rear brake needs adjustment.");
        controller.addTasks(List.of("Replace battery", "Adjust rear brake"));

        SummaryDTO summary = controller.getRepairSummary();
        System.out.println("\nRepair summary:");
        System.out.println(summary);

        SummaryDTO foundOrder = controller.findRepairOrder(orderId);
        System.out.println("\nFound stored repair order:");
        System.out.println(foundOrder);

        controller.acceptRepair();
    }
}
