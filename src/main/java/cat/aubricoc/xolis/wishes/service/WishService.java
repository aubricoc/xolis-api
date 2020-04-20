package cat.aubricoc.xolis.wishes.service;

import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.security.service.AuthService;
import cat.aubricoc.xolis.wishes.dao.WishDao;
import cat.aubricoc.xolis.wishes.model.Wish;
import cat.aubricoc.xolis.wishes.model.WishDoc;
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
        WishDoc wishDoc = ConversionUtils.convert(wishToCreate, WishDoc.class);
        wishDoc.setId(UUID.randomUUID().toString());
        wishDoc.setCreated(new Date());
        wishDoc.setUserId(authService.getUserId());
        wishDao.create(wishDoc);
    }

    public List<Wish> search() {
        return ConversionUtils.convert(wishDao.search(), Wish.class);
    }

    public Wish getById(String id) {
        return ConversionUtils.convert(wishDao.getById(id), Wish.class);
    }
}
