package cat.aubricoc.xolis.common.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class ConversionUtils {

    private ConversionUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static <T> T convert(@Nullable Object from, @Nonnull Class<T> toClass) {
        if (from == null) {
            return null;
        }
        try {
            T to = toClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(to, from);
            return to;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Cannot convert object", e);
        }
    }
}
