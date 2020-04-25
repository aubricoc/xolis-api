package cat.aubricoc.xolis.wishes.model;

import cat.aubricoc.xolis.common.model.FilterWithOperator;
import cat.aubricoc.xolis.common.model.PaginatedSearch;
import io.micronaut.core.annotation.Introspected;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@Introspected
public class WishesSearch extends PaginatedSearch {

    private FilterWithOperator<ZonedDateTime> created;
    private List<Integer> ids;

    public FilterWithOperator<ZonedDateTime> getCreated() {
        return created;
    }

    public void setCreated(FilterWithOperator<ZonedDateTime> created) {
        this.created = created;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
