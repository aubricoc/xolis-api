package cat.aubricoc.xolis.common.utils;

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getClass(T source) {
        return (Class<T>) source.getClass();
    }
}
