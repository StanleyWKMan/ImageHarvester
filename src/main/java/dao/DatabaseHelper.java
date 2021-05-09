package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class DatabaseHelper {

    private String cred = "";

    public void insertUpdateDocument(Document document, String collectionName) {
        MongoClientURI uri = new MongoClientURI(cred);
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("ImageHarvester");

        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);

        mongoClient.close();
    }

    public Document findDocument(String key, String value, String collectionName) {
        MongoClientURI uri = new MongoClientURI(cred);
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("ImageHarvester");
        MongoCollection<Document> collection = database.getCollection(collectionName);

        BasicDBObject query = new BasicDBObject();
        query.put(key, value);

        Document document = collection.find(query).first();
        System.out.println(document);

        mongoClient.close();
        return document;
    }

    public FindIterable<Document> getAllDocumentFromCollection(String collectionName) {
        MongoClientURI uri = new MongoClientURI(cred);
        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("ImageHarvester");
        MongoCollection<Document> collection = database.getCollection(collectionName);

        System.out.println("Collection: " + collection);

        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            System.out.println(document);
        }

        return documents;
    }



}
