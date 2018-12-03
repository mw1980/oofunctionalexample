package org.mrr.base;

public class Coordinate {

    public static final Coordinate UNKNOWN = new Coordinate(0 ,0 );
    private final int latitude;
    private final int longitude;

    public Coordinate(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int latitude(){
        return latitude;
    }

    public int longitude() {
        return longitude;
    }

    public Coordinate plus(Coordinate coordinate) {
        return new Coordinate(latitude + coordinate.latitude(), longitude + coordinate.longitude());
    }

    @Override
    public String toString() {
        return String.format("Coordinate [latitude = %s, longitude = %s]", latitude, longitude);
    }
}
