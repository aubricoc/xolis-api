package cat.aubricoc.xolis.wishes.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.common.model.PaginatedSearch;
import cat.aubricoc.xolis.wishes.model.SearchWishesResult;
import cat.aubricoc.xolis.wishes.model.WishDoc;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Facet;
import com.mongodb.client.model.Field;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;

@Singleton
public class WishDao extends Dao<WishDoc> {

    @Inject
    public WishDao(MongoClient client) {
        super(client, "wishes", WishDoc.class);
    }

    public SearchWishesResult search(PaginatedSearch search) {
        return getCollection().aggregate(
                Arrays.asList(
                        Aggregates.lookup("users", "userId", "_id", "user"),
                        Aggregates.addFields(new Field<>("user", new Document("$arrayElemAt", Arrays.asList("$user", 0)))),
                        Aggregates.sort(new Document("created", 1)),
                        Aggregates.facet(
                                new Facet("metadata", Aggregates.count("total")),
                                new Facet("data", Aggregates.skip(search.getOffset()), Aggregates.limit(search.getLimit()))
                        ),
                        Aggregates.addFields(new Field<>("metadata", new Document("$arrayElemAt", Arrays.asList("$metadata", 0))))
                ),
                SearchWishesResult.class)
                .first();
    }
}
