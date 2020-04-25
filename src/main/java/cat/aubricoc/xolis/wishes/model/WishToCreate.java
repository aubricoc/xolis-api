package cat.aubricoc.xolis.wishes.model;

import cat.aubricoc.xolis.common.model.Introspected;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotEmpty;

public class WishToCreate implements Introspected {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
