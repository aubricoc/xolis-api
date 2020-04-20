package cat.aubricoc.xolis.users.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.users.model.UserDoc;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDao extends Dao<UserDoc> {

    @Inject
    public UserDao(MongoClient client) {
        super(client, "users", UserDoc.class);
    }

    public boolean existsByUsername(String username) {
        return exists(Filters.eq("username", username));
    }

    public boolean existsByEmail(String email) {
        return exists(Filters.eq("email", email));
    }

    public UserDoc getByUsernameOrEmail(String usernameOrEmail) {
        return getBy(Filters.or(Filters.eq("username", usernameOrEmail), Filters.eq("email", usernameOrEmail)));
    }
}
