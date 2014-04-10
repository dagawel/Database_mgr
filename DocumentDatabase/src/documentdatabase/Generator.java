package documentdatabase;

import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
 

public class Generator {
    Connection connection;
    Statement s;
    
    public Generator() throws ClassNotFoundException, SQLException {
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
    
    public int szukajAutora (String name) throws SQLException {
        String tekst = "";
        int ret = 0;
        if (connection != null) {         
            ResultSet rs = s.executeQuery("SELECT ID FROM DAGMARA.AUTHORS WHERE NAME = '" + name + "'");   
            rs.next();
            ret = rs.getInt(1);
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return ret;
    }
    
    public int szukajTagu (String text) throws SQLException {
        String tekst = "";
        int ret = 0;
        if (connection != null) {         
            ResultSet rs = s.executeQuery("SELECT ID FROM DAGMARA.TAGS WHERE TEXT = '" + text + "'");   
            rs.next();
            ret = rs.getInt(1);
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return ret;
    }
    
    public int szukajMaxPost () throws SQLException {
        String tekst = "";
        int ret = 0;
        if (connection != null) {         
            ResultSet rs = s.executeQuery("SELECT MAX(ID) FROM DAGMARA.POSTS");   
            rs.next();
            ret = rs.getInt(1);
            tekst = "Dodano do bazy danych!";
        } 
        else {
            tekst = "Nie moge się połączyć!";
        }
        return ret;
    }
    
    public boolean autorIstnieje (String name) throws SQLException {
        boolean pom = true;
        int ret = 0; 
        ResultSet rs = s.executeQuery("SELECT COUNT(ID) FROM DAGMARA.AUTHORS WHERE NAME = '" + name + "'");   
        rs.next();
        ret = rs.getInt(1);
        if (ret > 0) {
            pom = true;
        }
        else {
            pom = false;
        }
        return pom;
    }
    
    public boolean tagIstnieje (String text) throws SQLException {
        boolean pom = true;
        int ret = 0; 
        ResultSet rs = s.executeQuery("SELECT COUNT(ID) FROM DAGMARA.TAGS WHERE TEXT = '" + text + "'");   
        rs.next();
        ret = rs.getInt(1);
        if (ret > 0) {
            pom = true;
        }
        else {
            pom = false;
        }
        return pom;
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
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Generator orcl = new Generator();
        Random rand = new Random(); 
        int rozmiarBazy = 1000;
        ArrayList<String> strTagi = tabelaStringow(20, 10);  
        ArrayList<Integer> like = tabelaIntow(200, 1000);  
        ArrayList<String> strUrl = tabelaStringow(200, 255); 
        ArrayList<String> strTytul = tabelaStringow(200, 25); 
        ArrayList<Integer> size = tabelaIntow(200, 10000); 
        ArrayList<String> strType = tabelaStringow(200, 25); 
        ArrayList<String> strComment = tabelaStringow(200, 255);
        ArrayList<String> strName = tabelaStringow(200, 25); 
        
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
                String tytul = strTytul.get(indeks);
                BasicDBObject doc = new BasicDBObject("title", tytul);

                int indeks2 = rand.nextInt(strUrl.size() - 2) + 2;
                String urlPost = strUrl.get(indeks2);
                doc.append("url", urlPost);
                
                int indeks3 = rand.nextInt(strName.size() - 2) + 2;
                String autor = strName.get(indeks3);
                doc.append("author", autor);
                
                if (!orcl.autorIstnieje(autor)) {
                    orcl.zapiszKlientRekordy(autor);
                }         
                int idAutoraZPostu = orcl.szukajAutora(autor);
                
                int indeks4 = rand.nextInt(like.size() - 2) + 2;
                int lajki = like.get(indeks4); 
                doc.append("likes", lajki);
                
                orcl.zapiszPostyRekordy(tytul, urlPost, idAutoraZPostu, lajki);
                
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
                
                for (int i = 0; i < tags.size(); i++) {
                    String tag = tags.get(i);
                    if (!orcl.tagIstnieje(tag)) {
                        orcl.zapiszTagiRekordy(tag);
                    }
                }
                int post = orcl.szukajMaxPost();
                for (int i = 0; i < tags.size(); i++) {
                    int ident = orcl.szukajTagu(tags.get(i));
                    orcl.zapiszPostyITagiRekordy(post, ident);
                }
                
                
                int indeks7 = rand.nextInt(strUrl.size() - 2) + 2;
                int indeks8 = rand.nextInt(strType.size() - 2) + 2;
                int indeks9 = rand.nextInt(size.size() - 2) + 2;
                String url = strUrl.get(indeks7);
                String type = strType.get(indeks8);
                int sizeImg = size.get(indeks9);
                doc.append("image", new BasicDBObject("url", url).
                                                   append("type", type).
                                                   append("size", sizeImg));

                orcl.zapiszImageRekordy(post, url, type, sizeImg);
                
                List<BasicDBObject> comments = new ArrayList<>();
                int iloscCommentow = rand.nextInt(20 - 2) + 2;
                for (int i = 0; i < iloscCommentow; i++) {
                    int indeks11 = rand.nextInt(strName.size() - 2) + 2;
                    int indeks12 = rand.nextInt(strComment.size() - 2) + 2;
                    String name = strName.get(indeks11);
                    String comment = strComment.get(indeks12);
                    BasicDBObject doc1 = new BasicDBObject("user", name);
                    doc1.append("text", comment);
                    comments.add(doc1);
                    
                    if (!orcl.autorIstnieje(name)) {
                        orcl.zapiszKlientRekordy(name);
                    }
                    int idautora = orcl.szukajAutora(name);
                    orcl.zapiszCommentRekordy(post, idautora, comment);
                }
                doc.put("comments", comments);

                table.insert(doc);
                a++;
            }

            DBCursor cursor = table.find();
            int i = 1;
            while (cursor.hasNext()) { 
                System.out.println("Inserted Document: " + i); 
                System.out.println(cursor.next()); 
                i++;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }        
    }
}

class Para {
    int tag;
    int post;
    
    public Para (int post, int tag) {
        this.tag = tag;
        this.post = post;
    } 
    
    public boolean equals(Object obj) {
        boolean pom = false;
        if (obj == this) {
            pom = true;
        }
        if (!(obj instanceof Para)) {
            pom = false;
        }
        Para other = (Para) obj;
        if (other.tag == this.tag && other.post == this.post) {
            pom = true;
        }
        return pom;
    }
}