package CD.startup;

import CD.Controller.Controller;
import CD.Integration.CustomerRegistry;
import CD.Integration.Date;
import CD.Integration.Printer;
import CD.Integration.RepairOrderRegistry;
import CD.View.View;

/*
 * Starts the Repair Electric Bike application.
 */
public class Main {
    /**
     * Starts the application.
     *
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        CustomerRegistry customerRegistry = new CustomerRegistry();
        Date date = new Date();
        Printer printer = new Printer();
        Controller controller = new Controller(repairOrderRegistry, customerRegistry, date, printer);
        View view = new View(controller);
        view.runBasicFlow();
    }
}
