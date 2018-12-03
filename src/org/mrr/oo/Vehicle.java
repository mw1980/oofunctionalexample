package org.mrr.oo;

import org.mrr.base.Coordinate;
import org.mrr.base.Distance;
import org.mrr.base.VehicleException;

import java.util.Random;

public interface Vehicle {

    void wakeUp();
    void moveForward(Distance distance);
    void moveLeft(Distance distance);
    void moveRight(Distance distance);
    void stop();
    Coordinate currentPosition();

    class Default implements Vehicle {

        public Default(){
            System.out.println("new default vehicle is created");
        }

        @Override
        public void wakeUp() {
            System.out.println("vehicle is waking up");
        }

        @Override
        public void moveForward(Distance distance) {
            System.out.println(String.format("moving vehicle forward %s %s", distance.value(), distance.unit()));
        }

        @Override
        public void moveLeft(Distance distance) {
            System.out.println(String.format("moving vehicle left %s %s", distance.value(), distance.unit()));
        }

        @Override
        public void moveRight(Distance distance) {
            System.out.println(String.format("moving vehicle right %s %s", distance.value(), distance.unit()));
        }

        @Override
        public void stop() {
             System.out.println("vehicle is going to sleep");
        }

        @Override
        public Coordinate currentPosition() {
            return new Coordinate(123, 456);
        }
    }

    class UnableToMove implements Vehicle {
        @Override
        public void wakeUp() {
            System.out.println("vehicle is waking up");
        }

        @Override
        public void moveForward(Distance distance) {
            throw new VehicleException("unable to move forward.");
        }

        @Override
        public void moveLeft(Distance distance) {
            throw new VehicleException("unable to move left.");
        }

        @Override
        public void moveRight(Distance distance) {
            //throw new VehicleException("unable to move right.");
            System.out.println("Moving right with " + distance.value());
        }

        @Override
        public void stop() {
            System.out.println("vehicle is stopped");
        }

        @Override
        public Coordinate currentPosition() {
            Random random = new Random();
            return new Coordinate(random.nextInt(1200), random.nextInt(1500));
        }
    }

    class Unstoppable implements Vehicle {

        @Override
        public void wakeUp() {
            System.out.println("Vehicle is waking up.");
        }

        @Override
        public void moveForward(Distance distance) {
            System.out.printf("Moving forward %s \n", distance);
        }

        @Override
        public void moveLeft(Distance distance) {
            System.out.printf("Moving left %s \n", distance);
        }

        @Override
        public void moveRight(Distance distance) {
            System.out.printf("Moving right %s \n", distance);
        }

        @Override
        public void stop() {
            System.out.println("Trying to stop the vehicle");
            throw new VehicleException("Unable to stop");
        }

        @Override
        public Coordinate currentPosition() {
            return new Coordinate(new Random().nextInt(1200), new Random().nextInt(1500));
        }
    }
}
