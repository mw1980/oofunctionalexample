package org.mrr.oo;

import java.util.Arrays;
import java.util.List;

class VehicleRide extends AbstractRideTemplate {

    private final List<AbstractVehicleCommand> commands;

    VehicleRide(final AbstractVehicleCommand... commands) { this.commands = Arrays.asList(commands); }

    @Override
    void mainRide() {
        try {
            this.commands.forEach(AbstractVehicleCommand::execute);
        } catch (final RuntimeException exception) {
            System.out.printf("Exception caught during vehicle ride: %s. Last known cart position is: %s \n",
                            exception.getMessage(), vehicle().currentPosition());
        }
    }

    @Override
    Vehicle vehicle() {
        return commands.get(0).vehicle();
    }
}
