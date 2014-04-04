package documentdatabase;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
 
/**
 * Java + MongoDB Hello world Example
 * 
 */
public class MongoDB {
//    public static void main(String[] args) {
//
//        try {
//
//            /**** Connect to MongoDB ****/
//            // Since 2.10.0, uses MongoClient
//            MongoClient mongo = new MongoClient("localhost", 27017);
//
//            /**** Get database ****/
//            // if database doesn't exists, MongoDB will create it for you
//            DB db = mongo.getDB("test");
//
//            /**** Get collection / table from 'testdb' ****/
//            // if collection doesn't exists, MongoDB will create it for you
//            DBCollection table = db.getCollection("user");
//
//            /**** Insert ****/
//            // create a document to store key and value
//            BasicDBObject document = new BasicDBObject();
//            document.put("name", "dagmara");
//            document.put("age", 23);
//            document.put("createdDate", new Date());
//            table.insert(document);
//
//            /**** Find and display ****/
//            BasicDBObject searchQuery = new BasicDBObject();
//            searchQuery.put("name", "dagmara");
//
//            DBCursor cursor = table.find(searchQuery);
//
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next());
//            }
//
//            /**** Update ****/
//            // search document where name="mkyong" and update it with new values
//            BasicDBObject query = new BasicDBObject();
//            query.put("name", "dagmara");
//
//            BasicDBObject newDocument = new BasicDBObject();
//            newDocument.put("name", "dagmara gawel");
//
//            BasicDBObject updateObj = new BasicDBObject();
//            updateObj.put("$set", newDocument);
//
//            table.update(query, updateObj);
//
//            /**** Find and display ****/
//            BasicDBObject searchQuery2 = new BasicDBObject().append("name", "dagmara gawel");
//
//            DBCursor cursor2 = table.find(searchQuery2);
//
//            while (cursor2.hasNext()) {
//                System.out.println(cursor2.next());
//            }
//
//            /**** Done ****/
//            System.out.println("Done");
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (MongoException e) {
//            e.printStackTrace();
//        }
//        
//    }
    /////////////
    public static void main( String args[] ){
      try{   
		 // To connect to mongodb server
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         // Now connect to your databases
         DB db = mongoClient.getDB( "test" );
		 System.out.println("Connect to database successfully");
         char[] haslo = {'m', 'o', 'n', 'g', 'o'}; 
         boolean auth = db.authenticate("mongo", haslo);
		 System.out.println("Authentication: "+auth);
      }catch(Exception e){
	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	  }
   }
}
