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
import java.util.Random;
 
/**
 * Java + MongoDB Hello world Example
 * 
 */
public class MongoDB {
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) (((int)(Math.random() * 26)) + (int)'A');
        }
        return (new String(str, 0, len));
    }
    
    public static ArrayList<String> tabelaStringow(int ilosc, int maksDlugosc) {
        Random rand = new Random();
        ArrayList<String> str = new ArrayList<String>();
        for (int i = 0; i < ilosc; i++) {
            int losuj = rand.nextInt(maksDlugosc - 2) + 2;
            String s = randomString(losuj);
            if (!str.contains(s)) {
                str.add(s);
            }
        } 
        return str;
    }
    
    public static ArrayList<Integer> tabelaIntow(int ilosc, int maksDlugosc) {
        Random rand = new Random();
        ArrayList<Integer> tab = new ArrayList<Integer>();
        for (int i = 0; i < ilosc; i++) {
            int losuj = rand.nextInt(maksDlugosc - 2) + 2;
            if (!tab.contains(losuj)) {
                tab.add(losuj);
            }
        } 
        return tab;
    }
    
    public static void main(String[] args) {
        int rozmiarBazy = 100;
        ArrayList<String> strTagi = tabelaStringow(20, 10);  
        ArrayList<Integer> like = tabelaIntow(200, 1000);  
        ArrayList<String> strUrl = tabelaStringow(200, 255); 
        ArrayList<String> strTytul = tabelaStringow(200, 25); 
        ArrayList<Integer> size = tabelaIntow(200, 10000); 
        ArrayList<String> strType = tabelaStringow(200, 25); 
        ArrayList<String> strComment = tabelaStringow(200, 255);
        ArrayList<String> strName = tabelaStringow(200, 25); 
        Random rand = new Random();  
        try {
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("test");
            System.out.println("Connect to database successfully");
            char[] haslo = {'m', 'o', 'n', 'g', 'o'}; 
            boolean auth = db.authenticate("mongo", haslo);
            System.out.println("Authentication: " + auth);

            DBCollection table = db.getCollection("user");
            table.drop();
            System.out.println("Collection user selected successfully");
            int a = 0;
            while (a < rozmiarBazy) {   
                int indeks = rand.nextInt(strTytul.size() - 2) + 2;
                BasicDBObject doc = new BasicDBObject("title", strTytul.get(indeks));

                int indeks2 = rand.nextInt(strUrl.size() - 2) + 2;
                doc.append("url", strUrl.get(indeks2));
                
                int indeks3 = rand.nextInt(strName.size() - 2) + 2;
                doc.append("author", strName.get(indeks3));
                
                int indeks4 = rand.nextInt(like.size() - 2) + 2;
                doc.append("likes", like.get(indeks4));

                List<String> tags = new ArrayList<String>();
                int iloscTagow = rand.nextInt(8 - 2) + 2;
                for (int i = 0; i < iloscTagow; i++) {
                    int indeks6 = rand.nextInt(strTagi.size() - 2) + 2;
                    String s = strTagi.get(indeks6);
                    if (!tags.contains(s)) {
                        tags.add(new String(s));
                    }
                }
                doc.append("tags", tags);
                
                int indeks7 = rand.nextInt(strUrl.size() - 2) + 2;
                int indeks8 = rand.nextInt(strType.size() - 2) + 2;
                int indeks9 = rand.nextInt(size.size() - 2) + 2;
                doc.append("image", new BasicDBObject("url", strUrl.get(indeks7)).
                                                   append("type", strType.get(indeks8)).
                                                   append("size", size.get(indeks9)));

                List<BasicDBObject> comments = new ArrayList<>();
                int iloscCommentow = rand.nextInt(20 - 2) + 2;
                for (int i = 0; i < iloscCommentow; i++) {
                    int indeks11 = rand.nextInt(strName.size() - 2) + 2;
                    int indeks12 = rand.nextInt(strComment.size() - 2) + 2;
                    BasicDBObject doc1 = new BasicDBObject("user", strName.get(indeks11));
                    doc1.append("text", strComment.get(indeks12));
                    comments.add(doc1);
                }
                doc.put("comments", comments);

                table.insert(doc);
                a++;
            }

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
