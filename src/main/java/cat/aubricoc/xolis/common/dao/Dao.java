package cat.aubricoc.xolis.common.dao;

import cat.aubricoc.xolis.common.model.Identified;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T extends Identified> {

    public static final String DATABASE_NAME = "xolis";
    private final MongoClient client;
    private final String collectionName;
    private final Class<T> type;

    protected Dao(MongoClient client, String collectionName, Class<T> type) {
        this.client = client;
        this.collectionName = collectionName;
        this.type = type;
    }

    protected MongoCollection<T> getCollection() {
        MongoDatabase database = client.getDatabase(DATABASE_NAME);
        return database.getCollection(collectionName, type);
    }

    public void create(T object) {
        getCollection().insertOne(object);
    }

    public void update(T object) {
        getCollection().replaceOne(getFilterById(object.getId()), object);
    }

    public List<T> search() {
        return toList(getCollection().find());
    }

    public T getById(String id) {
        return getCollection().find(getFilterById(id)).first();
    }

    protected List<T> toList(MongoIterable<T> iterable) {
        List<T> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }

    protected T getBy(Bson... filters) {
        if (filters == null || filters.length == 0) {
            return getCollection().find().first();
        }
        return getCollection().find(Filters.and(filters)).first();
    }

    protected long countBy(Bson... filters) {
        if (filters == null || filters.length == 0) {
            return getCollection().countDocuments();
        }
        return getCollection().countDocuments(Filters.and(filters));
    }

    protected boolean exists(Bson... filters) {
        return countBy(filters) > 0;
    }

    private Bson getFilterById(String id) {
        return Filters.eq("_id", id);
    }
}
