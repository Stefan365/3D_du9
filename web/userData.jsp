<%-- 
    Document   : uzivatelia
    Created on : 20-Apr-2014
    Author     : Stefan Veres
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="pak.DBconn"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="pak.Pom"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="newcss.css">
        <title>DU9</title>
    </head>
    <body>
        <h3>Select user:</h3>
        <%-- Tvorba comboboxu s menami vsetkych zaregistrovanych uzivatelov:--%>
        <div>
            <form action="userForm.jsp" method = "post">
                <select name="sel_user" size="1">
                    <%
                        String cn, sprava = "";
                        try{
                            List<String> listCn = Pom.getComboNames();
                            Iterator itr = listCn.iterator();
                            while (itr.hasNext()) {
                                cn = (String) itr.next();
                                %>
                                <option value="<%= cn%>"><%= cn%></option>
                                <%
                            }
                        } catch(SQLException e) {
                            sprava = "SOMETHING WENT WRONG WITH DB!";
                        }
                    %>
                </select>
                <input type="submit" value="SELECT">
            </form>
        </div>
                
        <%-- SPATNE TLACITKO:--%>
        <div id="paticka">
            <form action = "uzivatelia.jsp" method = "post">
                <input type="submit" value="Zpět" />
            </form>
        </div>
        <%-- TEXT O USPECHU:--%>
        <h5 id="podmenu1">
            <%= sprava%>
        </h5>

    </body>
</html>
