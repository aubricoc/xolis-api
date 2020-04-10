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

    public boolean existsByUsernameAndPassword(String username, String password) {
        return getCollection().countDocuments(Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", password))) > 0;
    }
}
