package cat.aubricoc.xolis.common.utils;

import cat.aubricoc.xolis.common.model.SearchResult;
import io.micronaut.core.convert.ConversionService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConversionUtils {

    private ConversionUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static <T> T convert(@Nullable Object from, @Nonnull Class<T> toClass) {
        return ConversionService.SHARED.convertRequired(from, toClass);
    }

    public static <T> SearchResult<T> convertSearchResult(@Nullable SearchResult<?> from, Class<T> toClass) {
        if (from == null) {
            return null;
        }
        SearchResult<T> result = new SearchResult<>();
        result.setMetadata(from.getMetadata());
        result.setData(convertListItems(from.getData(), toClass));
        return result;
    }

    private static <T> List<T> convertListItems(@Nullable List<?> from, @Nonnull Class<T> toClass) {
        if (from == null) {
            return Collections.emptyList();
        }
        return from.stream()
                .map(item -> convert(item, toClass))
                .collect(Collectors.toList());
    }
}
