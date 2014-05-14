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
            request.setCharacterEncoding("utf-8");
            String cn, uid, fn, ln, by, sprava = "";
            try {
                cn = request.getParameter("sel_user");
                uid = Pom.getIdFromComboName(cn);

                fn = DBconn.getUserFn(uid);
                ln = DBconn.getUserLn(uid);
                by = DBconn.getUserBy(uid);
        %>
        <h3><%= cn%></h3>
        <%-- Tvorba formulara s predvyplnenymi hodnotami pre zvoleneho uzivatela:--%>
        <div>
            <form action="changeUser.jsp" method="post">
                Jméno: <input type="text" value="<%= fn%>" name="first_name" /> <br/>
                Přijmění: <input type="text" value="<%= ln%>" name="last_name" /> <br/>
                Rok narození: <input type="text" value="<%= by%>" name="birth_year" /> <br/>
                                <input type="hidden" value="<%=uid%>" name="sel_uid"/>
                <input type="submit" value="SAVE" />
            </form>
            <br/>    
            <form action="deleteUser.jsp" method = "post">
                <input type="hidden" value="<%=uid%>" name="sel_uid" />
                <input type="submit" value="DELETE USER">
            </form>

        </div>

        <%
            } catch (SQLException e) {
                e.printStackTrace();
                sprava = "SOMETHING WENT WRONG WITH DB!";
            }
        %>
        <%-- SPATNE TLACITKO:--%>
        <div id=paticka>
            <form action = "userData.jsp" method = "post">
                <input type="submit" value="Zpět" />
            </form>
        </div>
        
        <%-- TEXT O USPECHU:--%>
        <h5 id = podmenu1>
            <%= sprava%>
        </h5>

    </body>
</html>
