package cat.aubricoc.xolis.wishes.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.wishes.model.Wish;
import com.mongodb.client.MongoClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WishDao extends Dao<Wish> {

    @Inject
    public WishDao(MongoClient client) {
        super(client, "wishes", Wish.class);
    }
}
