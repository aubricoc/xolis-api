package cat.aubricoc.xolis.common.utils;

import cat.aubricoc.xolis.common.enums.FilterOperator;
import cat.aubricoc.xolis.common.model.FilterWithOperator;
import cat.aubricoc.xolis.common.model.PaginatedSearch;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Facet;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BsonUtils {

    private BsonUtils() {
        throw new UnsupportedOperationException("Cannot instantiate utilities class");
    }

    public static List<Bson> preparePagination(PaginatedSearch search) {
        return Arrays.asList(
                Aggregates.facet(
                        new Facet("metadata", Aggregates.count("total")),
                        new Facet("data", Aggregates.skip(search.getOffset()), Aggregates.limit(search.getLimit()))
                ),
                Aggregates.addFields(new Field<>("metadata", new Document("$arrayElemAt", Arrays.asList("$metadata", 0))))
        );
    }

    public static List<Bson> prepareSort(String... fields) {
        return Stream.of(fields)
                .map(field -> Aggregates.sort(new Document(field, 1)))
                .collect(Collectors.toList());
    }

    public static List<Bson> prepareFilters(String fieldName, List<?> filters) {
        if (filters == null) {
            return Collections.emptyList();
        }
        return filters.stream()
                .map(filter -> prepareFilter(fieldName, filter))
                .filter(Objects::nonNull)
                .map(Aggregates::match)
                .collect(Collectors.toList());
    }

    private static Bson prepareFilter(String fieldName, Object filter) {
        if (filter == null) {
            return null;
        }
        if (filter instanceof FilterWithOperator) {
            return prepareFilter(fieldName, Utils.cast(filter, FilterWithOperator.class));
        }
        return Filters.eq(fieldName, filter);
    }

    private static Bson prepareFilter(String fieldName, FilterWithOperator<?> filter) {
        if (filter == null) {
            return null;
        }
        FilterOperator operator = filter.getOperator();
        Object value = filter.getValue();
        switch (operator) {
            case EQUALS:
                return Filters.eq(fieldName, value);
            case NOT_EQUALS:
                return Filters.ne(fieldName, value);
            case GREATER_THAN:
                return Filters.gt(fieldName, value);
            case GREATER_OR_EQUALS:
                return Filters.gte(fieldName, value);
            case LESS_THAN:
                return Filters.lt(fieldName, value);
            case LESS_OR_EQUALS:
                return Filters.lte(fieldName, value);
            default:
                throw new IllegalStateException("Unknown operator: " + operator);
        }
    }

    public static List<Bson> prepareOneToOneJoin(String fieldName, String collection, String newFieldWithJoin) {
        return Arrays.asList(
                Aggregates.lookup(collection, fieldName, "_id", newFieldWithJoin),
                Aggregates.addFields(new Field<>(newFieldWithJoin, new Document("$arrayElemAt", Arrays.asList("$user", 0))))
        );
    }
}
