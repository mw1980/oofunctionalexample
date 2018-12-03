package org.mrr.functional.effects.basic;

import org.mrr.base.Coordinate;
import org.mrr.base.Distance;
import org.mrr.functional.toolkit.Try;

public interface Vehicle {

    Try<Vehicle> wakeUp();

    Try<Vehicle> moveForward(Distance distance);

    Try<Vehicle> moveLeft(Distance distance);

    Try<Vehicle> moveRight(Distance distance);

    Try<Vehicle> stop();

    Coordinate currentPosition();

    class Default implements Vehicle {
        final Coordinate coordinate;

        Default(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        Default() {
            this(Coordinate.UNKNOWN);
        }

        @Override
        public Try<Vehicle> wakeUp() {
            System.out.println("vehicle is waking up.");
            return Try.of(() -> this);
        }

        @Override
        public Try<Vehicle> moveForward(Distance distance) {
            System.out.println("moving forward " + distance);
            return Try.of(() -> new Default(this.currentPosition().plus(new Coordinate(distance.value(), distance.value()))));
        }

        @Override
        public Try<Vehicle> moveLeft(Distance distance) {
            System.out.println("moving left " + distance);
            return Try.of(() -> new Default(this.currentPosition().plus(new Coordinate(distance.value(), 0))));
        }

        @Override
        public Try<Vehicle> moveRight(Distance distance) {
            System.out.println("moving right " + distance);
            return Try.of(() -> new Default(this.currentPosition().plus(new Coordinate(0, distance.value()))));
        }

        @Override
        public Try<Vehicle> stop() {
            System.out.println("stopping the vehicle");
            return Try.of(()-> this);
        }

        @Override
        public Coordinate currentPosition() {
            return coordinate;
        }
    }

    class UnableToMoveLeft implements Vehicle {
        final Coordinate coordinate;

        UnableToMoveLeft(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        UnableToMoveLeft() {
            this(Coordinate.UNKNOWN);
        }

        @Override
        public Try<Vehicle> wakeUp() {
            System.out.println("vehicle is waking up.");
            return Try.of(() -> this);
        }

        @Override
        public Try<Vehicle> moveForward(Distance distance) {
            System.out.println("moving forward " + distance);
            return Try.of(() -> new UnableToMoveLeft(this.currentPosition().plus(new Coordinate(distance.value(), distance.value()))));
        }

        @Override
        public Try<Vehicle> moveLeft(Distance distance) {
            throw new RuntimeException("unable to move left");
        }

        @Override
        public Try<Vehicle> moveRight(Distance distance) {
            System.out.println("moving right " + distance);
            return Try.of(() -> new UnableToMoveLeft(this.currentPosition().plus(new Coordinate(0, distance.value()))));
        }

        @Override
        public Try<Vehicle> stop() {
            System.out.println("stopping the vehicle");
            return Try.of(()-> this);
        }

        @Override
        public Coordinate currentPosition() {
            return coordinate;
        }
    }
}
