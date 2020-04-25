package cat.aubricoc.xolis.common.converter;

import cat.aubricoc.xolis.common.model.Introspected;
import cat.aubricoc.xolis.common.utils.Utils;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanIntrospector;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class IntrospectedToIntrospectedConverter implements TypeConverter<Introspected, Introspected> {

    @Override
    public Optional<Introspected> convert(Introspected source, Class<Introspected> targetType, ConversionContext context) {
        if (source == null) {
            return Optional.empty();
        }
        BeanIntrospector introspector = BeanIntrospector.SHARED;
        Class<Introspected> sourceType = Utils.getClass(source);
        BeanIntrospection<Introspected> sourceIntrospection = introspector.getIntrospection(sourceType);
        BeanIntrospection<Introspected> targetIntrospection = introspector.getIntrospection(targetType);
        Introspected target = targetIntrospection.instantiate();
        sourceIntrospection.getBeanProperties()
                .forEach(sourceProperty -> targetIntrospection.getProperty(sourceProperty.getName())
                        .ifPresent(targetProperty -> targetProperty.convertAndSet(target, sourceProperty.get(source))));
        return Optional.of(target);
    }
}
