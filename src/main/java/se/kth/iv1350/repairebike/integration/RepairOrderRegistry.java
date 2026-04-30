package se.kth.iv1350.repairebike.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.repairebike.model.RepairOrder;

/**
 * Stores repair orders instead of using a database.
 */
public class RepairOrderRegistry {
    private final List<RepairOrder> orders = new ArrayList<>();
    private int nextOrderId = 1;

    /**
     * @return A new unique repair order id.
     */
    public int createNewOrderId() {
        return nextOrderId++;
    }

    /**
     * Adds an order to the registry.
     *
     * @param repairOrder Repair order to add.
     */
    public void addOrder(RepairOrder repairOrder) {
        if (findRepairOrder(repairOrder.getOrderId()) == null) {
            orders.add(repairOrder);
        }
    }

    /**
     * Updates a repair order already stored in the registry.
     *
     * @param repairOrder Repair order to update.
     */
    public void updateRepairOrder(RepairOrder repairOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId() == repairOrder.getOrderId()) {
                orders.set(i, repairOrder);
                return;
            }
        }
        orders.add(repairOrder);
    }

    /**
     * Finds a specific repair order.
     *
     * @param orderId Id of the repair order.
     * @return Found repair order, or null if no repair order was found.
     */
    public RepairOrder findRepairOrder(int orderId) {
        for (RepairOrder order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    /**
     * Finds all repair orders for a customer phone number.
     *
     * @param phoneNumber Customer phone number.
     * @return Matching repair orders.
     */
    public List<RepairOrder> findRepairOrders(String phoneNumber) {
        List<RepairOrder> foundOrders = new ArrayList<>();
        for (RepairOrder order : orders) {
            if (order.getCustomer().getPhoneNumber().equals(phoneNumber)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }
}
