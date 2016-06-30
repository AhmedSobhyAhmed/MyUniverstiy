<%-- 
    Document   : 3FaculityAdminViewSubjects
    Created on : Mar 14, 2016, 7:39:49 AM
    Author     : mohamed
--%>

<%@page import="java.text.SimpleDateFormat"%>
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
            <center>



                <%!  ResultSet Levels = null;
                    ResultSet Departments = null;
                    ResultSet rs = null;
                %>
                <%     try {

                        ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
                %>



                <div class="FormClass">

                    <form method="POST" action="OpenCourses">
                        <br/> 
                        <h1> <font style="color: #905;">Course Name:</font></h1> 
                        <br/>
                        <select name="Subject_Code" required="">
                            <%  try {

                                    rs = modelOfFaculityAdmin.ViewFaculityCourses(p.getFaculityID(), p.getUniversityID());

                                    while (rs.next()) {
                            %>
                            <option value="<%=rs.getInt("ID")%>"><%=rs.getString("SubjectName")%></option>

                            <%}%> 
                            <% } catch (Exception ex) {

                                    out.println("ViewFaculityCourses Error:" + ex.getMessage() + "in OpenCourses.jsp");
                                }%>
                        </select>
                        <br/>
                        <h1>  <font style="color: #905;"> Registration Date From: </font></h1><input type="date" name="RegisterationFromDate" required="" />
                        <br/>   
                        <h1><font style="color: #905;"> Registration Date To: </font></h1> <input type="date" name="RegisterationToDate" required="" />

                        <br/>     
                        <h1> <font style="color: #905;">Level:</font></h1><select name="Level" required="">  <%  try {
                                Levels = p.ViewLevelNames();
                                while (Levels.next()) {
                            %>                                              
                            <option value="<%=Levels.getInt("userlevel.ID")%>"><%=Levels.getString("userlevel.Level")%></option>

                            <% } %> 

                            <% } catch (Exception ex) {

                                    out.println("Levels Error:" + ex.getMessage() + "in OpenCourses.jsp");
                                }%>
                        </select>
                        <br/> 
                        <h1><font style="color: #905;" >Department:</font></h1> 
                        <select name="Department" required="">
                            <%  try {
                                    Departments = p.ViewDepartmentNames();
                                    while (Departments.next()) {
                            %>
                            <option value="<%=Departments.getInt("user_department.ID")%>"><%=Departments.getString("user_department.DepartmentName")%></option>

                            <%}%> 
                            <% } catch (Exception ex) {

                                    out.println("Departments Error:" + ex.getMessage() + "in OpenCourses.jsp");
                                }%>
                        </select>
                        <br/> 
                        <h1><font style="color: #905;">Term:</font></h1><select name="Term" required="">
                            <option value="FirstTerm">First Term</option>
                            <option value="SecondTerm">Second Term</option>
                            <option value="SummerTerm">Summer Term</option>
                        </select><br/>
                        <h1><font style="color: #905;">Term Begin in:</font></h1> <input type="date" name="OpenFromDate" required="" />
                        <br/>   
                        <h1><font style="color: #905;">Term End in: </font></h1> <input type="date" name="OpenToDate" required=""  />

                        <br/> 

                        <input  style="padding-left: 5px;
                                background-color: #333; border: 1px;  
                                color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                                type="submit"  name="Open" value="Open">                    </form>
                </div>
                <%
                    } catch (Exception ex) {
                        out.println("<br/><br/><br/><br/><br/>");
                        out.println("<font color='blue'>SomeThing Wrong is happened  ^_^ </font>");
                        out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                    }%>


            </center>
        </div>
        <%
                // Instantiate a Date object
                Date date = new Date();
                String s = date.toString();

                for (int i = 0; i < s.length(); i++) {
                    //out.println(s(i));

                }
                // display time and date using toString()
                out.println(date.toString());
                out.println("<br/>");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String date2 = sdf.format(new Date());
                out.println(date2); //15/10/2013
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