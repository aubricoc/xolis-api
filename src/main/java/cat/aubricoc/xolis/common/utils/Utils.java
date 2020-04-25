package cat.aubricoc.xolis.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T source) {
        return (Class<T>) source.getClass();
    }

    @SafeVarargs
    public static <T> List<T> mergeLists(List<T>... lists) {
        return Stream.of(lists)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static <T> T cast(Object object, Class<T> type) {
        return type.cast(object);
    }
}
