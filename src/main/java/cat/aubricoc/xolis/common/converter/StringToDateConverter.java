package cat.aubricoc.xolis.common.converter;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Singleton
public class StringToDateConverter implements TypeConverter<String, Date> {

    @Override
    public Optional<Date> convert(String value, Class<Date> targetType, ConversionContext context) {
        return Optional.ofNullable(value)
                .map(ZonedDateTime::parse)
                .map(zdt -> {
                    Date date = new Date();
                    date.setTime(zdt.toInstant().toEpochMilli());
                    return date;
                });
    }
}
