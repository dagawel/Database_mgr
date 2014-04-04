package documentdatabase;

import com.orientechnologies.orient.core.config.OGlobalConfiguration;
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
//        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:database/test").open("orient", "orient");
//
//        // CREATE A NEW DOCUMENT AND FILL IT
//        ODocument doc = new ODocument("Person");
//        doc.field( "name", "Luke" );
//        doc.field( "surname", "Skywalker" );
//        doc.field( "city", new ODocument("City").field("name","Rome").field("country", "Italy") );
//
//        // SAVE THE DOCUMENT
//        doc.save();
//        db.close();
        
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:/orientdb/databases/demo").open("root", "orientt");
//        try {
//            System.out.println(db.getName());
//            //OGlobalConfiguration.STORAGE_KEEP_OPEN.setValue( false );
//        } finally {
//          db.close();
//        }
        
        ODocument doc = new ODocument("Person2");
        doc.field( "name", "Luke" );
        //doc.field( "surname", "Skywalker" );
        //doc.field( "city", new ODocument("City").field("name","Rome").field("country", "Italy") );

        // SAVE THE DOCUMENT
        doc.save();

        db.close();
        }
}
