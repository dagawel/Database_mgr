package documentdatabase;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


/**
 *
 * @author Dagawel
 */


public class OrientDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ////////////////////// 1 /////////////////////////

        ////////////////////// 2 /////////////////////////
//        ODatabaseDocumentTx db = new ODatabaseDocumentTx("local:/tmp/db/scratchpad");
//        db.create(); 
//        ODocument doc = db.newInstance(); 
//        doc.field("name", "Dagmara");  
//        doc.field("surname", "Gaweł"); 
//        doc.save();
//        db.close(); 
        
        ////////////////////// 2 /////////////////////////
        // OPEN THE DATABASE
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("remote:localhost/petshop").open("admin", "admin");

        // CREATE A NEW DOCUMENT AND FILL IT
        ODocument doc = new ODocument("Person");
        doc.field( "name", "Luke" );
        doc.field( "surname", "Skywalker" );
        doc.field( "city", new ODocument("City").field("name","Rome").field("country", "Italy") );

        // SAVE THE DOCUMENT
        doc.save();
        db.close();
        
        // USE THE DATABASE
//        ODatabaseDocumentTx db = new ODatabaseDocumentTx("local:/temp/test");
//        db.open("admin", "admin");
//
//        try {
//          // YOUR CODE
//        } finally {
//          db.close();
//        }
    }
}
