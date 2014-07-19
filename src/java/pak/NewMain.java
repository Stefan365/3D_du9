/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pak;

import java.sql.SQLException;

/**
 *
 * @author User
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        /*
        if (DBconn.existsT_USER_DU3()){
            System.out.println("ANO");
        } else {
            System.out.println("NIE");
        }*/
        
        //DBconn.dropTable("T_USER");
        //DBconn.dropTable("T_Q1");
        //DBconn.dropTable("T_Q2");
        DBconn.dropTable("T_QUERY");
        
        
    }
}
