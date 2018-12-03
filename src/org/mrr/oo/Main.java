package org.mrr.oo;

import org.mrr.base.Distance;

public class Main {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle.Default();

        new VehicleRide(
                new MoveForwardCommand(vehicle, Distance.ONE_METER),
                new MoveLeftCommand(vehicle, Distance.inch(2)),
                new MoveRightCommand(vehicle, Distance.THREE_METER)
        ).startRide();
    }
}
