package cat.aubricoc.xolis.wishes.service;

import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.security.service.AuthService;
import cat.aubricoc.xolis.wishes.dao.WishDao;
import cat.aubricoc.xolis.wishes.model.Wish;
import cat.aubricoc.xolis.wishes.model.WishToCreate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Singleton
public class WishService {

    private final AuthService authService;
    private final WishDao wishDao;

    @Inject
    public WishService(AuthService authService, WishDao wishDao) {
        this.authService = authService;
        this.wishDao = wishDao;
    }

    public void create(WishToCreate wishToCreate) {
        Wish wish = ConversionUtils.convert(wishToCreate, Wish.class);
        wish.setId(UUID.randomUUID().toString());
        wish.setCreated(new Date());
        wish.setUserId(authService.getUserId());
        wishDao.create(wish);
    }

    public List<Wish> search() {
        return wishDao.search();
    }

    public Wish getById(String id) {
        return wishDao.getById(id);
    }
}
