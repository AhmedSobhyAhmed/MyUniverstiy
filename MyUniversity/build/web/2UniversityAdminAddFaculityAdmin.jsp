<%-- 
    Document   : 3FaculityAdminAddStudents
    Created on : Mar 14, 2016, 7:32:33 AM
    Author     : mohamed
--%>

<%@page import="models.ModelOfUniversityAdmin"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
                                ModelOfUniversityAdmin modelOfUniversityAdmin = new ModelOfUniversityAdmin();

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            function isNumberKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
        </script>
    </head>
    <body>
        <div id="rightcontentRigs">
            <center>
                <% if (p.getUserType() == 2) { %>


                <br/>
                <form method="POST" action="RegisterFaculityAdmin">
                    <font style="color: #905;font-size: large;">Code:</font> <br/>  <input type="text" name="Code"  required="" value="" placeholder="Code ex:20120399" onkeypress="return isNumberKey(event)" /><br/>
                    <font style="color: #905;font-size: large;">Mail:</font> <br/> <input type="text" name="Mail" required="" value="" placeholder="Mail ex:ahmed33@yahoo.com" /><br/>
                    <font style="color: #905;font-size: large;">First Name:</font>  <br/><input type="text" name="FName" required="" value="" placeholder="Ahmed" /><br/>
                    <font style="color: #905;font-size: large;">Last Name:</font> <br/> <input type="text" name="LName" required="" value="" placeholder="Mohamed" /><br/>

                    <font style="color: #905;font-size: large;">Password:</font>  <br/><input type="password" required=""  name="Password" value="" placeholder="Password" /><br/>
                    <font style="color: #905;font-size: large;">Confirm Password:</font> <br/> <input type="password" required="" name="ConfirmPassword" value="" placeholder="Confirm Pasword" /><br/>

                    <font style="color: #905;font-size: large;">Gender:</font>  <br/>
                    <select name="Gender" required="">
                        <option>Male</option>
                        <option>Female</option>

                    </select>

                  <br/>  <font style="color: #905;font-size: large;">Faculty Name:</font>  <br/>
                    <select name="FacultyID">  <%  try {
                           ResultSet MyFaculties = modelOfUniversityAdmin.ViewMyFacylities(p.getUniversityID());
                        %>

                        <% while (MyFaculties.next()) {
                        %>                                              
                        <option value="<%=MyFaculties.getInt("ID")%>"><%=MyFaculties.getString("FacultyName")%>
                        </option>

                        <% } %> 

                        <% } catch (Exception ex) {

                                out.println("MyFaculties Error:" + ex.getMessage() + "in Add Faculty Admins.jsp");
                            }%>
                    </select>

                    <br/><input  style="padding-left: 5px;
                                 background-color: #333; border: 1px;  
                                 color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                                 type="submit"  name="ADD" value="ADD">  
                </form>

                <%
                    }//end of if usertype is faculity admin or not
                    else {
                        out.println("<br/><br/><br/><br/><br/>");
                        out.println("<center><font color='blue'><h1>You shouldn't be here ^_^ </h1></font></center>");

                    }

                %>
            </center>
        </div>
    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }
%>