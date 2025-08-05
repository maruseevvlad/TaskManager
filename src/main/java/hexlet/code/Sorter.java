package hexlet.code;

import java.util.Comparator;

@FunctionalInterface
public interface Sorter<T> {
    Comparator<T> getComparator();
}
