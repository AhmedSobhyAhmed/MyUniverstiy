<%-- 
    Document   : tryviewRatingAjax2
    Created on : Jun 28, 2016, 2:32:07 AM
    Author     : Mohamed
--%>

<%@page import="models.MyPerson"%>
<%@page import="models.ModelOfFaculityAdmin"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <% String Code = request.getParameter("Code");
            boolean isEveryThingOK = true;
            if (Code == null || Code.isEmpty()) {
                out.println("<br/><font color='red'>Please choose an instructor ^_^</font>");
                isEveryThingOK = false;
            } else {
                int InstructorID = 0;
                try {
                    InstructorID = Integer.parseInt(Code);
                } catch (Exception ex) {
                    isEveryThingOK = false;
                    out.println("Something wrong is happened Error:" + ex.getMessage());
                }
                if (isEveryThingOK) {

        %>
        <table border="1">
            <thead>
                <tr>
                    <!-- <th>ID</th>-->
                    <th>Student ID</th>
                    <th>Rating(from 10)</th>
                    <th>Positives</th>
                    <th>Negatives</th>

                </tr>
            </thead>
            <tbody>



                <%                            try {
                        out.println("<br/><br/><br/><br/><br/>");
                        out.println("Instructor:<font color='green'>" + MyPerson.ReturnUserName(InstructorID) + "</font>");
                        ArrayList<String[]> my_ratingsArrayList = new ArrayList<String[]>();
                        ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();

                        my_ratingsArrayList = modelOfFaculityAdmin.ViewRatings(p.getFaculityID(), InstructorID);
                        out.println("<br/><br/>");

                        if (my_ratingsArrayList == null) {
                %>
                <tr><td colspan='5'><font color='red'>No ratings here ^_^</font></td></tr>
                        <% } else {
                        %>
                        <%  for (int i = 0; i < my_ratingsArrayList.size(); i++) {
                                String[] ratings = my_ratingsArrayList.get(i);

                        %>
                <tr>
                    <td><%=ratings[1]%></td>
                    <td>
                        <%
                            if (Integer.parseInt(ratings[2]) < 5) {
                                out.println("<font color='red'>" + ratings[2] + "</font>");
                            } else if (Integer.parseInt(ratings[2]) > 5) {
                                out.println("<font color='blue'>" + ratings[2] + "</font>");

                            } else if (Integer.parseInt(ratings[2]) == 5) {
                                out.println("<font color='gray'>" + ratings[2] + "</font>");

                            }
                        %>

                    </td>
                    <td><font color='blue'><%=ratings[3]%></font></td>    
                    <td><font color='red'><%=ratings[4]%></font></td>     
                </tr>

                <%                        } //end of for
                        }
                    }//end of try
                    catch (Exception ex) {
                        out.println("Instructors Ratings Error:" + ex.getMessage());
                    }
                %> </tbody>
        </table>
        <%    }//end of isEveryThingOK
            }//end of Code is empty%>
    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("index.jsp"));

    }
%>