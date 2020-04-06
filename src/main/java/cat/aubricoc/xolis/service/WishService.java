package cat.aubricoc.xolis.service;

import cat.aubricoc.xolis.dao.WishDao;
import cat.aubricoc.xolis.model.Wish;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Singleton
public class WishService {

    @Inject
    private WishDao wishDao;

    public void create(Wish wish) {
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
