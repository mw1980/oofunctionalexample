package org.mrr.oo;

import org.mrr.base.Distance;

public class MoveLeftCommand extends AbstractVehicleCommand {

    private final Distance distance;

    MoveLeftCommand(final Vehicle vehicle, final Distance distance) {
        super(vehicle);
        this.distance = distance;
    }

    @Override
    public void execute() {
        vehicle().moveLeft(distance);
    }
}
