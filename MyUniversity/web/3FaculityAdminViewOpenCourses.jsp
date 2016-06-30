<%-- 
    Document   : 3FaculityAdminViewSubjects
    Created on : Mar 14, 2016, 7:39:49 AM
    Author     : mohamed
--%>

<%@page import="java.util.ArrayList"%>
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

                            <th>Registration Date From</th>
                            <th>Registration Date To</th>
                            <th>For Level</th>
                            <th>For Department</th>
                            <th>For Term</th>
                            <th>Open Date</th>
                            <th>Open From</th>
                            <th>Open To</th>
                            <th>By:</th>
                            <th> Professors:</th>
                            <th> Professor Assistants:</th>


                        </tr>
                    </thead>
                    <tbody>
                        <%!  ResultSet Professors = null;
                            ResultSet ProfessorsAssistants = null;
                            ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
                        %>

                        <%
                            try {

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

                            <td><%=rs.getString("DateFrom")%></td>
                            <td><%=rs.getString("DateTo")%></td>
                            <td><%= MyPerson.ReturnLevelNameFromUserLevelTable(rs.getInt("LevelID"))%></td>
                            <td><%= MyPerson.ReturnDepartmentNameFromUserDepartmentTable(rs.getInt("DepartmentID"))%></td>
                            <td><%= rs.getString("Term")%></td>
                            <td><%= rs.getString("OpenCourseInDate")%></td>
                            <td><%= rs.getString("OpenFromDate")%></td>
                            <td><%= rs.getString("OpenToDate")%></td>

                            <td><%= rs.getString("OpenBy")%></td>
                            <td>
                                <%
                                    try {
                                        ArrayList<MyPerson> my_personsArrayList = new ArrayList<MyPerson>();
                                        my_personsArrayList = modelOfFaculityAdmin.ViewMyTeachingStuffForSpecialCourse(rs.getString("OpenFromDate"), rs.getString("OpenToDate"), rs.getInt("ID"), 4);
                                        if (my_personsArrayList != null) {
                                %> <ol><%
                                    for (int i = 0; i < my_personsArrayList.size(); i++) {
                                        MyPerson my_person = my_personsArrayList.get(i);
                                        if (my_person.getUserEmail() != null || !my_person.getUserEmail().isEmpty()) {

                                    %>
                                    <li>
                                        <%= my_person.getFName()%>
                                        <%= my_person.getLName()%>
                                        ( <%= my_person.getUserEmail()%>)

                                    </li>
                                    <%
                                            } else {
                                                out.println("<li>No Professors here(in this course) </li>");
                                            }

                                        }//end of for
                                    %>                                </ol>
                                    <%
                                            }//if arraylist is null
                                            else {
                                                out.println("<li>No Professors here(all courses) </li>");

                                            }
                                        }//end of try
                                        catch (Exception ex) {
                                            out.println("my_personsArrayList Error:" + ex.getMessage());
                                        }
                                    %>

                            </td>
                            <td>
                                <%
                                    try {
                                        ArrayList<MyPerson> my_personsArrayList2 = new ArrayList<MyPerson>();
                                        my_personsArrayList2 = modelOfFaculityAdmin.ViewMyTeachingStuffForSpecialCourse(rs.getString("OpenFromDate"), rs.getString("OpenToDate"), rs.getInt("ID"), 5);
                                        if (my_personsArrayList2 != null) {
                                %> <ol><%
                                        for (int i = 0; i < my_personsArrayList2.size(); i++) {
                                            MyPerson my_person2 = my_personsArrayList2.get(i);
                                            if (my_person2.getUserEmail() == null || my_person2.getUserEmail().isEmpty()) {
                                                out.println("<li>No Professor Assistants here(in this course) </li>");

                                            }else{ %>
                                    <li>
                                        <%= my_person2.getFName()%>
                                        <%= my_person2.getLName()%>
                                        ( <%= my_person2.getUserEmail()%>)

                                    </li>
                                    <%
                                            } 

                                        }//end of for
                                    %>                                </ol>
                                    <%
                                            }//if arraylist is null
                                            else {
                                                out.println("<li>No Professor Assistants here(all courses) </li>");

                                            }
                                        }//end of try
                                        catch (Exception ex) {
                                            out.println("my_personsArrayList Error:" + ex.getMessage());
                                        }
                                    %>

                            </td>
                            <!--<td colspan="2">
                                <form method="POST" action="DeleteCourse">
                                    <input type="hidden"  name="Subject_Code" value="<rs.getInt("ID")%>"  />
                                    <input type="submit" value="Delete" />
                                </form>
                            </td>-->

                        </tr>
                        <%    }//end of while
                            } catch (Exception ex) {
                                out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font>");
                            }%>
                    </tbody>
                </table>
            </center><br/><br/>

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