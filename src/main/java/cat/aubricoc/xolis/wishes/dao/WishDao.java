package cat.aubricoc.xolis.wishes.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.common.utils.BsonUtils;
import cat.aubricoc.xolis.common.utils.Utils;
import cat.aubricoc.xolis.wishes.model.SearchWishesResult;
import cat.aubricoc.xolis.wishes.model.WishDoc;
import cat.aubricoc.xolis.wishes.model.WishesSearch;
import com.mongodb.client.MongoClient;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WishDao extends Dao<WishDoc> {

    @Inject
    public WishDao(MongoClient client) {
        super(client, "wishes", WishDoc.class);
    }

    public SearchWishesResult search(WishesSearch search) {
        boolean onlyCount = search.getLimit() == 0;
        return getCollection().aggregate(
                Utils.mergeLists(
                        BsonUtils.prepareFilters("created", search.getCreated()),
                        onlyCount ? null : BsonUtils.prepareOneToOneJoin("userId", "users", "user"),
                        onlyCount ? null : BsonUtils.prepareSort("created"),
                        BsonUtils.preparePagination(search)
                ), SearchWishesResult.class)
                .first();
    }
}
