package cat.aubricoc.xolis;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.jackson.JacksonConfiguration;
import io.micronaut.jackson.ObjectMapperFactory;
import io.micronaut.runtime.Micronaut;

import javax.inject.Singleton;

public class XolisApplication {

    public static void main(String[] args) {
        Micronaut.run(XolisApplication.class);
    }

    @Factory
    @Replaces(ObjectMapperFactory.class)
    static class CustomObjectMapperFactory extends ObjectMapperFactory {

        @Override
        @Singleton
        @Replaces(ObjectMapper.class)
        public ObjectMapper objectMapper(@Nullable JacksonConfiguration jacksonConfiguration, @Nullable JsonFactory jsonFactory) {
            return super.objectMapper(jacksonConfiguration, jsonFactory)
                    .registerModule(new JavaTimeModule());
        }
    }
}
