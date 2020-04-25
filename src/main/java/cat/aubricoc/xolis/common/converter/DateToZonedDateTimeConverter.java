package cat.aubricoc.xolis.common.converter;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Singleton
public class DateToZonedDateTimeConverter implements TypeConverter<Date, ZonedDateTime> {

    @Override
    public Optional<ZonedDateTime> convert(Date source, Class<ZonedDateTime> targetType, ConversionContext context) {
        if (source == null) {
            return Optional.empty();
        }
        return Optional.of(ZonedDateTime.ofInstant(Instant.ofEpochMilli(source.getTime()), ZoneOffset.UTC));
    }
}
