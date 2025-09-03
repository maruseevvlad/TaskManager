package hexlet.code.interfaces;

import java.util.Comparator;

@FunctionalInterface
public interface Sorter<T> {
    Comparator<T> getComparator();
}
