package cat.aubricoc.xolis.common.exception;

import io.micronaut.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ClientErrorException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ClientErrorException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, Object> toResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", getMessage());
        return response;
    }
}
