package hexlet.code;

@FunctionalInterface
public interface Filter<T> {
    boolean apply(T value);
}
