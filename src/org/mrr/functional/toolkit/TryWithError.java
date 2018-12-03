package org.mrr.functional.toolkit;

import java.util.function.Function;

public class TryWithError<T, U> {

    private final Function<T, U> f;

    public TryWithError(Function<T, U> f) {
        this.f = f;
    }

    public static <T, U> TryWithError<T,U> to(Function<T, U> f) {
        return new TryWithError<>(f);
    }

    public <V> TryWithError<T, V> andThen(Function<U, V> g) {
        return new TryWithError<>(f.andThen(g));
    }

    public Either<RuntimeException, U> execute(T t) {
        try {
            return Either.withRightValue(f.apply(t));
        } catch (RuntimeException exception) {
            return Either.withLeftValue(new RuntimeException(t.toString(), exception));
        }
    }
}
