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


        <%
            String sprava = "";
            try {
                if(DBconn.initDB()){
                    sprava = "DATABASE SUCCESSFULLY INITIALIZED!";
                } else {
                    sprava = "DATABASE HAS BEEN ALREADY INITIALIZED!!";
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                sprava = "SOMETHING WENT WRONG  WITH DB!";
            }
            
        %>
        <%-- SPATNE TLACITKO:--%>
        <div id="paticka">
            <form action = "registerUser.jsp" method = "post">
                <input type="submit" value="Zpět" />
            </form>
        </div>
        <%-- TEXT O USPECHU:--%>
        <h5 id="podmenu1">
            <%= sprava%>
        </h5>

    </body>
</html>
