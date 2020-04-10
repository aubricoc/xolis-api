package cat.aubricoc.xolis.wish.dao;

import cat.aubricoc.xolis.wish.model.Wish;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WishDao {

    @Inject
    private MongoClient client;

    private MongoCollection<Wish> getCollection() {
        MongoDatabase database = client.getDatabase("xolis");
        return database.getCollection("wishes", Wish.class);
    }

    public void create(Wish wish) {
        getCollection().insertOne(wish);
    }

    public List<Wish> search() {
        List<Wish> wishes = new ArrayList<>();
        getCollection().find().forEach(wishes::add);
        return wishes;
    }

    public Wish getById(String id) {
        return getCollection().find(Filters.eq("_id", id)).first();
    }
}
