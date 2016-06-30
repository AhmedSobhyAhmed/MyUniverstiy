<%-- 
    Document   : trySensSMS
    Created on : Jun 29, 2016, 12:56:25 AM
    Author     : Mohamed
--%>

<%@page import="models.sendsms"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            sendsms.init();
            sendsms.server = "http://127.0.0.1:8080/";
            sendsms.user = "test";
sendsms.password = "test";
            
sendsms.phonenumber = "+02 01112858168";
sendsms.text = "This is a test message";
sendsms.send();
        %>    </body>
</html>
