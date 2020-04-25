package cat.aubricoc.xolis.common.model;

import cat.aubricoc.xolis.common.enums.FilterOperator;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class FilterWithOperator<T> {

    private final FilterOperator operator;
    private final T value;

    public FilterWithOperator(T value) {
        this(null, value);
    }

    public FilterWithOperator(FilterOperator operator, T value) {
        this.operator = operator == null ? FilterOperator.EQUALS : operator;
        this.value = value;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
