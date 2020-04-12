package cat.aubricoc.xolis.common.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class ExceptionManager implements ExceptionHandler<ClientErrorException, HttpResponse<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionManager.class);

    @Override
    public HttpResponse<?> handle(HttpRequest request, ClientErrorException exception) {
        LOG.warn("Error on request: " + request.getMethodName() + " " + request.getPath(), exception);
        return HttpResponse.status(exception.getHttpStatus(), exception.getMessage());
    }
}
