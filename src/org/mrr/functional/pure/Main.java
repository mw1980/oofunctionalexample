package org.mrr.functional.pure;

import org.mrr.base.Distance;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.mrr.functional.pure.VehicleActions.MOVE_FORWARD;
import static org.mrr.functional.pure.VehicleActions.MOVE_LEFT;
import static org.mrr.functional.pure.VehicleActions.MOVE_RIGHT;
import static org.mrr.functional.pure.VehicleActions.RIDE;
import static org.mrr.functional.pure.VehicleActions.START;
import static org.mrr.functional.pure.VehicleActions.STOP;

public class Main {

    public static void main(final String[] args) {
        Vehicle vehicle = new Vehicle.Default();
        final UnaryOperator<Vehicle> moveLeft3 = MOVE_LEFT.apply(Distance.THREE_METER);
        final UnaryOperator<Vehicle> moveRight10 = MOVE_RIGHT.apply(Distance.TEN_METER);
        final UnaryOperator<Vehicle> moveForward13 = MOVE_FORWARD.apply(Distance.inch(13));

        System.out.println(
                START
                        .andThen(moveLeft3)
                        .andThen(moveRight10)
                        .andThen(moveForward13)
                        .andThen(STOP)
                        .apply(vehicle).currentPosition()
        );

        System.out.println(".... Second approach: .......");

        final UnaryOperator<Vehicle> allMoves = Stream.of(moveLeft3, moveRight10, moveForward13)
                .reduce(
                        UnaryOperator.identity(),
                        (first, second) -> v -> second.apply(first.apply(v))
                );

        System.out.println(
                RIDE.apply(allMoves).apply(vehicle).currentPosition()
        );

        System.out.println("...........");
    }

}
