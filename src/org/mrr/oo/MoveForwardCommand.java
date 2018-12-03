package org.mrr.oo;

import org.mrr.base.Distance;

public class MoveForwardCommand extends AbstractVehicleCommand {

    private final Distance distance;

    MoveForwardCommand(final Vehicle vehicle, final Distance distance) {
        super(vehicle);
        this.distance = distance;
    }

    @Override
    public void execute() {
        vehicle().moveForward(distance);
    }
}
