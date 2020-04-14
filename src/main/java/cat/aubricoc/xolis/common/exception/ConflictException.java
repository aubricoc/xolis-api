package cat.aubricoc.xolis.common.exception;

import io.micronaut.http.HttpStatus;

import java.util.Map;

public class ConflictException extends ClientErrorException {

    private final String field;

    public ConflictException(String message) {
        this(null, message);
    }

    public ConflictException(String field, String message) {
        super(HttpStatus.CONFLICT, message);
        this.field = field;
    }

    @Override
    public Map<String, Object> toResponse() {
        Map<String, Object> response = super.toResponse();
        if (field != null) {
            response.put("field", field);
        }
        return response;
    }
}
