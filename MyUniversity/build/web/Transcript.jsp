<%-- 
    Document   : Transcript
    Created on : May 26, 2016, 10:41:28 PM
    Author     : Mohamed
--%>

<%-- 
    Document   : Courses
    Created on : May 8, 2016, 5:49:12 AM
    Author     : Mohamed
--%>

<%@page import="models.ModelOfProfessor"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.ModelOfStudent"%>
<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% if (session.getAttribute("UserObject") != null) {
            MyPerson p = (MyPerson) session.getAttribute("UserObject");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="rightcontent5">


            <br/><br/><br/><br/><br/><br/><br/>


            <center>

                <%
                    if (p.getUserType() == 6) {

                        try {

                            ModelOfStudent modelOfStudent = new ModelOfStudent();
                            ResultSet rs = modelOfStudent.ViewMyCourses(p.getCode());

                            //    if(!rs.next()){out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");}
%>

                <table border="1">
                    <thead>
                        <tr>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>     


                            <td> University:  <%=MyPerson.ReturnUniversityName(p.getCode())%></td>
                        </tr>
                        <tr>
                            <td>               Faculity: <%=MyPerson.ReturnFaculityName(p.getCode())%>
                            </td>
                        </tr>
                        <tr>
                            <td>              Student Name: <%=p.getFName()%> <%=p.getMName()%> <%=p.getLName()%>
                            </td>
                        </tr>
                        <tr>
                            <td>               Level:  <%=MyPerson.ReturnLevelName(p.getCode())%>
                            </td>
                        </tr>
                        <tr>
                            <td>               Department  <%=MyPerson.ReturnDepartmentName(p.getCode())%>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <table border="1">
                    <thead>
                        <tr>
                            <th>Subject Name</th>
                            <th>Subject Code</th>
                            <th>Written Grade</th>
                            <th>Midterm Grade</th>
                            <th>Final Exam Grade</th>
                            <th>Final Grade</th>
                            <th>Term</th>
                            <th>Registration Date</th>

                        </tr>
                    </thead>
                    <tbody>




                        <%                while (rs.next()) {
                               // out.println(rs.getInt("ID"));

                        %>

                        <tr>
                            <td><%=rs.getString("SubjectName")%></td>
                            <td><%=rs.getString("ID")%></td>
                            <td><%=rs.getString("WritenGrade")%></td>
                            <td><%=rs.getString("MidtermGrade")%></td>
                            <td><%=rs.getString("FinalExamGrade")%></td>
                            <td><%=rs.getString("FinalGrade")%></td>
                            <td><%=rs.getString("subjectstudent.Term")%></td>

                            <td><%=rs.getString("subjectstudent.StudentRegisterSubjectDate")%></td>


                        </tr>
                        <%                                }

                        %> </tbody>
                </table>
                <%                    } catch (Exception ex) {
                        out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font>");
                    }

                %><br/><br/>
                <form action="CreateTranscript" method="POST">

                    <input type="submit" value="Download Transcript" />

                </form>
                <%                }


                %>

            </center>












        </div> <!--end  rightcontent -->
    </body>
    <% } else {
            response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

        }%>
</html>

