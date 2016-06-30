<%-- 
    Document   : CourseViewPOSTS
    Created on : Mar 20, 2016, 11:54:33 PM
    Author     : mohamed
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.ModelOfProfessor"%>
<%@page import="models.ModelOfStudent"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");


%>
<html>
    <head>
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <script>
            tinymce.init({
                selector: '#mytextarea'
            });
        </script> 
    </head>
    <body>
        <div id="rightcontentRigs">

            <%! int Subject_Code;
                boolean IsUserRegistgerInThisSubjectOrNot;
            %>
            <% try {
                    Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
                    IsUserRegistgerInThisSubjectOrNot = false;

                    // String Subject_Code2=    (String)session.getAttribute("Subject_Code");
                    if (p.getUserType() == 6) {

                        ModelOfStudent modelOfStudent = new ModelOfStudent();
                        ResultSet rs = modelOfStudent.ViewMyCourses(p.getCode());

                        while (rs.next()) {
                            //SubjectsStudentCode.add(rs.getInt("ID"));
//out.println(rs.getInt("ID") );
                            if (rs.getInt("ID") == Subject_Code) {
                                IsUserRegistgerInThisSubjectOrNot = true;
                            }

                        }
                    } else if (p.getUserType() == 4 || p.getUserType() == 5) {

                        ModelOfProfessor modelOfProfessor = new ModelOfProfessor();
                        ResultSet rs = modelOfProfessor.ViewMyCoursesProfessor(p.getCode());

                        while (rs.next()) {
                            //  SubjectsAssistantCode.add(rs.getInt("ID"));
//out.println(rs.getInt("ID") );

                            if (rs.getInt("ID") == Subject_Code) {
                                IsUserRegistgerInThisSubjectOrNot = true;
                            }
                        }
                    } else if (p.getUserType() == 2 || p.getUserType() == 3 || p.getUserType() == 1) {
                        IsUserRegistgerInThisSubjectOrNot = true;
                    }
                    if (IsUserRegistgerInThisSubjectOrNot == true) {

            %>


            <div id="Mymenue" >
                <ul>
                    <li>

                        <a href="?page=CoursePosts&Subject_Code=<%=Subject_Code%>">
                            <!--       <form action="?page=CoursePosts" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Posts"/>
       
                                   </form>-->
                            Posts
                        </a>
                    </li>
                    <li>

                        <a href="?page=upload_form&Subject_Code=<%=Subject_Code%>">
                            <!--       <form action="?page=upload_form" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Files"/>
       
                                   </form>-->
                            Files
                        </a>
                    </li>



                    <% if (p.getUserType() == 5 || p.getUserType() == 4) {


                    %>
                    <li>

                        <a href="?page=CourseGrades&Subject_Code=<%=Subject_Code%>">
                            <!--       <form action="?page=Course Grades" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Grades"/>
       
                                   </form>-->
                            Grades
                        </a>
                    </li>
                    <%
                        }%>
                </ul>
            </div>

            <form method="post" action="AddPost"><!--?Subject_Code="<Subject_Code%>>-->
                <!--      <input type="text" placeholder="Title" style="float: right;margin-top: 10px;width:195px; border:0; height: 25px; padding: 5px;margin-bottom: 10px;" name="POSTTitle" />
                -->
                <textarea   id="mytextarea" name="Post">Hello, World!</textarea>
                <input type="text"  value="<%=Subject_Code%>" name="Subject_Code" readonly="" hidden="" />

                <input type="submit" value="Post"  style="   width: 200px;
                       height: 40px;text-align: center;
                       margin-left: 150px;
                       margin-top: 20px;
                       background-color:#28a4c9;
                       color: #f5f4f4;
                       border:0px;
                       border-radius: 25px;" />
            </form>



            <hr/>
            <%! ResultSet course_posts;
            %>
            <% try {
                    course_posts = p.ViewCoursePosts(Subject_Code);

            %>
            <center>
                <table border="" cellspacing="1" cellpadding="2">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>User Type</th>
                            <th>Email</th>

                            <th>POST</th>
                            <th>Date</th>
                            <th>View</th>
                            <th>Action</th>

                        </tr>
                    </thead>

                    <%                        if (!course_posts.isBeforeFirst()) {
                            out.println("<tr><td colspan='8'><font color='blue'>There is no Posts Untill Now ^_^ </font></td></tr><br/>");
                        }
                        while (course_posts.next()) {
                    %>

                    <tbody>
                        <tr>


                            <!-- viewPost,,,CourseViewPOSTContents-->
                            <td><%=course_posts.getInt("PostID")%> </td>
                            <%! String Professorcolor;%>
                            <%
                                Professorcolor = "black";
                                if (course_posts.getInt("UserTypeID") == 4 || course_posts.getInt("UserTypeID") == 5) {
                                    Professorcolor = "skyblue";
                                }
                            %>
                            <td><font style="color:<%=Professorcolor%>"><%=course_posts.getString("FName")%> <%=course_posts.getString("LName")%>  </font></td>
                            <td><%=MyPerson.ReturnUserTypeName(course_posts.getInt("Code"))%></td>
                            <td>
                                <a style=" text-decoration: none;
                                   font-family: VerdaSna,Geneva,sans-serif;
                                   font-size: 12px;
                                   color: #60030;margin-bottom: 5px;
                                   " href="mailto:<%=course_posts.getString("Mail")%>"> 
                                    <%=course_posts.getString("Mail")%><br/>

                                </a>
                            </td> 

                            <td><%=course_posts.getString("Post")%></td>

                            <td><%=course_posts.getString("RegistrationPostDate")%> </td>
                            <td>  
                                <a target="_blank" href="MyAccount.jsp?page=CourseViewPost&POSTID=<%=course_posts.getInt("PostID")%>&Subject_Code=<%=Subject_Code%>" >
                                    <!-- <form action="?page=viewPost&POSTID=<course_posts.getInt("PostID")%>&Subject_Code=<Subject_Code%>" method="POST">
                                         <input hidden="" type="text" name="POSTID" value="<course_posts.getInt("PostID")%>" />
                                         <input hidden="" type="text" name="Subject_Code" value="<Subject_Code%>" />
                                         <input type="submit" value="View"/>
 
                                     </form>-->
                                    View
                                </a> 
                            </td>

                            <td>
                                <% if (p.getUserEmail().equals(course_posts.getString("Mail"))) {%>
                                <form action="DeletePost" method="POST">
                                    <input hidden="" name="POSTID" value="<%=course_posts.getString("PostID")%>"/>
                                    <input hidden="" name="Subject_Code" value="<%=Subject_Code%>"/>
                                    <input type="submit" value="Delete" name="Delete" />
                                </form>
                                <% } %>
                            </td>

                        </tr>  
                    </tbody>



                    <%
                        }
                    %>
                </table>
            </center>

            <% } catch (Exception ex) {
                    out.println("" + ex.getMessage());

                }%>
            <% }//end of IsUserRegistgerInThisSubjectOrNot
                    else {
                        out.println("<br/><br/><br/><br/><br/><br/><center><font color='red' ><h1>You didn't register in this course ^_^</h1></font></center> ");
                    }
                }//end of true Get Subject Code
                catch (Exception ex) {
                    out.println("" + ex.getMessage());
                }%> 
        </div>



    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }%>