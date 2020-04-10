package cat.aubricoc.xolis.wishes.controller;

import cat.aubricoc.xolis.wishes.model.Wish;
import cat.aubricoc.xolis.wishes.model.WishToCreate;
import cat.aubricoc.xolis.wishes.service.WishService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import java.util.List;

@Controller("/wishes")
public class WishesController {

    @Inject
    private WishService wishService;

    @Get
    public List<Wish> searchWishes() {
        return wishService.search();
    }

    @Get("/{id}")
    public Wish getWish(@PathVariable String id) {
        return wishService.getById(id);
    }

    @Post
    public void createWish(@Body WishToCreate wish) {
        wishService.create(wish);
    }
}
