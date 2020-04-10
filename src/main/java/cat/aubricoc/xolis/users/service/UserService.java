package cat.aubricoc.xolis.users.service;

import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.users.dao.UserDao;
import cat.aubricoc.xolis.users.model.User;
import cat.aubricoc.xolis.users.model.UserToCreate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UserService {

    private final UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(UserToCreate userToCreate) {
        User user = ConversionUtils.convert(userToCreate, User.class);
        user.setId(UUID.randomUUID().toString());
        userDao.create(user);
    }
}
