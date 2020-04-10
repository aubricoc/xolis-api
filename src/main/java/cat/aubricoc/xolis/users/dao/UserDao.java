package cat.aubricoc.xolis.users.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.users.model.User;

import javax.inject.Singleton;

@Singleton
public class UserDao extends Dao<User> {

    public UserDao() {
        super("users", User.class);
    }
}
