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
                    IsUserRegistgerInThisSubjectOrNot = true;

                    if (IsUserRegistgerInThisSubjectOrNot == true) {

            %>


       

            <form method="post" action="AddNews"><!--?Subject_Code="<Subject_Code%>>-->
                <!--      <input type="text" placeholder="Title" style="float: right;margin-top: 10px;width:195px; border:0; height: 25px; padding: 5px;margin-bottom: 10px;" name="POSTTitle" />
                -->
                <textarea   id="mytextarea" name="Post">Hello, World!</textarea>
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
                    course_posts = p.ViewNews(p.getUniversityID(), p.getFaculityID());

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
                            <td><%=course_posts.getInt("ID")%> </td>
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

                            <td><%=course_posts.getString("PostDate")%> </td>
                            <td>  
                             v
                            </td>

                            <td>
                                <% if (p.getUserEmail().equals(course_posts.getString("Mail"))) {%>
                                <form action="DeleteNews" method="POST">
                                    <input hidden="" name="NewsID" value="<%=course_posts.getInt("ID")%>"/>
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