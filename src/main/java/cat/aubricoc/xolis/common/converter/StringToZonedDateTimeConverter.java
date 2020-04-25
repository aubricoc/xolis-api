package cat.aubricoc.xolis.common.converter;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.time.ZonedDateTime;
import java.util.Optional;

@Singleton
public class StringToZonedDateTimeConverter implements TypeConverter<String, ZonedDateTime> {

    @Override
    public Optional<ZonedDateTime> convert(String value, Class<ZonedDateTime> targetType, ConversionContext context) {
        return Optional.ofNullable(value)
                .map(ZonedDateTime::parse);
    }
}
