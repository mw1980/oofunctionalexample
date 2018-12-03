package org.mrr.oo;

public class StartCommand extends AbstractVehicleCommand {

    StartCommand(final Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void execute() {
        vehicle().wakeUp();
    }
}
