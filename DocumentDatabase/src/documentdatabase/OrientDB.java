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
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("local:/temp/test");
        db.open("admin", "admin");

        try {
          // YOUR CODE
        } finally {
          db.close();
        }
    }
}
