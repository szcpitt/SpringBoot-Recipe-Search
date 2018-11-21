package com.recipesearch.reciepesearch.Controller.DAO.impl;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.recipesearch.reciepesearch.Controller.DAO.InsertDataDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * user: Carl.Wu
 * date: 11/21/2018
 */
public class InsertDataDAOImpl implements InsertDataDAO {

    @Override
    public void openConnection() {
        try{
            // connect to mongodb
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // connect to database
            MongoDatabase mongoDatabase = mongoClient.getDatabase("recipe");
            System.out.println("Connect to database successfully");

            MongoCollection<DBObject> collection = mongoDatabase.getCollection("recipe", DBObject.class);

            File file = new File("C:\\Users\\YUW121\\Desktop\\ir\\project\\Android-Recipe-Search-Engine\\app\\data\\data.json");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String temp;
            List<DBObject> dbObjectList = new ArrayList<>();
            while ((temp = bufferedReader.readLine()) != null){
                sb.append(temp);
                if(temp.charAt(0) == '}'){
                    dbObjectList.add((DBObject)JSON.parse(sb.toString()));
                    sb = new StringBuilder();
                }
            }
            collection.insertMany(dbObjectList);
            mongoClient.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
