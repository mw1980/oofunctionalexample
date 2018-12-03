package org.mrr.oo;

/**
 * The class abstracts an action that can be performed on a moon vehicle.
 */
public abstract class AbstractVehicleCommand {

    private final Vehicle vehicle;

    AbstractVehicleCommand(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract void execute();

    Vehicle vehicle() {
        return this.vehicle;
    }
}
