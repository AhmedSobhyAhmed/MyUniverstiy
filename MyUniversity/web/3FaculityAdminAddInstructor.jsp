<%-- 
    Document   : 3FaculityAdminAddInstructor
    Created on : Jun 19, 2016, 11:37:39 AM
    Author     : Mohamed
--%>

<%@page import="models.MyPerson"%>
<%@page import="models.ModelOfFaculityAdmin"%>
<%@page import="java.sql.ResultSet"%>
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
        <% if (p.getUserType() == 3) { %>
        <%!  ResultSet Professors = null;
            ResultSet ProfessorsAssistants = null;
            ResultSet AllCourses = null;
            ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
        %>
        <div id="rightcontentRigs">
            <br/>
            <center>   <font style="color: #905;font-size: x-large;">Add Professor</font>

                <form action="AddInstructor" method="POST">
                    <select name="UserCode">  <%  try {
                            Professors = modelOfFaculityAdmin.ViewMyUsersByUserType(p.getFaculityID(), 4);

                        %>

                        <% while (Professors.next()) {
                        %>                                              
                        <option value="<%=Professors.getInt("Code")%>"><%=Professors.getString("FName")%><%=Professors.getString("LName")%>(<%=Professors.getString("Mail")%>)</option>

                        <% } %> 

                        <% } catch (Exception ex) {

                                out.println("Professors Error:" + ex.getMessage() + "in OpenCourses.jsp");
                            }%>
                    </select><br/>
                    <select name="SubjectCode">  <%  try {
                            AllCourses = modelOfFaculityAdmin.ViewFaculityCourses(p.getFaculityID(), p.getUniversityID());

                        %>

                        <% while (AllCourses.next()) {
                        %>                                              
                        <option value="<%=AllCourses.getInt("ID")%>"><%=AllCourses.getString("SubjectName")%>
                        </option>

                        <% } %> 

                        <% } catch (Exception ex) {

                                out.println("AllCourses Error:" + ex.getMessage() + "in OpenCourses.jsp");
                            }%>
                    </select>
                    <br/>

                    <input type="submit" value="Add" />
                </form>

                <br/>
                <br/>
                <font style="color: #905;font-size: x-large;">Add Professor Assistant:</font>

                <form action="AddInstructor" method="POST">
                    <select name="UserCode">  <%  try {
                            ProfessorsAssistants = modelOfFaculityAdmin.ViewMyUsersByUserType(p.getFaculityID(), 5);

                        %>

                        <% while (ProfessorsAssistants.next()) {
                        %>                                              
                        <option value="<%=ProfessorsAssistants.getInt("Code")%>"><%=ProfessorsAssistants.getString("FName")%><%=ProfessorsAssistants.getString("LName")%>(<%=ProfessorsAssistants.getString("Mail")%>)</option>

                        <% } %> 

                        <% } catch (Exception ex) {

                                out.println("ProfessorsAssistants Error:" + ex.getMessage() + "in OpenCourses.jsp");
                            }%>
                    </select>
                    <br/>
                    <select name="SubjectCode">  <%  try {
                            AllCourses = modelOfFaculityAdmin.ViewFaculityCourses(p.getFaculityID(), p.getUniversityID());

                        %>

                        <% while (AllCourses.next()) {
                        %>                                              
                        <option value="<%=AllCourses.getInt("ID")%>"><%=AllCourses.getString("SubjectName")%>
                        </option>

                        <% } %> 

                        <% } catch (Exception ex) {

                                out.println("AllCourses Error:" + ex.getMessage() + "in OpenCourses.jsp");
                            }%>
                    </select>
                    <br/>
                    <input type="submit" value="Add" />
                </form> 
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
        response.sendRedirect(response.encodeURL("index.jsp"));

    }
%>