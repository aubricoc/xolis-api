package cat.aubricoc.xolis.wishes.controller;

import cat.aubricoc.xolis.common.model.PaginatedSearch;
import cat.aubricoc.xolis.common.utils.HttpUtils;
import cat.aubricoc.xolis.security.Role;
import cat.aubricoc.xolis.wishes.model.Wish;
import cat.aubricoc.xolis.wishes.model.WishToCreate;
import cat.aubricoc.xolis.wishes.service.WishService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller("/wishes")
public class WishesController {

    private final WishService wishService;

    @Inject
    public WishesController(WishService wishService) {
        this.wishService = wishService;
    }

    @Get("{?search*}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<List<Wish>> searchWishes(@Valid PaginatedSearch search) {
        return HttpUtils.buildResponse(wishService.search(search));
    }

    @Get("/{id}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Wish getWish(@PathVariable String id) {
        return wishService.getById(id);
    }

    @Post
    @Status(HttpStatus.CREATED)
    @Secured(Role.USER)
    public void createWish(@Valid @Body WishToCreate wish) {
        wishService.create(wish);
    }
}
