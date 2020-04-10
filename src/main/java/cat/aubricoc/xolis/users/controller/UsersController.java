package cat.aubricoc.xolis.users.controller;

import cat.aubricoc.xolis.users.model.UserToCreate;
import cat.aubricoc.xolis.users.service.UserService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

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
    public void createUser(@Valid @Body UserToCreate user) {
        userService.create(user);
    }
}
