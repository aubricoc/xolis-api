package cat.aubricoc.xolis.wishes.model;

import cat.aubricoc.xolis.common.model.FilterWithOperator;
import cat.aubricoc.xolis.common.model.PaginatedSearch;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;
import java.util.List;

public class WishesSearch extends PaginatedSearch {

    private List<FilterWithOperator<Date>> created;

    public List<FilterWithOperator<Date>> getCreated() {
        return created;
    }

    public void setCreated(List<FilterWithOperator<Date>> created) {
        this.created = created;
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
