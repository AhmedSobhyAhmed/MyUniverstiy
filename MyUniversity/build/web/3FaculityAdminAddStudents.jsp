<%-- 
    Document   : 3FaculityAdminAddStudents
    Created on : Mar 14, 2016, 7:32:33 AM
    Author     : mohamed
--%>

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
        <% if (p.getUserType() == 3) { %>

        <%!  ResultSet Levels = null;
            ResultSet Departments = null;
        %>
        <div id="rightcontentRigs">

            <form action="UploadExcelStudentsFile" method="post" enctype="multipart/form-data">
                Select File:<input type="file" name="fname"/><br/>
                <input type="image" src="MainUpload.png"/><br/>
            </form>
            <!--
            <ul>
                <li>Make a file with type (.xlsx)<br></br>
                    Note:you can make file with (.odt) but save it as (.xlsx)
                </li> 
                <li>
                    Columns order is:<br></br>
                    (`Code`,  `FName`, `LName`,
                    `Mail`, `Gender`,`UserTypeID`, `LevelID`)
                </li>                        

                <li>
                    User Type:<br></br>

                    6 for Students
                    5 for Professor Assistants
                    4 for Professors
                </li>                        
                <li>Ex:<br></br><img alt="Example" src="StudentsExcelJSP2.png" width="500" height="100"/></li>
            </ul>
            -->
            <table border="1">
                <thead>
                    <tr>
                        <th></th>
                        <th>Columns order </th>
                        <th>User Type</th>
                        <th>User Department</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Make a file with type (.xlsx)<br></br>
                            Note:you can make file with (.odt) but save it as (.xlsx)
                        </td>
                        <td>   
                            (`Code`,  `FName`, `LName`,
                            `Mail`, `Gender`,`UserTypeID`, `LevelID`)</td>
                        <td>   

                            6 for Students
                            5 for Professor Assistants
                            4 for Professors</td>
                        <td>   

                            Department will be (General) as a Default value
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <img alt="Example" src="StudentsExcelJSP2.png"/>

                        </td>

                    </tr>
                </tbody>
            </table>
            <hr/>
            <font style="color: #02b3e4;font-size: x-large;">Using Form:</font>
            <br/>
            <form method="POST" action="RegisterUser">
                <font style="color: #905;font-size: large;">Code:</font>   <input type="text" name="Code"  required="" value="" onkeypress="return isNumberKey(event)" /><br/>
                <font style="color: #905;font-size: large;">Mail:</font>  <input type="text" name="Mail" required="" value="" /><br/>
                <font style="color: #905;font-size: large;">First Name:</font>  <input type="text" name="FName" required="" value="" /><br/>
                <font style="color: #905;font-size: large;">Last Name:</font>  <input type="text" name="LName" required="" value="" /><br/>

                <font style="color: #905;font-size: large;">Password:</font>  <input type="password" required=""  name="Password" value="" /><br/>
                <font style="color: #905;font-size: large;">Confirm Password:</font>  <input type="password" required="" name="ConfirmPassword" value="" /><br/>
                <font style="color: #905;font-size: large;">Type:</font>  
                <select name="Type" required="">
                    <option value="Professor">Professor</option>
                    <option value="ProfesorAssistant">Professor Assistant</option>
                    <option value="Student">Student</option>

                </select><br/>
                <font style="color: #905;font-size: large;">Gender:</font>  
                <select name="Gender" required="">
                    <option>Male</option>
                    <option> Female</option>

                </select>
                <br/>
                <font style="color: #905;font-size: large;">Level:</font><select name="Level" required="">  <%  try {
                        Levels = p.ViewLevelNames();
                        while (Levels.next()) {
                    %>                                              
                    <option value="<%=Levels.getInt("userlevel.ID")%>"><%=Levels.getString("userlevel.Level")%></option>

                    <% } %> 

                    <% } catch (Exception ex) {

                            out.println("Levels Error:" + ex.getMessage() + "in OpenCourses.jsp");
                        }%>
                </select>
                <br/> 
                <font style="color: #905;font-size: large;" >Department:</font>
                <select name="Department" required="">
                    <%  try {
                            Departments = p.ViewDepartmentNames();
                            while (Departments.next()) {
                    %>
                    <option value="<%=Departments.getInt("user_department.ID")%>"><%=Departments.getString("user_department.DepartmentName")%></option>

                    <%}%> 
                    <% } catch (Exception ex) {

                            out.println("Departments Error:" + ex.getMessage() + "in OpenCourses.jsp");
                        }%>
                </select>
                <br/><input  style="padding-left: 5px;
                             background-color: #333; border: 1px;  
                             color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                             type="submit"  name="ADD" value="ADD">  
            </form>
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