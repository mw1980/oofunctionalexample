package org.mrr.functional.effects.basic;

import org.mrr.base.Distance;
import org.mrr.functional.toolkit.Try;

import java.util.function.Function;

public class VehicleActions {

    private static final Function<Distance, Function<Vehicle, Try<Vehicle>>> MOVE_FORWARD = distance -> vehicle -> vehicle.moveForward(distance);
    private static final Function<Distance, Function<Vehicle, Try<Vehicle>>> MOVE_LEFT = distance -> vehicle -> vehicle.moveLeft(distance);
    private static final Function<Distance, Function<Vehicle, Try<Vehicle>>> MOVE_RIGHT = distance -> vehicle -> vehicle.moveRight(distance);
    private static final Function<Vehicle, Try<Vehicle>> START = Vehicle::wakeUp;
    private static final Function<Vehicle, Try<Vehicle>> STOP = Vehicle::stop;

    public static void main(String... args) {
        final Try<Vehicle> firstTry = Try.<Vehicle>of(Vehicle.Default::new)
                .flatMap(START)
                .flatMap(MOVE_FORWARD.apply(Distance.THREE_METER))
                .flatMap(MOVE_LEFT.apply(Distance.FIVE_METER))
                .flatMap(MOVE_RIGHT.apply(Distance.TEN_METER))
                .flatMap(STOP);
        System.out.println("Vehicle successful moved: " + firstTry.isSuccess());

        System.out.println("....");

        final Try<Vehicle> secondTry = Try.<Vehicle>of(Vehicle.UnableToMoveLeft::new)
                .flatMap(START)
                .flatMap(MOVE_FORWARD.apply(Distance.THREE_METER))
                .flatMap(MOVE_LEFT.apply(Distance.FIVE_METER))
                .flatMap(MOVE_RIGHT.apply(Distance.TEN_METER))
                .flatMap(STOP);
        System.out.println("Vehicle successful moved: " + secondTry.isSuccess());
    }
}
