package cat.aubricoc.xolis.wishes.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.wishes.model.Wish;

import javax.inject.Singleton;

@Singleton
public class WishDao extends Dao<Wish> {

    public WishDao() {
        super("wishes", Wish.class);
    }
}
