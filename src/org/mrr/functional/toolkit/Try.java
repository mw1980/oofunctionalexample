package org.mrr.functional.toolkit;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public interface Try<T> {

    T getValue();

    Try<T> map(UnaryOperator<T> f);

    Try<T> flatMap(Function<T, Try<T>> f);

    boolean isFailure();

    Exception getCause();

    default boolean isSuccess() {
        return !isFailure();
    }

    static <T> Try<T> of(Supplier<T> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Exception exception) {
            return new Failure<>(exception);
        }
    }

    class Success<T> implements Try<T> {

        private T value;

        public Success(final T value) {
            this.value = value;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public Exception getCause() {
            return null;
        }

        @Override
        public Try<T> map(UnaryOperator<T> f) {
            return Try.of(() -> f.apply(getValue()));
        }

        @Override
        public Try<T> flatMap(Function<T, Try<T>> f) {
            return Try.of(() -> f.apply(getValue()).getValue());
        }
    }

    class Failure<T> implements Try<T> {

        private final Exception exception;

        public Failure(final Exception exception) {
            this.exception = exception;
        }

        @Override
        public T getValue() {
            return null;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public Exception getCause() {
            return this.exception;
        }

        @Override
        public Try<T> map(UnaryOperator<T> f) {
            //nothing to do, previous operation failed to execute.
            return this;
        }

        @Override
        public Try<T> flatMap(Function<T, Try<T>> f) {
            //nothing to do, previous operation failed to execute.
            return this;
        }
    }
}
