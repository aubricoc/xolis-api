package cat.aubricoc.xolis.users.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.users.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDao extends Dao<User> {

    @Inject
    public UserDao(MongoClient client) {
        super(client, "users", User.class);
    }

    public boolean existsByUsername(String username) {
        return exists(Filters.eq("username", username));
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        return exists(Filters.eq("username", username),
                Filters.eq("password", password));
    }
}
