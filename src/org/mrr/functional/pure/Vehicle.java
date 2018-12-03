package org.mrr.functional.pure;

import org.mrr.base.Coordinate;
import org.mrr.base.Distance;

public interface Vehicle {

    Vehicle wakeUp();

    Vehicle moveForward(Distance distance);

    Vehicle moveLeft(Distance distance);

    Vehicle moveRight(Distance distance);

    Vehicle stop();

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
        public Vehicle wakeUp() {
            System.out.println("vehicle is waking up.");
            return this;
        }

        @Override
        public Vehicle moveForward(Distance distance) {
            System.out.println("moving forward " + distance);
            return new Default(this.currentPosition().plus(new Coordinate(distance.value(), distance.value())));
        }

        @Override
        public Vehicle moveLeft(Distance distance) {
            System.out.println("moving left " + distance);
            return new Default(this.currentPosition().plus(new Coordinate(distance.value(), 0)));
        }

        @Override
        public Vehicle moveRight(Distance distance) {
            System.out.println("moving right " + distance);
            return new Default(this.currentPosition().plus(new Coordinate(0, distance.value())));
        }

        @Override
        public Vehicle stop() {
            System.out.println("stopping the vehicle");
            return this;
        }

        @Override
        public Coordinate currentPosition() {
            return coordinate;
        }
    }
}
