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

<html>
    <head>
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <script>
            tinymce.init({
                selector: '#mytextarea'
            });
        </script> 
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>

        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="style.css"/>

      

    </head>
    <body>
        <div id="rightcontentRigs">

            <%! int Subject_Code;
                boolean IsUserRegistgerInThisSubjectOrNot;
            %>
            <% try {
                    Subject_Code = Integer.parseInt(request.getParameter("CourseCode"));


            %>


            <div id="Mymenue" >
                <ul>
                          <li>

                        <a href="index.jsp">
                            <!--       <form action="?page=CoursePosts" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Posts"/>
       
                                   </form>-->
                            Home
                        </a>
                    </li>
                    <li>
                        
                        <a href="CourseGuestPosts.jsp?CourseCode=<%=Subject_Code%>">
                            <!--       <form action="?page=CoursePosts" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Posts"/>
       
                                   </form>-->
                            Posts
                        </a>
                    </li>
                    <li>

                        <a href="CourseGuestFiles.jsp?CourseCode=<%=Subject_Code%>">
                            <!--       <form action="?page=upload_form" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Files"/>
       
                                   </form>-->
                            Files
                        </a>
                    </li>


                </ul>
            </div>

           


            <hr/>
            <%! ResultSet course_posts;
            %>
            <% try {
                MyPerson p=new MyPerson();
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

                        </tr>
                    </thead>

                    <%                        if (!course_posts.isBeforeFirst()) {
                            out.println("<tr><td colspan='7'><font color='blue'>There is no Posts Untill Now ^_^ </font></td></tr><br/>");
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
                                <a target="_blank" href="CourseGuestViewPost.jsp?POSTID=<%=course_posts.getInt("PostID")%>&Subject_Code=<%=Subject_Code%>" >
                                    <!-- <form action="?page=viewPost&POSTID=<course_posts.getInt("PostID")%>&Subject_Code=<Subject_Code%>" method="POST">
                                         <input hidden="" type="text" name="POSTID" value="<course_posts.getInt("PostID")%>" />
                                         <input hidden="" type="text" name="Subject_Code" value="<Subject_Code%>" />
                                         <input type="submit" value="View"/>
 
                                     </form>-->
                                    View
                                </a> 
                            </td>

                        

                        </tr>  
                    </tbody>



                    <%
                        }
                    %>
                </table>
            </center>

            <% } catch (Exception ex) {
                    out.println("Error:in first catch" + ex.getMessage());
                       out.println("<br/><br/><center><font color='red' ><h1>Error:" + ex.getMessage() + " ^_^</h1></font></center> ");
                    out.println("<br/><br/><br/><center><font color='red' ><h1><a href='index.jsp'>Home</a></h1></font></center> ");


                }%>
            <% 
                   
                }//end of true Get Subject Code
                catch (Exception ex) {
                    out.println("Error: in last catch:" + ex.getMessage());
                       out.println("<br/><br/><br/><center><font color='red' ><h1>Error:" + ex.getMessage() + " ^_^</h1></font></center> ");
                    out.println("<br/><br/><br/><center><font color='red' ><h1><a href='index.jsp'>Home</a></h1></font></center> ");

                }%> 
        </div>



    </body>
</html>
