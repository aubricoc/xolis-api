package cat.aubricoc.xolis.common.utils;

import cat.aubricoc.xolis.common.model.SearchMetadata;
import cat.aubricoc.xolis.common.model.SearchResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;

import javax.annotation.Nonnull;
import java.util.List;

public class HttpUtils {

    private static final String HEADER_TOTAL = "X-Total-Count";

    private HttpUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static String getEndpoint(@Nonnull HttpRequest<?> request) {
        return request.getMethodName() + " " + request.getPath();
    }

    public static <T> HttpResponse<List<T>> buildResponse(@Nonnull SearchResult<T> result) {
        SearchMetadata metadata = result.getMetadata();
        if (metadata == null) {
            throw new IllegalArgumentException("metadata cannot be null");
        }
        return HttpResponse.ok(result.getData())
                .header(HEADER_TOTAL, metadata.getTotal().toString());
    }
}
