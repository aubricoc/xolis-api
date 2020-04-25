package cat.aubricoc.xolis.common.converter;

import cat.aubricoc.xolis.common.enums.FilterOperator;
import cat.aubricoc.xolis.common.model.FilterWithOperator;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.core.convert.TypeConverter;
import io.micronaut.core.type.Argument;
import org.apache.commons.lang3.StringUtils;

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
        String[] split = StringUtils.split(value, OPERATOR_SEPARATOR, 2);
        if (split.length == 2) {
            Optional<FilterOperator> operator = FilterOperator.getByKey(split[0]);
            if (operator.isPresent()) {
                Object val = conversionService.convertRequired(split[1], valueType);
                return Optional.of(new FilterWithOperator<>(operator.get(), val));
            }
        }
        Object val = conversionService.convertRequired(value, valueType);
        return Optional.of(new FilterWithOperator<>(val));
    }
}
