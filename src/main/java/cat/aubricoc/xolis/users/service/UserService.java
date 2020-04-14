package cat.aubricoc.xolis.users.service;

import cat.aubricoc.xolis.common.exception.ConflictException;
import cat.aubricoc.xolis.common.security.Role;
import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.common.utils.PasswordUtils;
import cat.aubricoc.xolis.users.dao.UserDao;
import cat.aubricoc.xolis.users.model.User;
import cat.aubricoc.xolis.users.model.UserToCreate;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
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
        User user = ConversionUtils.convert(userToCreate, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(PasswordUtils.encode(userToCreate.getPassword()));
        userDao.create(user);
    }

    public AuthenticationResponse validateUserAuth(String usernameOrEmail, String password) {
        User user = userDao.getByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
        }
        String encodedPassword = PasswordUtils.encode(password);
        if (!user.getPassword().equals(encodedPassword)) {
            return new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }
        return new UserDetails(user.getUsername(), Collections.singletonList(Role.USER));
    }
}
