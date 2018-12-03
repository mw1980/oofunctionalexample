package org.mrr.functional.pure;

import org.mrr.base.Distance;

import java.util.function.Function;
import java.util.function.UnaryOperator;

class VehicleActions {

    static final Function<Distance, UnaryOperator<Vehicle>> MOVE_FORWARD = distance -> vehicle -> vehicle.moveForward(distance);
    static final Function<Distance, UnaryOperator<Vehicle>> MOVE_LEFT = distance -> vehicle -> vehicle.moveLeft(distance);
    static final Function<Distance, UnaryOperator<Vehicle>> MOVE_RIGHT = distance -> vehicle -> vehicle.moveRight(distance);
    static final UnaryOperator<Vehicle> START = Vehicle::wakeUp;
    static final UnaryOperator<Vehicle> STOP = Vehicle::stop;
    static final Function<UnaryOperator<Vehicle>, UnaryOperator<Vehicle>> RIDE = f -> vehicle -> STOP.apply(f.apply(START.apply(vehicle)));
}
