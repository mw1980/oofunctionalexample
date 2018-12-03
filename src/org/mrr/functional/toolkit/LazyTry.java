package org.mrr.functional.toolkit;


import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class LazyTry<T> {
    private final UnaryOperator<T> f;
    private final Consumer<Exception> exceptionHandler;

    private LazyTry(final UnaryOperator<T> f, final Consumer<Exception> exceptionHandler) {
        this.f = f;
        this.exceptionHandler = exceptionHandler;
    }

    public LazyTry(final UnaryOperator<T> f) {
        this(f, exception -> System.out.printf("Exception caught while trying to execute f: %s with parameter: %s; \n",
                f.getClass().getSimpleName(), exception)
        );
    }

    public static <T> LazyTry<T> to(UnaryOperator<T> f) {
        return new LazyTry<>(f);
    }

    public LazyTry<T> andThen(UnaryOperator<T> after) {
        return new LazyTry<>(t -> after.apply(f.apply(t)), exceptionHandler);
    }

    public LazyTry<T> onError(Consumer<Exception> exceptionHandler) {
        return new LazyTry<>(f, exceptionHandler);
    }

    public T execute(final T t) {
        try {
            return f.apply(t);
        } catch (RuntimeException exception) {
            exceptionHandler.accept(exception);
            return t;
        }
    }
}
