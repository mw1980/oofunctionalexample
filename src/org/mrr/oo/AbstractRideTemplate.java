package org.mrr.oo;

abstract class AbstractRideTemplate {

    abstract void mainRide();

    abstract Vehicle vehicle();

    void startRide(){
        beforeRide();
        mainRide();
        afterRide();
    }

    private void beforeRide() {
        new StartCommand(vehicle()).execute();
    }

    private void afterRide() {
        new StopCommand(vehicle()).execute();
    }
}
