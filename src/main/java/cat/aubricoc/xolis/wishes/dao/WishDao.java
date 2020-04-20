package cat.aubricoc.xolis.wishes.dao;

import cat.aubricoc.xolis.common.dao.Dao;
import cat.aubricoc.xolis.wishes.model.WishDoc;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

@Singleton
public class WishDao extends Dao<WishDoc> {

    @Inject
    public WishDao(MongoClient client) {
        super(client, "wishes", WishDoc.class);
    }

    @Override
    public List<WishDoc> search() {
        return toList(getCollection().aggregate(
                Arrays.asList(
                        Aggregates.lookup("users", "userId", "_id", "user"),
                        Aggregates.addFields(new Field<>("user", new Document("$arrayElemAt", Arrays.asList("$user", 0))))
                ),
                WishDoc.class));
    }
}
