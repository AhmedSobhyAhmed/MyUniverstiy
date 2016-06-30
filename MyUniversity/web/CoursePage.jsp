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
            %>
            <% try {
                    Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
                    

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

            <% 
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