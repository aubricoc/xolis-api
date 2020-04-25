package cat.aubricoc.xolis.common.converter;

import cat.aubricoc.xolis.common.enums.FilterOperator;
import cat.aubricoc.xolis.common.model.FilterWithOperator;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.core.convert.TypeConverter;
import io.micronaut.core.type.Argument;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class StringToFilterWithOperatorConverter implements TypeConverter<String, FilterWithOperator<?>> {

    private static final String OPERATOR_SEPARATOR = ":";

    private final ConversionService<?> conversionService;

    @Inject
    public StringToFilterWithOperatorConverter(ConversionService<?> conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Optional<FilterWithOperator<?>> convert(String value, Class<FilterWithOperator<?>> targetType, ConversionContext context) {
        if (value == null) {
            return Optional.empty();
        }
        Class<?> valueType = context.getFirstTypeVariable()
                .map(Argument::getType)
                .orElseThrow();
        for (FilterOperator operator : FilterOperator.values()) {
            String operatorText = operator.getKey() + OPERATOR_SEPARATOR;
            if (value.startsWith(operatorText)) {
                String valueWithoutOperator = value.substring(operatorText.length());
                Object val = conversionService.convertRequired(valueWithoutOperator, valueType);
                return Optional.of(new FilterWithOperator<>(operator, val));
            }
        }
        Object val = conversionService.convertRequired(value, valueType);
        return Optional.of(new FilterWithOperator<>(val));
    }
}
