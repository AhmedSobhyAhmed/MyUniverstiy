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
try{
            sendsms.init();
            sendsms.server = "http://127.0.0.1:8080/";
            sendsms.user = "root";
sendsms.password = "";
            
sendsms.phonenumber = "+0";
sendsms.text = "This is a test message";
sendsms.send();
                        out.println("<script type='text/javascript' > alert('Message  is Sending Sucesfull^_^ ');history.back();</script>");
}catch(Exception ex){

                    out.println("<script type='text/javascript' > alert('Failed Sending ^_^'"+ex.getMessage()+"' ');history.back();</script>");
}    %>    </body>
</html>
