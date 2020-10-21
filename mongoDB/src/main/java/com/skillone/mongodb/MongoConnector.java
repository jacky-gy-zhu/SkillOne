package com.skillone.mongodb;

import com.mongodb.*;

public class MongoConnector {

    public static void main(String[] args) throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 2717);
        mongoClient.getDatabaseNames().forEach(System.out::println);

        // Create a Collection
        DB database = mongoClient.getDB("test");
        database.createCollection("customers", null);

        // Save – Insert
        DBCollection collection = database.getCollection("customers");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Shubham");
        document.put("company", "Baeldung");
        collection.insert(document);

        // Save – Update
        BasicDBObject query = new BasicDBObject();
        query.put("name", "Shubham");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "John");

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        collection.update(query, updateObject);

        // Read a Document From a Collection
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "John");
        DBCursor cursor = collection.find(searchQuery);

        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        // Delete a Document
        BasicDBObject searchQuery2 = new BasicDBObject();
        searchQuery2.put("name", "John");

        collection.remove(searchQuery2);
    }
}
