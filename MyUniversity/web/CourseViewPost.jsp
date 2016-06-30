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
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src='https://cdn.tinymce.com/4/tinymce.min.js'></script>
        <script>
            tinymce.init({
                selector: '#mytextarea'
            });
        </script> 
        <script>
            var mycomment = document.getElementById("mycomment").innerHTML;

            function insertComment(POSTID, Subject_Code) {
                if (POSTID == "" || Subject_Code == "" || mycomment == "") {
                    //  document.getElementById("commentAjax").innerHTML = "";
                    //alert("please fill all fields");
                    return;
                } else {
                    if (window.XMLHttpRequest) {
                        // code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlhttp = new XMLHttpRequest();
                    } else {
                        // code for IE6, IE5
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlhttp.onreadystatechange = function () {
                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                            document.getElementById("commentAjax").innerHTML = xmlhttp.responseText;
                        }
                    };
                    xmlhttp.open("GET", "tryviewRatingAjax2.jsp?Subject_Code=" + Subject_Code"&POSTID=" + POSTID + "&mycomment=jkopo" , true);
                            xmlhttp.send();
                }
            }
        </script>
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
                <%
                    try {
                        // select user.FName,user.LName,user.Mail,user.Code,subject.Code,subject.ID,post.date,post.PostID,post.Post from post,user,subject where post.UserID=user.Code and subject.ID=post.SubjectPostID and  subject.ID=? and post.PostID=?";

                        course_posts = p.ViewCoursePostContent(Subject_Code, POSTID);
                        //  if(course_posts!=null){

                        while (course_posts.next()) {%>   

                <span class="media-right"  style=" background-color:#fff">
                    <img style="width:60px; height:60px; border:1px solid #cccccc;" class="img-circle"   src="images/myaccc.png">




                    <a class="media-right" href=""> <%=course_posts.getString("FName")%><%=course_posts.getString("LName")%></a>
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



                    <form style=" float:left ;display: inline;" enctype="multipart/form-data" id="like" method="post" action="add_like.php">

                        <input hidden="" name="kind" value="1">
                        <input hidden="" name="pid" value="486">
                        <input hidden="" name="uid" value="875">
                        <a style="text-decoration:none;" onclick="$(this).closest('form').submit()"><span id="likeerr">
                                <span style="color:#828384;">
                                    <i class="fa fa-thumbs-up"></i>  0 </span>
                            </span> </a>
                    </form>

                    <span style="color:#900; font-weight:bold;"> |  </span>


                    <form style=" float:left ; display: inline;" enctype="multipart/form-data" id="unlike" method="post" action="">
                        <input hidden="" name="kind" value="1">
                        <input hidden="" name="pid" value="486">
                        <input hidden="" name="uid" value="875">
                        <a style="text-decoration:none;" onclick="$(this).closest('form').submit()"><span id="unlikeerr"><span style=" float:left ;color:#828384;"><i class="fa fa-thumbs-down"></i>  0 </span></span> </a>
                    </form>
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
                                <!--onchange="insertComment(57, 2)" -->
                                <form id="myForm" method="post" action="AddComment" style="  width:900px;">             
                                    <textarea id="mytextarea"  name="mycomment" >Hello EveryOne this is my first comment  ^_^</textarea>
                                    <!-- style="height:200px; margin-left:15px; margin-right:15px;font-family:Tahoma, Geneva, sans-serif; background-color:#fff;" class="form-control" rows="1" maxlength="340" placeholder="write comment"></textarea>-->
                                    <input hidden="" value="1" name="kind">
                                    <input hidden="" value="486" name="pid">
                                    <input hidden="" value="875" name="uid"> 
                                    <input type="text" value="<%=POSTID%>" name="POSTID" readonly="" hidden="" />
                                    <input type="text" value="<%=Subject_Code%>" name="Subject_Code" readonly="" hidden="" />

                                    <table>
                                        <tbody><tr>
                                                <td style="float:right;">

                                                    <!--  ondblclick="insertComment(<=POSTID%>,<=Subject_Code%>)"-->

                                                    <button name="submit" id="submit" style="margin-right:15px;margin-top:10px;margin-left:15px;" type="submit" class="btn btn-primary" onchange="insertComment(57, 2)"><span class="fa fa-comment"></span> comment</button>
                                                </td><td width="100%">    
                                                    <span style="color:#fff; padding:5px;" id="here"></span>                     
                                                </td>
                                            </tr>
                                        </tbody></table> 


                                </form>                                               
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

                <% if (p.getUserEmail().equals(commentResulttset.getString("Mail"))) {%>
                <form action="DeleteComment" method="POST">
                    <input hidden="" name="POSTID" value="<%=POSTID%>"/>
                    <input hidden="" name="Subject_Code" value="<%=Subject_Code%>"/>

                    <input hidden="" name="CommentID" value="<%=commentResulttset.getInt("CommentID")%>"/>
                    <input type="submit" value="Delete" name="Delete"  />
                </form>
                <% } %>

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




            <%}//end of IsUserRegistgerInThisSubjectOrNot
                    else {
                        out.println("<br/><br/><br/><br/><br/><br/><center><font color='red' ><h1>You didn't register in this course ^_^</h1></font></center> ");
                    }
                }//end of true Get Subject Code
                catch (Exception ex) {

                }
            %> 
        </div>
        <!--end  rightcontent -->

    </body>


</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }%>