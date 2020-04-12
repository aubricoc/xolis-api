package cat.aubricoc.xolis.users.controller;

import cat.aubricoc.xolis.users.model.UserToCreate;
import cat.aubricoc.xolis.users.service.UserService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller("/users")
public class UsersController {

    private final UserService userService;

    @Inject
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Secured(SecurityRule.IS_ANONYMOUS)
    public void createUser(@Valid @Body UserToCreate user) {
        userService.create(user);
    }
}
