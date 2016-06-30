<%-- 
    Document   : 1MyUniversityAdmin
    Created on : Mar 9, 2016, 2:34:11 PM
    Author     : mohamed
--%>

<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% if (session.getAttribute("UserObject") != null) {
            MyPerson p = (MyPerson) session.getAttribute("UserObject");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <%= p.getFName()%><%=p.getLName()%></title>

    </head>
    <body>
              <div id="menue" >
            <ul>
                <li><a href="?page=AddUniversity">Add University with it's Admin</a></li>
                <li><a href="?page=ViewUniversity">View University Admins </a></li>
                <li><a href="?page=EditProfile">Edit Profile</a></li>
                <li><a href="?page=Chats">Chats</a></li>

            </ul>
        </div>
    </body>
    <% } else {
            response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

        }%>
</html>