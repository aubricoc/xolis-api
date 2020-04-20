package cat.aubricoc.xolis.security.service;

import cat.aubricoc.xolis.security.Role;
import cat.aubricoc.xolis.security.utils.PasswordUtils;
import cat.aubricoc.xolis.users.dao.UserDao;
import cat.aubricoc.xolis.users.model.User;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.utils.SecurityService;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class AuthService implements AuthenticationProvider {

    private static final String ADMIN = "aubricoc";
    private static final String ATTRIBUTE_USER_ID = "userId";

    private final SecurityService securityService;
    private final UserDao userDao;

    @Inject
    public AuthService(SecurityService securityService, UserDao userDao) {
        this.securityService = securityService;
        this.userDao = userDao;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        Object identity = authenticationRequest.getIdentity();
        Object secret = authenticationRequest.getSecret();
        if (identity instanceof String && secret instanceof String) {
            return Flowable.just(validateUserAuth((String) identity, (String) secret));
        }
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
    }

    private AuthenticationResponse validateUserAuth(String usernameOrEmail, String password) {
        User user = userDao.getByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
        }
        String encodedPassword = PasswordUtils.encode(password);
        if (!user.getPassword().equals(encodedPassword)) {
            return new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }
        String username = user.getUsername();
        return new UserDetails(username, getRoles(username), buildAttributes(user));
    }

    private Map<String, Object> buildAttributes(User user) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(ATTRIBUTE_USER_ID, user.getId());
        return attributes;
    }

    private List<String> getRoles(String username) {
        if (ADMIN.equals(username)) {
            return Arrays.asList(Role.USER, Role.ADMIN);
        }
        return Collections.singletonList(Role.USER);
    }

    public String getUserId() {
        return (String) securityService.getAuthentication()
                .map(Authentication::getAttributes)
                .map(map -> map.get(ATTRIBUTE_USER_ID))
                .orElse(null);
    }
}
