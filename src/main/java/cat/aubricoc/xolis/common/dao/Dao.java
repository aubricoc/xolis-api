package cat.aubricoc.xolis.common.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

    private final String collectionName;
    private final Class<T> type;
    @Inject
    private MongoClient client;

    protected Dao(String collectionName, Class<T> type) {
        this.collectionName = collectionName;
        this.type = type;
    }

    private MongoCollection<T> getCollection() {
        MongoDatabase database = client.getDatabase("xolis");
        return database.getCollection(collectionName, type);
    }

    public void create(T object) {
        getCollection().insertOne(object);
    }

    public List<T> search() {
        List<T> list = new ArrayList<>();
        getCollection().find().forEach(list::add);
        return list;
    }

    public T getById(String id) {
        return getCollection().find(Filters.eq("_id", id)).first();
    }
}
