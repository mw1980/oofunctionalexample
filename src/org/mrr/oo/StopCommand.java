package org.mrr.oo;

public class StopCommand extends AbstractVehicleCommand {

    StopCommand(final Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void execute() {
        vehicle().stop();
    }
}
