package cat.aubricoc.xolis.wishes.model;

import cat.aubricoc.xolis.common.model.Doc;
import cat.aubricoc.xolis.users.model.UserDoc;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class WishDoc extends Doc {

    private String name;
    private String userId;
    private Date created;
    private UserDoc user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserDoc getUser() {
        return user;
    }

    public void setUser(UserDoc user) {
        this.user = user;
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
