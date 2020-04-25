package cat.aubricoc.xolis.common.enums;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Optional;
import java.util.stream.Stream;

public enum FilterOperator {

    EQUALS("eq"),
    NOT_EQUALS("ne"),
    GREATER_THAN("gt"),
    GREATER_OR_EQUALS("ge", "gte"),
    LESS_THAN("lt"),
    LESS_OR_EQUALS("le", "lte");

    private final String[] keys;

    FilterOperator(String... keys) {
        this.keys = keys;
    }

    public static Optional<FilterOperator> getByKey(String key) {
        return Stream.of(values())
                .filter(operator -> ArrayUtils.contains(operator.keys, key))
                .findFirst();
    }
}
