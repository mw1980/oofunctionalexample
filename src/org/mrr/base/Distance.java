package org.mrr.base;

public class Distance {

    public static final Distance ONE_METER = Distance.meter(1);
    public static final Distance THREE_METER = Distance.meter(3);
    public static final Distance FIVE_METER = Distance.meter(5);
    public static final Distance SEVEN_INCH = Distance.inch(7);
    public static final Distance TEN_METER = Distance.meter(10);

    private final int value;
    private final Unit unit;

    private Distance(final int value, final Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public static Distance meter(final int value) {
        return new Distance(value, Unit.METER);
    }

    public static Distance inch(final int value) {
        return new Distance(value, Unit.INCH);
    }

    public int value(){
        return value;
    }

    public String unit(){
        return unit.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("the distance of %s %s", value, unit);
    }

    private enum Unit { METER, INCH}
}
