package cat.aubricoc.xolis.common.exception;

import cat.aubricoc.xolis.common.utils.HttpUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class ExceptionManager implements ExceptionHandler<ClientErrorException, HttpResponse<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionManager.class);

    @Override
    public HttpResponse<?> handle(HttpRequest request, ClientErrorException exception) {
        HttpStatus httpStatus = exception.getHttpStatus();
        String endpoint = HttpUtils.getEndpoint(request);
        LOG.warn("Client Error ({}) on request {} : {}", httpStatus, endpoint, exception.getMessage());
        return HttpResponse.status(httpStatus).body(exception.toResponse());
    }
}
