package documentdatabase;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
/**
 * Java + MongoDB Hello world Example
 * 
 */
public class MongoDB {
    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("test");
            System.out.println("Connect to database successfully");
            char[] haslo = {'m', 'o', 'n', 'g', 'o'}; 
            boolean auth = db.authenticate("mongo", haslo);
            System.out.println("Authentication: "+auth);

            DBCollection table = db.getCollection("user");
            table.drop();
            System.out.println("Collection user selected successfully");

            BasicDBObject doc = new BasicDBObject("title", "Adventures in Databases");
            doc.append("url", "http://example.com/databases.txt");
            doc.append("author", "msmith");
            doc.append("likes", 20);
            
            String[] tabTags = new String[]{"databases", "mongodb", "indexing"};
            List<String> tags = Arrays.asList(tabTags);
            doc.append("tags", tags);
            
            doc.append("image", new BasicDBObject("url", "http://example.com/databases.txt").
                                               append("type", "jpg").
                                               append("size", 75381));

            List<BasicDBObject> comments = new ArrayList<>();
            BasicDBObject doc1 = new BasicDBObject("user", "bjones");
            doc1.append("text", "Interesting article!");  
            BasicDBObject doc2 = new BasicDBObject("user", "blogger");
            doc2.append("text", "Another related article is at http://example.com/db/db.txt");
            comments.add(doc1);
            comments.add(doc2);
            doc.put("comments", comments);
   
            table.insert(doc);
            System.out.println("Document inserted successfully");

//            BasicDBObject searchQuery = new BasicDBObject();
//            searchQuery.put("name", "dagmara");
//            DBCursor cursor = table.find(searchQuery);

            DBCursor cursor = table.find();
            int i = 1;
            while (cursor.hasNext()) { 
                System.out.println("Inserted Document: " + i); 
                System.out.println(cursor.next()); 
                i++;
            }
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

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        
    }
    /////////////
//    public static void main( String args[] ){
//      try{   
//		 // To connect to mongodb server
//         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//         // Now connect to your databases
//         DB db = mongoClient.getDB( "test" );
//		 System.out.println("Connect to database successfully");
//         char[] haslo = {'m', 'o', 'n', 'g', 'o'}; 
//         boolean auth = db.authenticate("mongo", haslo);
//		 System.out.println("Authentication: "+auth);
//      }catch(Exception e){
//	     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	  }
//   }
}
