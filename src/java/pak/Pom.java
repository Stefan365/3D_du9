package pak;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Stefan
 */
public class Pom {

    //kvoli odfiltrovaniu problemu s F5 na strankach deleteUser.jsp a changeUser.jsp
    private static String selUser;

    public static String getSelUser() {
        return selUser;
    }

    public static synchronized void setSelUser(String user) {
        selUser = user;
    }

    //1.
    /**
     * Vlozi novy riadok do DB tabulky.
     *
     * @param request pozadavek od klienta.
     * @throws java.sql.SQLException
     *
     */
    public static void insertDbUser(HttpServletRequest request) throws SQLException {
        String fn, ln, by;

        fn = request.getParameter("first_name");
        ln = request.getParameter("last_name");
        by = request.getParameter("birth_year");

        //zapis hodnoty do DB:
        DBconn.insertValuesUser(fn, ln, by);
    }

    //2.
    /**
     * Zapise atributy do DB tabulky.
     *
     * @param uid
     * @param request pozadavek od klienta.
     * @throws java.sql.SQLException
     *
     */
    public static void updateDbUserApp(String uid, HttpServletRequest request) throws SQLException {

        String fn, ln, by;

        fn = request.getParameter("first_name");
        ln = request.getParameter("last_name");
        by = request.getParameter("birth_year");

        //zapis do DB:
        DBconn.updateValuesUser(uid, fn, ln, by);
    }

    //3.
    /**
     * Ziska zoznam id vsetkych uzivatelov.
     *
     * @return zoznam id vsetkych uzivatelov.
     * @throws java.sql.SQLException
     *
     */
    public static List<Integer> getAllUsersId() throws SQLException {
        int uid;
        List<Integer> listIds = new ArrayList<>();
        String query = "SELECT id from T_USER_DU3";
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (DBconn.connection).createStatement();
        }
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            uid = rs.getInt("id");
            listIds.add(uid);
        }
        stmt.close();

        return listIds;
    }

    //4.
    /**
     * Vrati zoznam mien + id vsetkych zaregistrovanych userov v systeme, tj.
     * len tych ktori niesu Admini.(aby sa nemohli navzajom vymazat, resp.
     * upravovat si udaje)
     *
     * @return zoznam mien + id na tvorbu combo boxu.
     * @throws java.sql.SQLException
     *
     */
    public static List<String> getComboNames() throws SQLException {

        List<String> comboNames = new ArrayList();
        String uid, fn, ln, cn;
        String query = "SELECT id, first_name, last_name from T_USER_DU3";
        Statement stmt;
        synchronized (DBconn.class) {
            stmt = (DBconn.connection).createStatement();
        }
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            uid = "" + rs.getInt("id");
            fn = rs.getString("first_name");
            ln = rs.getString("last_name");
            cn = uid + ", " + fn + " " + ln;
            comboNames.add(cn);
        }
        stmt.close();

        return comboNames;
    }

    //5.
    /**
     * Ziska user id z nazvu combo polozky.
     *
     * @param cn combo item name
     * @return string of user id.
     */
    public static String getIdFromComboName(String cn) {

        String[] zoz = cn.split(",");
        int uid;

        try {
            uid = Integer.parseInt(zoz[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "";
        }
        return "" + uid;
    }

    //6.
    /**
     * Vymaze z Databazy vsetky zaznamy usera s dany id.
     *
     * @param uid user id
     * @throws java.sql.SQLException
     */
    public static void deleteDbId(String uid) throws SQLException {

        String sql = "DELETE FROM T_USER_DU3 WHERE id = " + uid;

        Statement stmt;

        //deleting in T_USER table:
        synchronized (DBconn.class) {
            stmt = (DBconn.connection).createStatement();
        }
        stmt.executeUpdate(sql);
        
    }    
}
