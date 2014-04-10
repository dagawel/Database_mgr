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
    
    public static String[] tabelaStringow(int ilosc, int maksDlugosc) {
        Random rand = new Random();
        String[] str = new String[ilosc];
        for (int i = 0; i < ilosc; i++) {
            int losuj = rand.nextInt(maksDlugosc - 2) + 2;
            str[i] = randomString(losuj);
        } 
        return str;
    }
    
    public static int[] tabelaIntow(int ilosc, int maksDlugosc) {
        Random rand = new Random();
        int[] tab = new int[ilosc];
        for (int i = 0; i < ilosc; i++) {
            tab[i] = rand.nextInt(maksDlugosc - 2) + 2;
        } 
        return tab;
    }
    
    public static void main(String[] args) {
        int rozmiarBazy = 1000000;
        String[] strTagi = tabelaStringow(20, 10);  
        int[] like = tabelaIntow(200, 100);  
        String[] strUrl = tabelaStringow(200, 255); 
        String[] strTytul = tabelaStringow(200, 25); 
        int[] size = tabelaIntow(200, 100000); 
        String[] strType = tabelaStringow(200, 25); 
        String[] strComment = tabelaStringow(200, 25);
        String[] strName = tabelaStringow(200, 25); 
        
        for (int i = 0; i < like.length; i++) {
            //System.out.println(like[i]);
        }
             
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
            int a = 0;
            while (a < rozmiarBazy) {
                Random rand = new Random();
                int indeks = rand.nextInt(strTytul.length - 2) + 2;
                BasicDBObject doc = new BasicDBObject("title", strTytul[indeks]);
                
                Random rand2 = new Random();
                int indeks2 = rand2.nextInt(strUrl.length - 2) + 2;
                doc.append("url", strUrl[indeks2]);
                
                Random rand3 = new Random();
                int indeks3 = rand3.nextInt(strName.length - 2) + 2;
                doc.append("author", strName[indeks3]);
                
                Random rand4 = new Random();
                int indeks4 = rand4.nextInt(like.length - 2) + 2;
                doc.append("likes", like[indeks4]);

                List<String> tags = new ArrayList<String>();
                Random rand5 = new Random();
                int iloscTagow = rand5.nextInt(8 - 2) + 2;
                for (int i = 0; i < iloscTagow; i++) {
                    Random rand6 = new Random();
                    int indeks6 = rand6.nextInt(strTagi.length - 2) + 2;
                    String s = strTagi[indeks6];
                    if (!tags.contains(s)) {
                        tags.add(new String(s));
                    }
                }
                doc.append("tags", tags);
                
                Random rand7 = new Random();
                int indeks7 = rand7.nextInt(strUrl.length - 2) + 2;
                Random rand8 = new Random();
                int indeks8 = rand8.nextInt(strType.length - 2) + 2;
                Random rand9 = new Random();
                int indeks9 = rand9.nextInt(size.length - 2) + 2;
                doc.append("image", new BasicDBObject("url", strUrl[indeks7]).
                                                   append("type", strType[indeks8]).
                                                   append("size", size[indeks9]));

                Random rand10 = new Random();
                List<BasicDBObject> comments = new ArrayList<>();
                int iloscCommentow = rand10.nextInt(20 - 2) + 2;
                for (int i = 0; i < iloscCommentow; i++) {
                    Random rand11 = new Random();
                    int indeks11 = rand11.nextInt(strName.length - 2) + 2;
                    Random rand12 = new Random();
                    int indeks12 = rand12.nextInt(strComment.length - 2) + 2;
                    BasicDBObject doc1 = new BasicDBObject("user", strName[indeks11]);
                    doc1.append("text", strComment[indeks12]);
                    comments.add(doc1);
                }
                doc.put("comments", comments);

                table.insert(doc);
                //System.out.println("Document inserted successfully");
                a++;
            }

//            BasicDBObject searchQuery = new BasicDBObject();
//            searchQuery.put("name", "dagmara");
//            DBCursor cursor = table.find(searchQuery);

      //      DBCursor cursor = table.find();
      //      int i = 1;
       //     while (cursor.hasNext()) { 
       //         System.out.println("Inserted Document: " + i); 
      //          System.out.println(cursor.next()); 
      //          i++;
      //      }
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
