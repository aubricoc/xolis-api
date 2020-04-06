package cat.aubricoc.xolis.controller;

import cat.aubricoc.xolis.model.Wish;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

@Controller("/wishes")
public class WishesController {

    @Get
    public List<Wish> searchWishes() {
        return Collections.singletonList(buildWish(null));
    }

    @Get("/{id}")
    public Wish getWish(Long id) {
        return buildWish(id);
    }

    private Wish buildWish(@Nullable Long id) {
        Wish wish = new Wish();
        wish.setId(id != null ? id : 1L);
        wish.setTitle("Xolís del Pallars");
        return wish;
    }
}
