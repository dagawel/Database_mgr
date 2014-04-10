/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package documentdatabase;

import static documentdatabase.MongoDB.tabelaStringow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dagawel
 */
public class OracleDatabase {
    Connection connection;
    Statement s;

    /**
     * @param args the command line arguments
     */
    
    public OracleDatabase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:XE", "Dagmara", "oracle");
            s = connection.createStatement();
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
    
    public void drukuj () throws SQLException {
        ResultSet rs = s.executeQuery("SELECT * FROM KLIENCI");
        while (rs.next()) {
            System.out.println(rs.getString("NAZWISKO"));
        }
        rs.close();
        s.close();
        connection.close();
    }
    
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) (((int)(Math.random() * 26)) + (int)'A');
        }
        return (new String(str, 0, len));
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        OracleDatabase orcl = new OracleDatabase();
        //orcl.drukuj();
        //System.out.println(randomString(5));
        //System.out.println(orcl.zapiszKlientRekordy());
        
        String[] strTagi = tabelaStringow(20, 10);
        List<String> tags = new ArrayList<String>();
        Random rand5 = new Random();
        int iloscTagow = rand5.nextInt(10 - 2) + 2;
        for (int i = 0; i < iloscTagow; i++) {
            Random rand6 = new Random();
            int indeks6 = rand6.nextInt(strTagi.length - 2) + 2;
            String s = strTagi[indeks6];
            if (!tags.contains(s)) {
                tags.add(new String(s));
            }
        }
        System.out.println(iloscTagow);
        for (int i = 0 ; i < tags.size(); i++) {
            System.out.println(i + "    " + tags.get(i));
        }
    }
    
}
