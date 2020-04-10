package cat.aubricoc.xolis.wishes.service;

import cat.aubricoc.xolis.common.utils.ConversionUtils;
import cat.aubricoc.xolis.wishes.dao.WishDao;
import cat.aubricoc.xolis.wishes.model.Wish;
import cat.aubricoc.xolis.wishes.model.WishToCreate;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public class WishService {

    @Inject
    private WishDao wishDao;

    public void create(WishToCreate wishToCreate) {
        Wish wish = ConversionUtils.convert(wishToCreate, Wish.class);
        wish.setId(UUID.randomUUID().toString());
        wishDao.create(wish);
    }

    public List<Wish> search() {
        return wishDao.search();
    }

    public Wish getById(String id) {
        return wishDao.getById(id);
    }
}
