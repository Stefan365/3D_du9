<%-- 
    Document   : uzivatelia
    Created on : 08-Mar-2014, 11:27:55
    Author     : User
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="pak.DBconn"%>
<%@page import="javax.swing.JOptionPane"%>
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



        <%
            String sprava = "";
            String pag = (String) request.getParameter("page");
            try {
                if ("registrace".equals(pag)) {
                            user.setFirstname(request.getParameter("first_name"));
                            user.setLastname(request.getParameter("last_name"));
                            user.setBirthyear(request.getParameter("birth_year"));

                            %>         <%--<jsp:setProperty name="user" property="*" />--%> <%
                        if (user.hasValidData()) {
                            Pom.insertDbUser(request);
                            //ochrana pred F5. (opakovane zapisovanie uz raz zadanych udajov)
                            String go  ="uzivatelia.jsp";        
                            response.sendRedirect(response.encodeRedirectURL(go));
                            %> 
                            
                            <%
                        } else {
                            %>
                            
                            <h4 id=podpaticka> <font color = "red"> Zadaj prosím všetky políčka!</font></h4>
                            
                            <%
                        }
                }
        %>
        <table border="4">
            <td> <%= "* ID *"%> </td> <td> <%= "*  JMÉNO  *"%> </td> 
            <td> <%="*  PŘIJMĚNÍ  *"%> </td> <td> <%= "*  ROK NAROZENÍ  *"%> </td>   

            <%
                //Konstrukcia tabulky:
                int cols = 4;
                List<Integer> listIds = Pom.getAllUsersId();
                int rows = listIds.size();
                String fn, ln, by, uid;

                Iterator itr = listIds.iterator();
                while (itr.hasNext()) {
                    uid = "" + (Integer) itr.next();
                    fn = DBconn.getUserFn(uid);
                    ln = DBconn.getUserLn(uid);
                    by = DBconn.getUserBy(uid);
            %>

            <tr>              
                <% for (int c = 0; c < cols; c++) {
                %>
                <td> <%= uid%> </td> <td> <%= fn%> </td> <td> <%= ln%> </td> <td> <%= by%> </td>
                <%
                        break;
                    }
                %>
            </tr>
            <%
                }%>
            </table>    
            
                            
            <%

                } catch (SQLException e) {
                    e.printStackTrace();
                    sprava = "SOMETHING WENT WRONG WITH DB!";
                }

            %>
        
           


        <%-- ODOSIELACIE TLACITKO:--%>
        <div id="paticka">
            <form action = "registerUser.jsp" method = "post">
                <input type="submit" value="Zpět" />
            </form>
        </div>

        <%-- TLACITKO NA STRANKU SO ZMENOU USER DAT:--%>
        <div id="patickaR">
            <form action = "userData.jsp" method = "post">
                <input type="submit" value="USER DATA" />
            </form>
        </div>

    </body>
</html>
