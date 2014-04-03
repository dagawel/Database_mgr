/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package documentdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dagawel
 */
public class OracleDatabase {
    Connection connection;

    /**
     * @param args the command line arguments
     */
    
    public OracleDatabase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:XE", "Dagmara", "oracle");
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public String zapiszKlient () throws ClassNotFoundException, SQLException {
        String tekst = "";
        if (connection != null) {
            java.sql.Statement s = connection.createStatement();
            s.execute("CREATE TABLE DAGMARA.Klienci (" +
                        "  NIK INTEGER CONSTRAINT klienci_pk PRIMARY KEY," +
                        "  NIP VARCHAR2(15) CONSTRAINT NIP_klienta UNIQUE," +
                        "  Nazwisko VARCHAR2(30)," +
                        "  Imie VARCHAR2(30)," +
                        "  Login_ID VARCHAR2(38)" +
                        ") ");
            tekst = "Dodano do bazy danych!";
            s.close();
            connection.close();
        } 
        else {
            tekst = "Nie moge się połączyć! I jest mega dupa";
        }
        return tekst;
    }
    
    public String zapiszKlientRekordy () throws ClassNotFoundException, SQLException {
        String tekst = "";
        if (connection != null) {
            java.sql.Statement s = connection.createStatement();
            s.execute("INSERT INTO DAGMARA.Klienci (NIK, NIP, Nazwisko, Imie, Login_ID) VALUES (1, 1, 'Gawel', 'Dagmara', 'Dagawel')");   
            tekst = "Dodano do bazy danych!";
            s.close();
            connection.close();
        } 
        else {
            tekst = "Nie moge się połączyć! I jest mega dupa";
        }
        return tekst;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        OracleDatabase orcl = new OracleDatabase();
        System.out.println(orcl.zapiszKlientRekordy());
    }
    
}
