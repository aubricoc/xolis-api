package cat.aubricoc.xolis.common.exception;

import io.micronaut.http.HttpStatus;

public class ConflictException extends ClientErrorException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
