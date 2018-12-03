package org.mrr.oo;

import org.mrr.base.Distance;

public class MoveRightCommand extends AbstractVehicleCommand {

    private final Distance distance;

    MoveRightCommand(final Vehicle vehicle, final Distance distance) {
        super(vehicle);
        this.distance = distance;
    }

    @Override
    public void execute() {
        vehicle().moveRight(distance);
    }
}
