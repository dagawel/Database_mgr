/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package documentdatabase;

import static documentdatabase.MongoDB.tabelaStringow;
import static documentdatabase.MongoDB.tabelaIntow;
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
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
    }
    
    public String zapiszKlientRekordy (String name) throws ClassNotFoundException, SQLException {
        String tekst = "";
        if (connection != null) {         
            s.execute("INSERT INTO DAGMARA.Authors (Name) VALUES ('" + name + "')");   
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
    }
    
    public String zapiszTagiRekordy (String text) throws ClassNotFoundException, SQLException {
        String tekst = "";
        if (connection != null) {         
            s.execute("INSERT INTO DAGMARA.Tags (Text) VALUES ('" + text + "')");   
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
    }
    
    public String zapiszPostyRekordy (String title, String url, int author, int like) throws ClassNotFoundException, SQLException {
        String tekst = "";
        if (connection != null) {         
            s.execute("INSERT INTO DAGMARA.Posts (Title, Url, Author_id, Likes) VALUES ('" + title + "', '" + url + "', " + author + ", " + like + ")");   
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
    }
    
    public ArrayList<Integer> szukajAutorow () throws SQLException {
        ArrayList<Integer> array = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 11; i++) {
            int j = rand.nextInt(11 - 2) + 2;
            ResultSet rs = s.executeQuery("SELECT ID FROM AUTHORS WHERE ID = " + j);
            rs.next();
            array.add(rs.getInt(1));
        }
        return array;
    }
    
    public ArrayList<Integer> szukajPost () throws SQLException {
        ArrayList<Integer> array = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            //a = r.nextInt(gorna-dolna+1)+dolna;
            int p = rand.nextInt(50 - 1+1) + 1;
            ResultSet rs = s.executeQuery("SELECT ID FROM POSTS WHERE ID = " + p);
            rs.next();
            array.add(rs.getInt(1));          
        }
        return array;
    }
    
    public ArrayList<Para> szukajTagIPost () throws SQLException {
        ArrayList<Para> array = new ArrayList<Para>();
        Random rand = new Random();
        Statement s2 = connection.createStatement();
        for (int i = 0; i < 20; i++) {
            //a = r.nextInt(gorna-dolna+1)+dolna;
            int j = rand.nextInt(73 - 61+1) + 61;
            ResultSet rs = s.executeQuery("SELECT ID FROM TAGS WHERE ID = " + j);
            int p = rand.nextInt(50 - 1+1) + 1;
            ResultSet rs2 = s2.executeQuery("SELECT ID FROM POSTS WHERE ID = " + p);
            rs.next();
            rs2.next();
            Para para = new Para(rs2.getInt(1), rs.getInt(1));
            if (!array.contains(para)) {
                array.add(para);
            }
            
        }
        s2.close();
        return array;
    }
    
    public String zapiszPostyITagiRekordy (int post, int tag) throws ClassNotFoundException, SQLException {
        String tekst = "";
        if (connection != null) {         
            s.execute("INSERT INTO DAGMARA.Post_tags (Post_id, Tag_id) VALUES (" + post + ", " + tag + ")");   
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
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
        int rozmiarBazy = 155;
        ArrayList<String> strTagi = tabelaStringow(20, 10);  
        ArrayList<Integer> like = tabelaIntow(200, 1000);  
        ArrayList<String> strUrl = tabelaStringow(200, 255); 
        ArrayList<String> strTytul = tabelaStringow(200, 25); 
        ArrayList<Integer> size = tabelaIntow(200, 10000); 
        ArrayList<String> strType = tabelaStringow(200, 25); 
        ArrayList<String> strComment = tabelaStringow(200, 255);
        ArrayList<String> strName = tabelaStringow(200, 25); 
        
        Random rand5 = new Random();
//        for (int i = 0; i < rozmiarBazy; i++) {
//            int indeksName = rand5.nextInt(strName.size() - 2) + 2;
//            orcl.zapiszKlientRekordy(strName.get(indeksName));
//        }
        //////////////////////////////////////////////////////////////////////////
//        ArrayList<String> pom = new ArrayList<String>();
//        for (int i = 0; i < rozmiarBazy; i++) {
//            int indeksTag = rand5.nextInt(strTagi.size() - 2) + 2;
//            String s = strTagi.get(indeksTag);
//            if (!pom.contains(s)) {
//                pom.add(s);
//            }
//        }
//        for (int i = 0; i < pom.size(); i++) {
//            orcl.zapiszTagiRekordy(strName.get(i));
//        }
        //////////////////////////////////////////////////////////////////////////
//        ArrayList<Integer> authors = new ArrayList<Integer>();
//        authors = orcl.szukajAutorow();
//        for (int i = 0; i < rozmiarBazy; i++) {
//            int indeksTitle = rand5.nextInt(strTytul.size() - 2) + 2;
//            String title = strTytul.get(indeksTitle);
//            
//            int indeksUrl = rand5.nextInt(strUrl.size() - 2) + 2;
//            String url = strUrl.get(indeksUrl);
//            System.out.println(authors.size());
//            int indeksAutor = rand5.nextInt(authors.size() - 1) + 1;
//            int autor = authors.get(indeksAutor);
//            
//            int indeksLike = rand5.nextInt(like.size() - 2) + 2;
//            int lajk = like.get(indeksLike);
//            
//            orcl.zapiszPostyRekordy(title, url, autor, lajk);
//        }
        /////////////////////////////////////////////////////////////////////////
//        ArrayList<Para> posttags = new ArrayList<Para>();
//        posttags = orcl.szukajTagIPost();
//        for (int i = 0; i < rozmiarBazy; i++) {
//            orcl.zapiszPostyITagiRekordy(posttags.get(i).post, posttags.get(i).tag);
//        }
        //////////////////////////////////////////////////////////////////////////
//        ArrayList<Integer> posty = new ArrayList<Integer>();
//        posty = orcl.szukajPost();
//        for (int i = 0; i < rozmiarBazy; i++) {
//            int indeksUrl = rand5.nextInt(strUrl.size() - 2) + 2;
//            String url = strUrl.get(indeksUrl);
//            
//            int indeksType = rand5.nextInt(strType.size() - 2) + 2;
//            String type = strType.get(indeksType);
//            
//            int indeksSize = rand5.nextInt(size.size() - 2) + 2;
//            int sizee = size.get(indeksSize);
//            
//            int indekspost = rand5.nextInt(posty.size() - 1) + 1;
//            int post = posty.get(indekspost);
//            
//            orcl.zapiszImageRekordy(post, url, type, sizee);
//        }
        //////////////////////////////////////////////////////////////////////////
        ArrayList<Integer> posty = new ArrayList<Integer>();
        posty = orcl.szukajPost();
        ArrayList<Integer> autorzy = new ArrayList<Integer>();
        autorzy = orcl.szukajAutorow();
        for (int i = 0; i < rozmiarBazy; i++) {
            int indekspost = rand5.nextInt(posty.size() - 1) + 1;
            int post = posty.get(indekspost);
            
            int indeksUser = rand5.nextInt(autorzy.size() - 1) + 1;
            int user = autorzy.get(indeksUser);
            
            int indeksText = rand5.nextInt(strComment.size() - 2) + 2;
            String comment = strComment.get(indeksText);
            
            orcl.zapiszCommentRekordy(post, user, comment);
        }
        
        
        orcl.s.close();
        orcl.connection.close();
    }

    public String zapiszImageRekordy(int post, String url, String type, int size) throws SQLException {
        String tekst = "";
        if (connection != null) {         
            s.execute("INSERT INTO DAGMARA.Images (Post_id, Url, Type_, Size_) VALUES (" + post + ", '" + url + "', '" + type + "', " + size + ")");   
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
    }

    public String zapiszCommentRekordy(int post, int user, String comment) throws SQLException {
        String tekst = "";
        if (connection != null) {         
            s.execute("INSERT INTO DAGMARA.Comments (Post_id, User_id, Text) VALUES (" + post + ", " + user + ", '" + comment + "')");   
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return tekst;
    }
    
}

//class Para {
//    int tag;
//    int post;
//    
//    public Para (int post, int tag) {
//        this.tag = tag;
//        this.post = post;
//    } 
//    
//    public boolean equals(Object obj) {
//        boolean pom = false;
//        if (obj == this) {
//            pom = true;
//        }
//        if (!(obj instanceof Para)) {
//            pom = false;
//        }
//        Para other = (Para) obj;
//        if (other.tag == this.tag && other.post == this.post) {
//            pom = true;
//        }
//        return pom;
//    }
//}
