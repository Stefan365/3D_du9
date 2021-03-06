package pak;


import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan Veres
 */
public class DBconn {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/IIVOS_java1?characterEncoding=utf8";
    static final String USER = "root";
    static final String PASSWORD = "";
    static final String DATABASE = "IIVOS_java1";
    
    //static final String DATABASE_URL = "jdbc:mysql://project.iivos.cz:9906/iivos3Dalfa?characterEncoding=utf8";
    //static final String USER = "veres";
    //static final String PASSWORD = "Stefan.Veres";
    //static final String DATABASE = "iivos3Dalfa";
    

    public static Connection connection;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBconn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBconn() {
    }


    //1.
    /**
     * Vytvori tabulku T_USER. Sluzi na ukladanie udajov o zaregistrocanych uzivateloch
     * 
     * @throws java.sql.SQLException
     */
    private static void createTableUser() throws SQLException {
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (Statement) connection.createStatement();
        }
        String sql = "CREATE TABLE T_USER_DU3"
            + " (id INTEGER NOT NULL AUTO_INCREMENT, " + " first_name VARCHAR(30) NOT NULL,"
            + " last_name VARCHAR(30) NOT NULL,  birth_year VARCHAR(30) NOT NULL,"
            
            + " PRIMARY KEY(id)) DEFAULT CHARSET=utf8";

        stmt.executeUpdate(sql);
        stmt.close();
    }

    //2. Inserting new values:
    /**
     * Vlozi do tab. T_USER novy riadok.
     * 
     * @param fn first name
     * @param ln last name
     * @param by bitrh year
     * @throws java.sql.SQLException
     */
    public static void insertValuesUser(String fn, String ln, String by) throws SQLException {

        String sql = "INSERT INTO T_USER_DU3 (first_name, last_name, birth_year) "
            + " VALUES (?, ?, ?)";
        PreparedStatement st;
        synchronized (DBconn.class) {        
            st = connection.prepareStatement(sql);
        }
        st.setString(1, fn);
        st.setString(2, ln);
        st.setString(3, by);
        
        st.executeUpdate();
        st.close();
    }

    //2.1
    /**
     * Upravi existujuce hodnoty v tab. T_USER.
     * 
     * @param uid user id
     * @param fn first name
     * @param ln last name
     * @param by birth year
     * @throws java.sql.SQLException
     */
    public static void updateValuesUser(String uid, String fn, String ln, String by) 
        throws SQLException {
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (Statement) connection.createStatement();
        }
        String sql = "UPDATE T_USER_DU3 SET first_name = '" + fn + "', "
            + "last_name= '" + ln + "', "
            + "birth_year= '" + by + "'"
            + " WHERE id = " + uid;
        
        stmt.executeUpdate(sql);

    }

    //3.
    /**
     * Drop any table in DB
     * 
     * @param tn DB table name
     * @throws java.sql.SQLException
     */
    public static void dropTable(String tn) throws SQLException {
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (Statement) connection.createStatement();
        }
        String sql = "DROP TABLE " + tn;

        if (!(stmt.executeUpdate(sql) == 1)) {
            throw new SQLException();
        }
        System.out.println("Droped table: " + tn + " in given database...");
    }

    
    //3.1  
    /**
     * gets users first name
     * 
     * @param uid id uzivatela
     * @return 
     * @throws java.sql.SQLException
     */
    public static String getUserFn(String uid) throws SQLException {
        
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (Statement) connection.createStatement();
        }
        String sql = "SELECT first_name FROM T_USER_DU3 WHERE id = " + uid;
        
        ResultSet rs = stmt.executeQuery(sql);
        String fn = "";
        while (rs.next()) {
            fn = rs.getString("first_name");
        }
        stmt.close();
        
        return fn;
    }
    
    //3.2
    /**
     * gets users last name
     * 
     * @param uid id uzivatela
     * @return 
     * @throws java.sql.SQLException
     */
    public static String getUserLn(String uid) throws SQLException {
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (Statement) connection.createStatement();
        }
        String sql = "SELECT last_name FROM T_USER_DU3 WHERE id = " + uid;

        ResultSet rs = stmt.executeQuery(sql);
        String ln = "";
        while (rs.next()) {
            ln = rs.getString("last_name");
        }
        stmt.close();
        return ln;
    }

    //3.3
    /**
     * gets users birth year
     * 
     * @param uid id uzivatela
     * @return 
     * @throws java.sql.SQLException
     */
    public static String getUserBy(String uid) throws SQLException {
        Statement stmt;
        synchronized (DBconn.class) {        
            stmt = (Statement) connection.createStatement();
        }
        String sql = "SELECT birth_year FROM T_USER_DU3 WHERE id = " + uid;

        ResultSet rs = stmt.executeQuery(sql);
        String by = "";
        while (rs.next()) {
            by = rs.getString("birth_year");
        }
        stmt.close();
        return by;
    }
    
    //3.4
    /**
     * gets nr of users in T_USER
     * 
     * @return 
     * @throws java.sql.SQLException
     */
    public static int getNrOfUsers() throws SQLException {
        
        Statement stmt;
        synchronized (DBconn.class) {        
            stmt = (Statement) connection.createStatement();
        }
        String sql = "SELECT COUNT(*) AS NUM FROM T_USER_DU3";
        int nr;
        try (ResultSet rs = stmt.executeQuery(sql)) {
            nr = 0;
            while (rs.next()) {
                nr = (Integer)rs.getInt("NUM");
            }
        }
        stmt.close();
        return nr;
    }
    
    //4.
    /**
     * Zjistuje jestli existuje DB tabulka T_USER_DU3, 
     *
     * @return ano/ne pro existenci T_USER_DU3 v databazi.
     */
    public static boolean existsT_USER_DU3() {
        java.sql.Statement stmt = null;
        try {
            synchronized (DBconn.class) {
                stmt = (Statement) connection.createStatement();
            }
            int count = 0;
            String sql = "SELECT COUNT(*) FROM information_schema.tables WHERE " 
                + "table_schema = '" + DBconn.DATABASE + "'" + 
                " AND table_name = 'T_USER_DU3'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
            }
            
            if (count == 1){
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //5. 
    /**
     * initialize Database. tj. vytvori prislusne DB tabulky.
     * a naplni je nevyhnutnymi udaji.
     * 
     * @return 
     * @throws java.sql.SQLException
     */
    public static synchronized boolean initDB() throws SQLException {
        if (!DBconn.existsT_USER_DU3()){
            DBconn.createTableUser();
            DBconn.insertValuesUser("Stefan", "Veres", "1979");
            return true;
        } else {
            return false;
        }
    }
}
