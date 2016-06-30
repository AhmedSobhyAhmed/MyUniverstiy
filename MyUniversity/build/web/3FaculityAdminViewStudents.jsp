<%-- 
    Document   : 3FaculityAdminViewSubjects
    Created on : Mar 14, 2016, 7:39:49 AM
    Author     : mohamed
--%>

<%@page import="java.util.Date"%>
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
        <div id="rightcontentRigs">
            <br/>
            User:
             <br/>
            <table border="1">
                <thead>
                    <tr>
                        <th>Code</th>
                        <th>Mail</th>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Gender</th>
                        <th>Address</th>
                        <th>Birth Date</th>
                        <th>Registration Date</th>
                        <th>Department</th>
                        <th>Level</th>
                        <th>User Type</th>
                        <th>Approve</th>
                        <th>Action</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                        try {
                            ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();

                            ResultSet result = modelOfFaculityAdmin.ViewMyUsers(p.getFaculityID());
                            while (result.next()) {
                    %>

                    <tr>
                        <td> <%= result.getInt("Code")%> </td>
                        <td><%= result.getString("Mail")%></td>
                        <td><%= result.getString("FName")%></td>
                        <td><%= result.getString("MName")%></td>
                        <td><%= result.getString("LName")%></td>
                        <td><%= result.getString("Gender")%></td>
                        <td><%= result.getString("Address")%></td>
                        <td><%= result.getString("BirthDate")%></td>
                        <td><%= result.getString("RegistrationDate")%></td>
                        <td> <%= MyPerson.ReturnDepartmentName(result.getInt("Code"))%></td>
                        <td><%= MyPerson.ReturnLevelName(result.getInt("Code"))%></td>
                        <td>  <% if (result.getInt("UserTypeID") == 6) {
                                out.println("Student");
                            } else if (result.getInt("UserTypeID") == 4) {
                                out.println("Professor");
                            } else if (result.getInt("UserTypeID") == 5) {
                                out.println("Professor Assistant");
                            }
                            %>



                        </td>
                        <td><%= result.getString("Approve")%></td>
                        <td>
                            <form  method="POST" action="ApproveUser" >
                                <%! String value;%>
                                <% if (result.getString("Approve").equals("Yes")) {
                                        // value = "Delete";
                                %>
                                <input type="submit" name="ApproveButton" value="Delete" />
                                <%} else if (result.getString("Approve").equals("No")) {
                                    // value = "Approve";
                                %>
                                <input type="submit" name="ApproveButton" value="Approve" />

                                <%}
                                %>

                                <input type="text"  hidden="" name="Code" value="<%= result.getInt("Code")%>" />
                                <br/> 

                            </form>
                        </td>


                    </tr>


                    <%
                            }//end of while
                        } catch (Exception ex) {
                            out.println("<br/><br/><br/><br/><br/>");

                            out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font><br/>");
                            out.println("Error:" + ex.getMessage() + "<br/>");

                            out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                        }
                    %>
                </tbody>
            </table>
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
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }
%>