package cat.aubricoc.xolis.users.service;

import cat.aubricoc.xolis.common.exception.ConflictException;
import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.common.utils.PasswordUtils;
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
        if (userDao.existsByUsername(userToCreate.getUsername())) {
            throw new ConflictException("Username is already used");
        }
        User user = ConversionUtils.convert(userToCreate, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(PasswordUtils.encode(userToCreate.getPassword()));
        userDao.create(user);
    }

    public boolean isUserValid(String username, String password) {
        String encodedPassword = PasswordUtils.encode(password);
        return userDao.existsByUsernameAndPassword(username, encodedPassword);
    }
}
