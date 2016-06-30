<%-- 
    Document   : 3FaculityAdminViewRatings
    Created on : Jun 25, 2016, 9:39:03 AM
    Author     : Mohamed
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.ModelOfFaculityAdmin"%>
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
    </head>
    <body>
        <% if (p.getUserType() == 3) { %>
        <%
            ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
            ResultSet Instructors = modelOfFaculityAdmin.ViewMyUsersByUserTypeRatings(p.getFaculityID());

        %>
        <div id="rightcontentRigs">
            <center>

                <form action="MyAccount.jsp?page=3FaculityAdminViewRatings" method="POST">
                    <h1> <font style="color: #905;">Instructors Names </font></h1> <br/>    
                    <select required="" name="Code">
                        <% try {
                                while (Instructors.next()) {%>
                        <option value="<%= Instructors.getInt("Code")%>">
                           <%= Instructors.getString("FName")%> <%= Instructors.getString("LName")%>

                        </option>
                        <%
                                }//end of result
                            }//end of try
                            catch (Exception ex) {
                                out.println("Some thing wrong is happened ^_^,Eror(" + ex.getMessage() + ")");
                            }
                        %>
                    </select>


                    <br/>

                    <input  style="padding-left: 5px;
                            background-color: #333; border: 1px;  
                            color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                            type="submit"  name="View" value="View">   
                </form>

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
                                out.println("InstructorID:" + InstructorID);
                                ArrayList<String[]> my_ratingsArrayList = new ArrayList<String[]>();

                                my_ratingsArrayList = modelOfFaculityAdmin.ViewRatings(p.getFaculityID(), InstructorID);
                                out.println("<br/><br/><br/><br/><br/>");

                                if (my_ratingsArrayList == null) {
                                    out.println("<tr><td colspan='5'><font color='red'>No ratings here ^_^</font></td></tr>");
                                } else {
                        %>
                        <%  for (int i = 0; i < my_ratingsArrayList.size(); i++) {
                                String[] ratings = my_ratingsArrayList.get(i);

                        %>
                        <tr>
                            <td><%=ratings[1]%></td>
                            <td><%=ratings[2]%></td>
                            <td><%=ratings[3]%></td>    
                            <td><%=ratings[4]%></td>     
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
            </center>
        </div>
        <%
            }//end of if usertype is faculity admin or not
            else {
                out.println("<br/><br/><br/><br/><br/>");
                out.println("<center><font color='blue'><h1>You shouldn't be here ^_^ </h1></font></center>");

            }

        %>
    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("index.jsp"));

    }
%>