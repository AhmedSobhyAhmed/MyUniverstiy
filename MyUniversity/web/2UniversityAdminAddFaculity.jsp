<%-- 
    Document   : 3FaculityAdminViewSubjects
    Created on : Mar 14, 2016, 7:39:49 AM
    Author     : mohamed
--%>

<%@page import="models.ModelOfUniversityAdmin"%>
<%@page import="java.util.Date"%>
<%@page import="models.ModelOfFaculityAdmin"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (p.getUserType() == 2) { %>
        <div id="rightcontentRigs">
            <center>

                <form action="AddFaculty" method="POST">
                    <h1> <font style="color: #905;">Faculty Name </font></h1> <br/>     <input type="text" name="FacultyName" value="" placeholder="Faculty Name" />
                                     <br/>

                   <h1> <font style="color: #905;">System Type </font></h1> <br/> 
                   <select name="SystemType">
                       <option value="CreditHours">Credit Hours</option>
                       <option value="Normal">Normal</option>
                    </select>
                   <br/>
                    <input  style="padding-left: 5px;
                            background-color: #333; border: 1px;  
                            color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                            type="submit"  name="ADD" value="ADD">                 </form>
            </center>
        </div>
        <%
            }//end of if usertype is faculity admin or not
            else {
                out.println("<br/><br/><br/><br/><br/>");
                out.println("<center><font color='blue'><h1>You shouldn't be here ^_^ </h1></font></center>");

            }

        %>
    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }
%>