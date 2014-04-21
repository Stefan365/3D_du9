
<%--
    Document   : DU9
    Created on : 20.04.2014
    Author     : Stefan Veres
--%>

<%@page import="users.UserBean"%>
<%@page import="pak.Pom"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="request" class="users.UserBean"/>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="newcss.css">
        <title>DU9</title>
    </head>

    <body>

        <h3>Registrace uživatele</h3>

        <form action="uzivatelia.jsp" method="post">
            Jméno: <input type="text" name="first_name" /> <br/>
            Přijmění: <input type="text" name="last_name" /> <br/>
            Rok narození: <input type="text" name="birth_year" /> <br/>
                            <input type="hidden" value="registrace" name="page" /> <br/>
            <input type="submit" value="Odeslat" />

        </form>
        
        <div id="patickaR">
            <form action="initDb.jsp" method="post">
                <input type="submit" value="INIT DB" />
            </form>
            <br/>
            <form action="uzivatelia.jsp" method="post">
                <input type="submit" value="NEXT" />
            </form>

        </div>
        
    </body>
</html>
