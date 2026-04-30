package se.kth.iv1350.repairebike.model;

/**
 * Possible states for a repair order.
 */
public enum RepairOrderState {
    /** The repair order has been created. */
    CREATED,
    /** The customer has accepted the repair. */
    ACCEPTED,
    /** The customer has rejected the repair. */
    REJECTED
}
