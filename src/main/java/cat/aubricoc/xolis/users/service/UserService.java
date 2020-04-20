package cat.aubricoc.xolis.users.service;

import cat.aubricoc.xolis.common.exception.ConflictException;
import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.security.utils.PasswordUtils;
import cat.aubricoc.xolis.users.dao.UserDao;
import cat.aubricoc.xolis.users.model.UserDoc;
import cat.aubricoc.xolis.users.model.UserToCreate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
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
            throw new ConflictException("username", "Username is already used");
        }
        if (userDao.existsByEmail(userToCreate.getEmail())) {
            throw new ConflictException("email", "Email is already used");
        }
        UserDoc userDoc = ConversionUtils.convert(userToCreate, UserDoc.class);
        userDoc.setId(UUID.randomUUID().toString());
        userDoc.setPassword(PasswordUtils.encode(userToCreate.getPassword()));
        userDoc.setCreated(new Date());
        userDao.create(userDoc);
    }
}
