<%@page import="models.ModelOfUniversityAdmin"%>
<%@page import="models.ModelOfFaculityAdmin"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
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
            <div id="top_page">
                <h1>Courses</h1>


            </div>
            <div id="down">


                <!--end leatest project-->

                <div id="latestproject">

                    <h1> latest courses</h1>
                    <%
                        if (p.getUserType() == 6) {

                            try {

                                ModelOfStudent modelOfStudent = new ModelOfStudent();
                                ResultSet rs = modelOfStudent.ViewMyCourses(p.getCode());
                                if (!rs.isBeforeFirst()) {
                                    out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");
                                }

                    %>

                    <%                        while (rs.next()) {
                            out.println(rs.getInt("ID"));

                    %>
                    <div class="send">
                        <div class="project">
                            <img src="images/c.png" width="100" height="50" />
                            <!--//subject=`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //subjectstudent=`SubjectID`, `StudentID`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //user=`Code`, `Mail`,
                            --> 

                            <h2> <%= rs.getString("SubjectName")%></h2>

                            <p>


                                Midterm :<font color="red"><%= rs.getInt("MidtermGrade")%></font>

                                <br/>
                                Class Work: <font color="red"><%= rs.getInt("WritenGrade")%></font>

                                <br/>
                                Final Exam :<font color="red"><%= rs.getInt("FinalExamGrade")%></font>

                                <br/>


                                Code:<font color="red"><%= rs.getString("Code")%></font>
                                <br/>
                                Final Grade:
                                <% if (rs.getInt("FinalGrade") > 50) {
                                %> <font color="red"><%= rs.getInt("FinalGrade")%></font><font color="green">Pass</font>
                                <% } else if (rs.getInt("FinalGrade") < 50 && rs.getInt("WritenGrade") > -1) {%>
                                <font color="red"><%= rs.getInt("FinalGrade")%></font> <font color="green">Fail</font>
                                <% } else {%>
                                <font color="green">No Grades till now</font>
                                <% }%>
                                <br/>
                                Term:
                                <font color="red"><%= rs.getString("subjectstudent.Term")%></font>
                                <br/>
                                Begins In:
                                <font color="red"><%= rs.getString("subjectstudent.CourseOpeningFrom")%></font>
                                <br/>
                                Ends In:
                                <font color="red"><%= rs.getString("subjectstudent.CourseOpeningTo")%></font>

                            </p>


                            <a href="?page=CoursePosts&Subject_Code=<%=rs.getInt("ID")%>">
                                <!-- <form action="?page=CoursePage" method="POST">
                                      <input hidden="" type="text" name="Subject_Code" value="<rs.getInt("ID")%>" />
                                      <input type="submit" value="View"/>
                                     
                                 </form>-->
                                View
                            </a> 

                        </div>
                    </div>
                    <!-- end block 1 -->

                    <%

                            }
                        } catch (Exception ex) {
                            out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font><br/>Error:(" + ex.getMessage() + ")");
                        }

                    } else if (p.getUserType() == 4 || p.getUserType() == 5) {

                        try {

                            ModelOfProfessor modelOfProfessor = new ModelOfProfessor();
                            ResultSet rs = modelOfProfessor.ViewMyCoursesProfessor(p.getCode());

                            if (!rs.isBeforeFirst()) {
                                out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");
                            }

                    %>

                    <%                        while (rs.next()) {
                            out.println(rs.getInt("ID"));

                    %>
                    <div class="send">
                        <div class="project">
                            <img src="images/c.png" width="100" height="50" />
                            <!--//subject=`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //subjectstudent=`SubjectID`, `StudentID`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //user=`Code`, `Mail`,
                            --> 
                            <font color="red"> <b><%= rs.getString("SubjectName")%></b></font><br/>
                            Semester<font color="red"><%= rs.getString("semester")%></font>
                            <br/>

                            Code:<font color="red"><%= rs.getString("Code")%></font>
                            <br/>

                            Begins In:
                            <font color="red"><%= rs.getString("subjectteachingstuff.CourseOpeningFrom")%></font>
                            <br/>
                            Ends In:
                            <font color="red"><%= rs.getString("subjectteachingstuff.CourseOpeningTo")%></font>
                            <br/>
                            Added By:
                            <font color="red"><%= rs.getString("subjectteachingstuff.AddedBy")%></font>

                            <br/>


                            <a href="?page=CoursePosts&Subject_Code=<%=rs.getInt("ID")%>">
                                <!-- <form action="?page=CoursePage" method="POST">
                                      <input hidden="" type="text" name="Subject_Code" value="<rs.getInt("ID")%>" />
                                      <input type="submit" value="View"/>
                                     
                                 </form>-->

                                View
                            </a> 


                        </div>
                    </div>
                    <!-- end block 1 -->

                    <%

                            }
                        } catch (Exception ex) {
                            out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font>");
                        }

                    } else if (p.getUserType() == 3) {

                        try {

                            ModelOfFaculityAdmin modelOfFacultyAdmin = new ModelOfFaculityAdmin();
                            ResultSet rs = modelOfFacultyAdmin.ViewFaculityCourses(p.getFaculityID(), p.getUniversityID());
                          
                            if (!rs.isBeforeFirst()) {
                                out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");
                            }

                    %>

                    <%                        while (rs.next()) {
                            out.println(rs.getInt("ID"));

                    %>
                    <div class="send">
                        <div class="project">
                            <img src="images/c.png" width="100" height="50" />
                            <!--//subject=`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //subjectstudent=`SubjectID`, `StudentID`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //user=`Code`, `Mail`,
                            --> 
                            <font color="red"> <b><%= rs.getString("SubjectName")%></b></font><br/>
                           
                            Open Course In Date<font color="red"><%= rs.getString("OpenCourseInDate")%></font>
                            <br/>
                            Open From Date<font color="red"><%= rs.getString("OpenFromDate")%></font>
                            <br/>
                            Open To Date<font color="red"><%= rs.getString("OpenToDate")%></font>
                            <br/>
                            Description<font color="red"><%= rs.getString("CourseDescription")%></font>
                            <br/>
                            Code:<font color="red"><%= rs.getString("Code")%></font>
                            <br/>

                            <br/>


                            <a href="?page=CoursePosts&Subject_Code=<%=rs.getInt("ID")%>">
                                <!-- <form action="?page=CoursePage" method="POST">
                                      <input hidden="" type="text" name="Subject_Code" value="<rs.getInt("ID")%>" />
                                      <input type="submit" value="View"/>
                                     
                                 </form>-->

                                View
                            </a> 


                        </div>
                    </div>
                    <!-- end block 1 -->

                    <%

                            }
                        } catch (Exception ex) {
                            out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font>");
                        }

                    } else if (p.getUserType() == 2) {

                        try {

                            ModelOfUniversityAdmin modelOfUniversityAdmin = new ModelOfUniversityAdmin();
                            ResultSet rs = modelOfUniversityAdmin.ViewUniversityCourses(p.getUniversityID());
                            if (!rs.isBeforeFirst()) {
                                out.println("<font color='blue'>There is no Courses Untill Now ^_^ </font>");
                            }

                    %>

                    <%                        while (rs.next()) {
                            out.println(rs.getInt("ID"));

                    %>
                    <div class="send">
                        <div class="project">
                            <img src="images/c.png" width="100" height="50" />
                            <!--//subject=`SubjectName`, `Code`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //subjectstudent=`SubjectID`, `StudentID`, `WritenGrade`, `MidtermGrade`, `RemaingGrade`
                   //user=`Code`, `Mail`,
                            --> 
                            <font color="red"> <b><%= rs.getString("SubjectName")%></b></font><br/>
                            
                            Open Course In Date<font color="red"><%= rs.getString("OpenCourseInDate")%></font>
                            <br/>
                            Open From Date<font color="red"><%= rs.getString("OpenFromDate")%></font>
                            <br/>
                            Open To Date<font color="red"><%= rs.getString("OpenToDate")%></font>
                            <br/>
                           
                            Code<font color="red"><%= rs.getString("Code")%></font>
                            <br/>
                            Faculty<font color="red"><%= MyPerson.ReturnFaculityNameFromCourses(rs.getInt("FaculityID"))%></font>
                            <br/>
                            <br/>


                            <a href="?page=CoursePosts&Subject_Code=<%=rs.getInt("ID")%>">
                                <!-- <form action="?page=CoursePage" method="POST">
                                      <input hidden="" type="text" name="Subject_Code" value="<rs.getInt("ID")%>" />
                                      <input type="submit" value="View"/>
                                     
                                 </form>-->

                                View
                            </a> 


                        </div>
                    </div>
                    <!-- end block 1 -->

                    <%

                            }
                        } catch (Exception ex) {
                            out.println("<font color='blue'>SomeThing Wrong is happened  ^_^"+ex.getMessage()+" </font>");
                        }

                    } else {
                        out.println("<font color='blue'>You shouldn't be here ^_^ </font>");

                    %>

                    <%                        }
                    %>





                </div>





            </div>




        </div> <!--end  rightcontent -->
    </body>
    <% } else {
            response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

        }%>
</html>
