<%-- 
    Document   : viewPost
    Created on : May 8, 2016, 9:30:21 PM
    Author     : Mohamed
--%>

<%@page import="models.ModelOfProfessor"%>
<%@page import="models.ModelOfStudent"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>

        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="style.css"/>


    </head>
    <body>
        <!--start rightcontent -->
        <div id="rightcontent2" >


            <div class="media-body">


                <%! int Subject_Code;
                    int POSTID;
                    boolean IsUserRegistgerInThisSubjectOrNot;

                    ResultSet course_posts;

                %>
                <% try {
                        Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
                        POSTID = Integer.parseInt(request.getParameter("POSTID"));


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
                <%
                    try {
                        // select user.FName,user.LName,user.Mail,user.Code,subject.Code,subject.ID,post.date,post.PostID,post.Post from post,user,subject where post.UserID=user.Code and subject.ID=post.SubjectPostID and  subject.ID=? and post.PostID=?";
                        MyPerson p = new MyPerson();
                        course_posts = p.ViewCoursePostContent(Subject_Code, POSTID);
                        //  if(course_posts!=null){

                        while (course_posts.next()) {%>   

                <span class="media-right"  style=" background-color:#fff">
                    <img style="width:60px; height:60px; border:1px solid #cccccc;" class="img-circle"   src="images/myaccc.png">




                    <a class="media-right" href=""> <%=course_posts.getString("FName")%><%=course_posts.getString("LName")%></a>
                    (
                    <% if (course_posts.getInt("user.UserTypeID") == 6) {
                            out.println("Student");
                        } else if (course_posts.getInt("user.UserTypeID") == 5) {
                            out.println("Professor Assistant");
                        } else if (course_posts.getInt("user.UserTypeID") == 4) {
                            out.println("Professor ");
                        } else if (course_posts.getInt("user.UserTypeID") == 3) {
                            out.println("Faculty Admin");
                        } else if (course_posts.getInt("user.UserTypeID") == 2) {
                            out.println("University Admin");
                        } else if (course_posts.getInt("user.UserTypeID") == 1) {
                            out.println("My WebApp Admin");
                        }

                    %>
                    )

                    <a style=" text-decoration: none;
                       font-family: Verdana,Geneva,sans-serif;
                       font-size: 12px;
                       color: #60030;margin-bottom: 5px;
                       " href="mailto:<%=course_posts.getString("Mail")%>"> 
                        <%=course_posts.getString("Mail")%><br/>


                    </a>

                </span> 




                <span style=" margin-left:20px;font-family:tahoma;color:#808080;">Date:  <%=course_posts.getString("RegistrationPostDate")%><br/></span>
                <span style="margin-left:20px;font-family:tahoma; background-color:#02b3e4 "> <%=course_posts.getString("Post")%></span>
                <hr style="margin-top: 20px; margin-bottom: 10px">





                <div class="imoshn">





                    <span style="color:#900; font-weight:bold;"> |  </span>


                    <span style="color:#900; font-weight:bold;"> | </span>
                    <span style="color:#828384;" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="number view it"><i class="fa fa-eye"></i> 25 </span>          
                    <span style="color:#900; font-weight:bold;"> | </span>
                    <span style="color:#828384;" title="" data-toggle="tooltip" data-placement="bottom" data-original-title="number comment"><i class="fa fa-comments"></i> 0</span>

                </div>    
            </div>




            <!--start comment-->

            <div class="form-group" style="padding-top:15px;">

                <table>
                    <tbody><tr>
                            <td style="float:left; padding-right:15px;">

                                <img style=" margin-left:10px; width:70px; height:70px;border:1px solid #cccccc;" class="img-circle" src="images/myaccc.png"></td>
                            <td width="500px" height="100px;"> 

                                <%! ResultSet commentResulttset;
                                    int commentsCount;
                                    String color;
                                %>

                            </td>
                        </tr>
                    </tbody></table>


            </div>

            <!--end comment-->

            <%
                commentsCount = 0;
                commentResulttset = p.ViewCoursePostComments(course_posts.getInt("PostID"));
                //        String sql = "select `comment`.`commentContent`,`comment`.`UserID`,`comment`.`date`,`user`.`Code`,`user`.`FName`,`user`.`LName`,`user`.`Mail`,`user`.`Code`,`post`.`PostID` from `post`,`user`,`comment` where `comment`.`UserID`=`user`.`Code` and `comment`.`PostID`=`post`.`PostID` and `post`.`PostID`=?";
                if (!commentResulttset.isBeforeFirst()) {

                    out.println("<font color='blue' ><h1>There is no Comments  till Now  ^_^</h1></font>");

                }
                while (commentResulttset.next()) {
                    ++commentsCount;

                    if (commentsCount % 2 == 0) {
                        color = "skyblue";
                    } else {
                        color = "yellow";
                    }%>
            <%! String CommentUserType;%>
            <%
                CommentUserType = "Student";
                if (commentResulttset.getInt("UserTypeID") == 4) {
                    CommentUserType = "Professor";
                } else if (commentResulttset.getInt("UserTypeID") == 5) {
                    CommentUserType = "Professor Assistant";
                } else if (commentResulttset.getInt("UserTypeID") == 3) {
                    CommentUserType = "Faculty Admin";
                } else if (commentResulttset.getInt("UserTypeID") == 2) {
                    CommentUserType = "University Admin";
                } else if (commentResulttset.getInt("UserTypeID") == 1) {
                    CommentUserType = "My University Admin";
                }

            %>


            <div style="background-color:<%=color%>;width:500px; margin-left: 150px; " id="commentAjax">

                <%=commentsCount%>.<%=commentResulttset.getString("commentContent")%><br/>   

                <font style=" color: red"> BY: <%=commentResulttset.getString("user.FName")%><%=commentResulttset.getString("user.LName")%>
                ( <%=CommentUserType%> 
                )
                <br/>

                Mail:  <a style=" text-decoration: none;
                          font-family: Verdana,Geneva,sans-serif;
                          font-size: 12px;
                          color: #60030;margin-bottom: 5px;" href="mailto:<%=commentResulttset.getString("Mail")%>"> 

                    <%=commentResulttset.getString("Mail")%>

                    <br/>
                </a>
                Date:  <%=commentResulttset.getString("comment.RegistrationCommentDate")%>
                </font>


                <hr/>
            </div>
            <% }%>



            <%}//end of while
                    /*}else{
                 out.println("There is no Posts untill now  ^_^");}*/
            %>

            <% } catch (Exception ex) {
                System.err.println(ex.getMessage());
                out.println("<font color='blue' ><h1>Some thing wrong is hapened ^_^ </h1></font>");
                out.println("<br/><font color='red' ><h1>Error:" + ex.getMessage() + "</h1></font>");

            %>
            <% }%>




            <%
                }//end of true Get Subject Code
                catch (Exception ex) {
                    out.println("Error:in CourseGuestViewPost:" + ex.getMessage());
                }
            %> 
        </div>
        <!--end  rightcontent -->

    </body>


</html>
