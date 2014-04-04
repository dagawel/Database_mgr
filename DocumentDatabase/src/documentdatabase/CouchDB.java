package documentdatabase;

import java.util.List;
import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.ViewResults;
/**
 *
 * @author Dagawel
 */
public class CouchDB {
    
    public static void main(String[] args) { 
        Session s = new Session("localhost", 5984);
        Database db = s.getDatabase("test");
        Document doc = db.getDocument("9087be7915aac95b08db7a54ed000df9");
        doc.put("foo", "bar");
        db.saveDocument(doc);

        Document newdoc = new Document();
        newdoc.put("foo", "baz"); // same as JSON: { foo: "baz"; }
        db.saveDocument(newdoc); // auto-generated id given by the database

        // Running a view
        ViewResults result = db.getAllDocuments(); // same as db.view("_all_dbs");
        for (Document d: result.getResults()) {
                System.out.println(d.getId());
                Document full = db.getDocument(d.getId());
        }

        // Ad-Hoc view
        ViewResults resultAdHoc = db.adhoc("function (doc) { if (doc.foo=='bar') { return doc; }}");
    }
}