package cat.aubricoc.xolis.common.utils;

import cat.aubricoc.xolis.common.model.SearchResult;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ConversionUtils {

    private ConversionUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static <T> T convert(@Nullable Object from, @Nonnull Class<T> toClass) {
        if (from == null) {
            return null;
        }
        if (toClass.isInstance(from)) {
            return toClass.cast(from);
        }
        if (toClass == ZonedDateTime.class && from instanceof Date) {
            return toClass.cast(convertToZonedDateTime((Date) from));
        }
        try {
            T to = toClass.getDeclaredConstructor().newInstance();
            fillAllFields(from, to);
            return to;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Cannot convert object", e);
        }
    }

    public static <T> List<T> convert(@Nullable List<?> from, @Nonnull Class<T> toClass) {
        if (from == null) {
            return Collections.emptyList();
        }
        return from.stream()
                .map(item -> convert(item, toClass))
                .collect(Collectors.toList());
    }

    public static <T> SearchResult<T> convert(@Nullable SearchResult<?> from, Class<T> toClass) {
        if (from == null) {
            return null;
        }
        SearchResult<T> result = new SearchResult<>();
        result.setMetadata(from.getMetadata());
        result.setData(convert(from.getData(), toClass));
        return result;
    }

    private static ZonedDateTime convertToZonedDateTime(@Nullable Date from) {
        if (from == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(from.getTime()), ZoneOffset.UTC);
    }

    private static void fillAllFields(@Nonnull Object from, @Nonnull Object to) throws IllegalAccessException {
        Class<?> fromClass = from.getClass();
        Class<?> toClass = to.getClass();
        for (Field fieldTo : FieldUtils.getAllFieldsList(toClass)) {
            Field fieldFrom = FieldUtils.getField(fromClass, fieldTo.getName(), true);
            fieldTo.setAccessible(true);
            Object valueFrom = fieldFrom.get(from);
            Object valueTo = ConversionUtils.convert(valueFrom, fieldTo.getType());
            fieldTo.set(to, valueTo);
        }
    }
}
