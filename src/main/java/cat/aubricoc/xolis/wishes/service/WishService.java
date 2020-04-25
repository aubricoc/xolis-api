package cat.aubricoc.xolis.wishes.service;

import cat.aubricoc.xolis.common.model.SearchResult;
import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.security.service.AuthService;
import cat.aubricoc.xolis.wishes.dao.WishDao;
import cat.aubricoc.xolis.wishes.model.SearchWishesResult;
import cat.aubricoc.xolis.wishes.model.Wish;
import cat.aubricoc.xolis.wishes.model.WishDoc;
import cat.aubricoc.xolis.wishes.model.WishToCreate;
import cat.aubricoc.xolis.wishes.model.WishesSearch;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
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

    public void create(@Nonnull WishToCreate wishToCreate) {
        WishDoc wishDoc = ConversionUtils.convert(wishToCreate, WishDoc.class);
        wishDoc.setId(UUID.randomUUID().toString());
        wishDoc.setCreated(new Date());
        wishDoc.setUserId(authService.getUserId());
        wishDao.create(wishDoc);
    }

    public SearchResult<Wish> search(@Nonnull WishesSearch search) {
        SearchWishesResult searchResult = wishDao.search(search);
        return ConversionUtils.convertSearchResult(searchResult, Wish.class);
    }

    public Wish getById(@Nonnull String id) {
        return ConversionUtils.convert(wishDao.getById(id), Wish.class);
    }
}
