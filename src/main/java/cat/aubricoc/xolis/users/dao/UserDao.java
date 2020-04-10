package cat.aubricoc.xolis.users.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.users.model.User;
import com.mongodb.client.MongoClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDao extends Dao<User> {

    @Inject
    public UserDao(MongoClient client) {
        super(client, "users", User.class);
    }
}
