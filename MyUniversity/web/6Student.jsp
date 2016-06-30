<%-- 
    Document   : 6Student
    Created on : Mar 9, 2016, 2:38:01 PM
    Author     : mohamed
--%>

<%@page import="models.MyPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <%= p.getFName()%><%=p.getLName()%></title>

    </head>
    <body>


        <div id="Mymenue" >
            <ul>
                <li><a href="?innerpage=Courses">Courses</a></li>
                <li><a href="?innerpage=Chats">Chats</a></li>
                <li><a href="?innerpage=6StudentRegisterCourses">Register Courses</a></li>
                <li><a href="?innerpage=6StudentTranscript">Transcript</a></li>
                <li><a href="?innerpage=EditProfile">Edit Profile</a></li>

            </ul>
        </div>
        <!--
        <form action="MyAccount.jsp" method="POST">
          <input name="innerPage" type="text" readonly="" hidden=""  value="Chats">
           <input type="submit"  style=" float: right;background-color: black;
                      color: #f5f4f4;
                      "
                    value="Go" name="Go">
         </form>-->

        <%! String pageName = null;%>
        <%                        try {

                pageName = request.getParameter("innerpage");
           //     out.println(request.getAttribute("innerpage"));
             //                    out.println(pageName);

                if (pageName != null) {
                    pageName += ".jsp";

                     out.println(pageName);
%>

        <jsp:include page="<%=pageName%>"></jsp:include>

        <%
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());

        %>
        <h1>Page Not Found ^_^  </h1>
        <%  }//end of catch %> 

    </body>

</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }%>