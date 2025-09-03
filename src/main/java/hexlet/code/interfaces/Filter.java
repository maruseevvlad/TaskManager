package hexlet.code.interfaces;

@FunctionalInterface
public interface Filter<T> {
    boolean apply(T value);
}
