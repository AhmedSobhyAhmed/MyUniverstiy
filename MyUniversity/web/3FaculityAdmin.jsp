<%-- 
    Document   : 3FaculityAdmin
    Created on : Mar 9, 2016, 2:36:42 PM
    Author     : mohamed
--%>

<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="com.itextpdf.text.DocumentException"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="com.itextpdf.text.Document"%>
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

      
    </body>
    <% } else {
             response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

        }%>
</html>