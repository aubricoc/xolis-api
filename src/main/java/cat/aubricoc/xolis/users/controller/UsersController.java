package cat.aubricoc.xolis.users.controller;

import cat.aubricoc.xolis.users.model.UserToCreate;
import cat.aubricoc.xolis.users.service.UserService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;

@Controller("/users")
public class UsersController {

    @Inject
    private UserService userService;

    @Post
    public void createUser(@Body UserToCreate user) {
        userService.create(user);
    }
}
