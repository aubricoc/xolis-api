package cat.aubricoc.xolis.common.utils;

import io.micronaut.http.HttpRequest;

public class HttpUtils {

    private HttpUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static String getEndpoint(HttpRequest<?> request) {
        return request.getMethodName() + " " + request.getPath();
    }
}
