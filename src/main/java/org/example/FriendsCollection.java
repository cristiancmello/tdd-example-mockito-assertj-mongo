package org.example;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientException;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class FriendsCollection {
    private MongoCollection friends;

    public FriendsCollection() {
        try {
            DB db = new MongoClient("localhost", 27017).getDB("friendships");
            friends = new Jongo(db).getCollection("friends");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Person findByName(String name) {
        return friends
            .findOne("{_id: #}", name)
            .as(Person.class);
    }

    public void save(Person p) {
        friends.save(p);
    }
}
