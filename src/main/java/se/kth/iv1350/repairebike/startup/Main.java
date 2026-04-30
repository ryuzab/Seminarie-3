
package se.kth.iv1350.repairebike.startup;
import se.kth.iv1350.repairebike.controller.Controller;
import se.kth.iv1350.repairebike.integration.CustomerRegistry;
import se.kth.iv1350.repairebike.integration.Printer;
import se.kth.iv1350.repairebike.integration.RepairOrderRegistry;
import se.kth.iv1350.repairebike.view.View;

/**
 * Starts the Repair Electric Bike application.
 */
public class Main {
    /**
     * Creates only system-wide objects and starts the example flow.
     *
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        Controller controller = new Controller(customerRegistry, repairOrderRegistry, printer);
        View view = new View(controller);
        view.runExampleFlow();
    }
}
