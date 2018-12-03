package org.mrr.functional.toolkit;

public class Either<T, V> {
    private final T left;
    private final V right;

    private Either(final T left, final V right) {
        this.left = left;
        this.right = right;
    }

    public static <T, V> Either<T, V> withLeftValue(final T value) {
        return new Either<>(value, null);
    }

    public static <T, V> Either<T, V> withRightValue(final V value) {
        return new Either<>(null, value);
    }

    public T left() {
        return left;
    }

    public V right() {
        return right;
    }

    @Override
    public String toString() {
        return String.format("[Either: left = %s and right = %s]", left(), right());
    }
}
