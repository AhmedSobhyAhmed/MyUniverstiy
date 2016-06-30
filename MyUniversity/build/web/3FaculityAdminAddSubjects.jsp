<%-- 
    Document   : 3FaculityAdminAddSubjects
    Created on : Mar 14, 2016, 7:39:02 AM
    Author     : mohamed
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html>
    <head>
        <script>
            function isNumberKey(evt) {
                var charCode = (evt.which) ? evt.which : event.keyCode
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% if (p.getUserType() == 3) {


        %>

        <div id="rightcontentRigs">

            <center> 
                <div class="FormClass">
                    <form method="POST" action="AddCourse" >
                        <h1> <font style="color: #905;">Course Name:</font></h1><input type="text" name="CourseName" placeholder="Course Name" required=""><br>
                        <h1><font style="color: #905;"> Course Code:</font></h1>  <input type="text" name="CourseCode" placeholder="Product Code" required=""><br>
                        <h1><font style="color: #905;"> Written Grade:</font></h1><input type="number" onkeypress="return isNumberKey(event)"  name="WrittenGrade" placeholder="Written Grade" required=""><br>

                        <h1><font style="color: #905;">Midterm Grade:</font></h1>  <input type="number" onkeypress="return isNumberKey(event)" name="MidtermGrade" placeholder="Midterm Grade" required=""><br>
                        <h1> <font style="color: #905;">Final Grade:</font></h1>  <input type="number" onkeypress="return isNumberKey(event)"  name="FinalGrade" placeholder="Final Grade" required=""><br>

                        <h1><font style="color: #905;"> Course Description:</font></h1>
                        <textarea placeholder="Enter your Course Description " name="CourseDescription" required=""></textarea><br>
                        <!--<h1> Course Image:</h1><input 
                             style="padding-left: 5px;
                                                width:150px; height: 30px; margin-top: 5px;"
                            type="file" name="product_image"  required=""><br>
                        -->
                        <input  style="padding-left: 5px;
                                background-color: #333; border: 1px;  
                                color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                                type="submit"  name="ADD" value="ADD">
                    </form>
                </div>
            </center>
        </div>
        <%            }//end of if usertype is faculity admin or not

        %>
    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }
%>