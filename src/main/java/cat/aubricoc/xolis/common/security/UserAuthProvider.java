package cat.aubricoc.xolis.common.security;

import cat.aubricoc.xolis.users.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;

@Singleton
public class UserAuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Inject
    public UserAuthProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        Object identity = authenticationRequest.getIdentity();
        Object secret = authenticationRequest.getSecret();
        if (identity instanceof String &&
                secret instanceof String &&
                userService.isUserValid((String) identity, (String) secret)) {
            return Flowable.just(new UserDetails((String) identity, Collections.singletonList(Role.USER)));
        }
        return Flowable.just(new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH));
    }
}
