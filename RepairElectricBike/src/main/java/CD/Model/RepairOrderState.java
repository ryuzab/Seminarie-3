package CD.Model;

/**
 * Possible states for a repair order.
 */
public enum RepairOrderState {
    NEWLY_CREATED,
    READY_FOR_APPROVAL,
    REJECTED,
    ACCEPTED,
    COMPLETED,
    PAID
}
