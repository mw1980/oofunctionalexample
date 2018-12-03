package org.mrr.functional.effects;

import org.mrr.functional.toolkit.Either;
import org.mrr.functional.toolkit.LazyTry;
import org.mrr.functional.toolkit.TryWithError;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.mrr.base.Distance.FIVE_METER;
import static org.mrr.base.Distance.SEVEN_INCH;
import static org.mrr.base.Distance.THREE_METER;

public class Main {

    public static void main(String[] args) {

        System.out.println("........ Simple try ..............");

        Vehicle moonVehicle = new Vehicle.Default();

        final Vehicle firstResult = LazyTry.to(Vehicle::wakeUp)
                .andThen(v -> v.moveLeft(FIVE_METER))
                .andThen(v -> v.moveForward(THREE_METER))
                .andThen(v -> v.moveRight(SEVEN_INCH))
                .andThen(Vehicle::stop)
                .onError(exception -> System.out.printf("Simple try example: exception caught: %s  \n", exception.getMessage()))
                .execute(moonVehicle);

        System.out.println(firstResult.currentPosition());

        System.out.println("........ Try with error ..............");

        final Either<RuntimeException, Vehicle> vehicleMoveResult = TryWithError.to(Vehicle::wakeUp)
                .andThen(v -> v.moveLeft(FIVE_METER))
                .andThen(v -> v.moveForward(THREE_METER))
                .andThen(v -> v.moveRight(SEVEN_INCH))
                .andThen(Vehicle::stop)
                .execute(moonVehicle);
        System.out.printf("The result is: %s \n", vehicleMoveResult);

        System.out.println("....... Folding ...............");

        final BinaryOperator<LazyTry<Vehicle>> lazyTryComposition = (first, second) -> new LazyTry<>(vehicle -> second.execute(first.execute(vehicle)));
        final Stream<LazyTry<Vehicle>> allMoves = Stream.of(
                new LazyTry<Vehicle>(v -> v.moveLeft(FIVE_METER)),
                new LazyTry<Vehicle>(v -> v.moveForward(THREE_METER)),
                new LazyTry<Vehicle>(v -> v.moveRight(SEVEN_INCH))
        );

        final Vehicle result = allMoves.reduce(new LazyTry<>(UnaryOperator.identity()), lazyTryComposition)
                .execute(moonVehicle);
        System.out.println(result.currentPosition());
    }
}
