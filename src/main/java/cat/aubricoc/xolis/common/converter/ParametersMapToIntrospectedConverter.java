package cat.aubricoc.xolis.common.converter;

import cat.aubricoc.xolis.common.model.Introspected;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanIntrospector;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class ParametersMapToIntrospectedConverter implements TypeConverter<Map<String, List<String>>, Introspected> {

    @Override
    public Optional<Introspected> convert(Map<String, List<String>> map, Class<Introspected> targetType, ConversionContext context) {
        BeanIntrospection<Introspected> introspection = BeanIntrospector.SHARED.getIntrospection(targetType);
        Introspected object = introspection.instantiate();
        map.forEach((name, list) -> introspection.getProperty(name).ifPresent(property -> {
            Class<Object> type = property.getType();
            if (Collection.class.isAssignableFrom(type)) {
                property.convertAndSet(object, list);
            } else {
                property.convertAndSet(object, list.get(0));
            }
        }));
        return Optional.of(object);
    }
}
