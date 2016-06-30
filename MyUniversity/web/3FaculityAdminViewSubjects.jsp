<%-- 
    Document   : 3FaculityAdminViewSubjects
    Created on : Mar 14, 2016, 7:39:49 AM
    Author     : mohamed
--%>

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
        <% if (p.getUserType() == 3) { %>
        <div id="rightcontentRigs">
            <center>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Code</th>
                            <th>Course Name</th>
                            <th>Written Grade</th>
                            <th>Midterm Grade</th>
                            <th>Final Grade</th>
                            <th>Description</th>
                            <th>Open From Date:</th>
                            <th>Open To Date:</th>

                            <th>Registration Date</th>
                            <th>By:</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        <%     try {

                                ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
                                ResultSet rs = modelOfFaculityAdmin.ViewFaculityCourses(p.getFaculityID(), p.getUniversityID());
                                out.println("<br/><br/><br/><br/><br/>");

                                //    if(!rs.next()){out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");}
                                while (rs.next()) {
                                    //`ID`, `Code`, `SubjectName`, 
                                    //`WritenGrade`, `MidtermGrade`, 
                                    //`FinalExamGrade`, `FaculityID`,
                                    //`UniversityID`, `CourseDescription`, `AddedBy`, `RegisterCourseInSystemDate`             
                        %>
                        <tr>
                            <td><%= rs.getInt("ID")%></td>
                            <td><%= rs.getString("Code")%></td>
                            <td><%= rs.getString("SubjectName")%></td>
                            <td><%= rs.getInt("WritenGrade")%></td>
                            <td><%= rs.getInt("MidtermGrade")%></td>
                            <td><%= rs.getInt("FinalExamGrade")%></td>
                            <td><%= rs.getString("CourseDescription")%></td>
                           <td><%= rs.getString("OpenFromDate")%></td>
                            <td><%= rs.getString("OpenToDate")%></td>

                            <td><%= rs.getString("RegisterCourseInDate")%></td>
                            <td><%= rs.getString("AddedBy")%></td>

                            <td colspan="2">
                                <form method="POST" action="DeleteCourse">
                                    <input type="hidden"  name="Subject_Code" value="<%=rs.getInt("ID")%>"  />
                                    <input type="submit" value="Delete" />
                                </form>
                            </td>
                        </tr>
                        <%       }//end of while
                            } catch (Exception ex) {
                                out.println("<br/><br/><br/><br/><br/>");
                                out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font>");
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                            }%>
                    </tbody>
                </table>
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