<%@page import="models.MyPerson"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <% if (session.getAttribute("UserObject") != null) {
            MyPerson p = (MyPerson) session.getAttribute("UserObject");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>
          
            <%= p.getFName()%><%= p.getLName()%></title>



        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>

        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="style.css"/>

        <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
        <link href='https://fonts.googleapis.com/css?family=Raleway:400,300,500,600,700' rel='stylesheet' type='text/css'/>


        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
        <script src="dropdown-menu.js" type="text/javascript"></script>


        <script type="text/javascript">
            <!--
            function toggle_visibility(id) {
                var e = document.getElementById(id);
                if (e.style.display == 'block')
                    e.style.display = 'none';
                else
                    e.style.display = 'block';
            }
            //-->
        </script>







        <script type="text/javascript">
            $(document).ready(function () {
                $('.text_container').addClass("hidden");

                $('.text_container').click(function () {
                    var $this = $(this);

                    if ($this.hasClass("hidden")) {
                        $(this).removeClass("hidden").addClass("visible");

                    } else {
                        $(this).removeClass("visible").addClass("hidden");
                    }
                });
            });
        </script>





        <script src="tinymce/js/tinymce/tinymce.min.js"></script>
        <script>tinymce.init({selector: 'textarea2'});</script>

    </head>

    <body>

        <!--start content -->
        <div  id="contentmyaccount">

            <!--start leftcontent -->
            <div id="leftcontent2">
                <div id="head"> 
                    <div class="menu2">         
                        <span class="main2-color"><i class="fa fa-graduation-cap">
                            </i>My

                            <span>university</span></span>
                    </div>

                </div>
                <!--end head-->
                <ul class="nav2">
                    <li >
                        <a href="index.jsp"><i class="fa fa-home" style="font-size:28px;color:#02b3e4"></i>  Home</a></li>
                    <li><a href="Chat.jsp">Chat</a></li>
                    <li><a href="Chat2.jsp">Chat2</a></li>
                    <li><a href="?page=EditProfile"><i class="fa fa-cogs" style="font-size:28px;color:#02b3e4"></i> Edit Profile</a> </li>
                    <li><a href="?page=Courses">Courses</a></li>
                    <li><a href="?page=News">News</a></li>

                    <%
                        if (p.getUserType() == 1) {


                    %>
                    <li><a href="?page=AddUniversity">Add University with it's Admin</a></li>
                    <li><a href="?page=ViewUniversity">View University Admins </a></li>

                    <%        } else if (p.getUserType() == 2) {
                    %>
                    <li><a href="?page=2UniversityAdminAddFaculity">Add Faculty </a></li>
                    <li><a href="?page=2UniversityAdminAddFaculityAdmin">Add Faculty Admin</a></li>

                    <li><a href="?page=2UniversityAdminViewUsers">View Users  </a></li>

                    <%
                    } else if (p.getUserType() == 3) {
                    %>
                    <li><a href="?page=3FaculityAdminAddStudents">Add Students</a></li>
                    <li><a href="?page=3FaculityAdminViewStudents">View Students</a></li>
                    <!-- <li><a href="?page=3FaculityAdminAddYear">Add Year</a></li>-->
                    <li><a href="?page=3FaculityAdminAddSubjects">Add Courses</a></li>
                    <li><a href="?page=3FaculityAdminViewSubjects">View Courses</a></li>
                    <li><a href="?page=3FaculityAdminOpenCourses">Open Courses</a></li>
                    <li><a href="?page=3FaculityAdminViewOpenCourses">View Open Courses</a></li>
                    <li><a href="?page=3FaculityAdminAddInstructor">Add Instructor To Open Courses</a></li>
                    <li><a href="?page=3FaculityAdminViewRatingsAjax">View Ratings</a></li>

                    <%
                    } else if (p.getUserType() == 4) {

                    %>


                    <%                    } else if (p.getUserType() == 5) {
                    %>


                    <%        } else if (p.getUserType() == 6) {

                    %>

                    <li><a href="?page=6StudentRegisterCourses">Register Courses</a></li>
                    <li><a href="?page=Transcript">Transcript</a> </li>
                    <li><a href="?page=6StudentRating">Rating</a> </li>

                    <%        } else {
                    %>

                    <%
                        }
                    %>

                    <li> <a href="MyLogout"><i class="fa fa-sign-out" style="font-size:28px;color:#02b3e4">
                            </i>  logout</a></li>


                </ul>           




            </div>
            <!--end leftcontent -->
            <!--start rightcontent -->
            <!--  <iframe src="//www.facebook.com/plugins/follow?href=https%3A%2F%2Fwww.facebook.com%2FMohamed.AbdullahFCIH&amp;layout=standard&amp;show_faces=true&amp;colorscheme=light&amp;width=450&amp;height=80" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:450px; height:80px;" allowTransparency="true">
                  
              </iframe>-->
            <%! String pageName = null;%>
            <%                        try {

                    pageName = request.getParameter("page");
                    //     out.println(request.getAttribute("innerpage"));
                    //                    out.println(pageName);

                    if (pageName != null) {
                        pageName += ".jsp";

                       // out.println(pageName);
%>

            <jsp:include page="<%=pageName%>"></jsp:include>

            <%
                }

            } catch (Exception ex) {

                System.err.println(ex.getMessage());

            %>
            <h1>Page Not Found ^_^  </h1>
            <%  }//end of catch %> 

            <!--end  rightcontent -->




        </div>
        <!--end content -->













        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>



        <script src="js/bootstrap.min.js"></script>
        <div id="end">

            <!--Copy rights reserved© <a href="http://myuniversity.com">university.com 2016</a> 11>-->
        </div>


    </body>

    <% } else {
            response.sendRedirect(response.encodeURL("index.jsp"));

        }%>
</html>


















