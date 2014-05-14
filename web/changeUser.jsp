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
            request.setCharacterEncoding("utf-8");
            
            String sel_uid = request.getParameter("sel_uid");
            String fn = request.getParameter("first_name");
            String ln = request.getParameter("last_name");
            try {
                if (("".equals(sel_uid)) || (sel_uid == null)){
                    //do nothing
                } else {
                    Pom.setSelUser(sel_uid + ", " + fn + " " + ln);
                    Pom.updateDbUserApp(sel_uid, request);                    
                    //ochrana pred F5. (opakovane zapisovanie uz raz zadanych udajov)
                    String go  ="changeUser.jsp";        
                    response.sendRedirect(response.encodeRedirectURL(go));
                }

                %>
                <h3>User: <%=Pom.getSelUser()%></h3>
                <h5 id="menu"> User, data successfully updated! </h5>
                <%
                
            } catch (SQLException e) {
                e.printStackTrace();
                sprava = "SOMETHING WENT WRONG WITH DB!";
            }
        %>
        <%-- SPATNE TLACITKO:--%>
        <div id="paticka">
            <form action = "uzivatelia.jsp" method = "post">
                <input type="submit" value="Back" />
            </form>
        </div>
        <%-- TEXT O USPECHU:--%>
        <h5 id="podmenu1">
            <%= sprava%>
        </h5>
    </body>
</html>
