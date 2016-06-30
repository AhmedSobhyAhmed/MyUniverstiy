<%-- 
    Document   : 3FaculityAdminViewSubjects
    Created on : Mar 14, 2016, 7:39:49 AM
    Author     : mohamed
--%>

<%@page import="models.ModelOfStudent"%>
<%@page import="models.ModelOfUniversityAdmin"%>
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
        <% if (p.getUserType() == 6) {
                ModelOfStudent modelOfStudent = new ModelOfStudent();
             try{   ResultSet result = modelOfStudent.ViewStudentInstructors(p.getFaculityID());
        %>
        <div id="rightcontentRigs">
            <center>

                <form action="Rate" method="POST">
                    <h1> <font style="color: #905;">Instructors Names </font></h1> <br/>    
                    <select required="" name="Code">
                        <% try {
                                while (result.next()) {%>
                        <option value="<%= result.getInt("Code")%>">
                            <%= result.getString("FName")%> <%= result.getString("LName")%>

                        </option>
                        <%
                                }//end of result
                            }//end of try
                            catch (Exception ex) {
                                out.println("Some thing wrong is happened ^_^,Eror(" + ex.getMessage() + ")");
                            }
                        %>
                    </select>

                    <h1> <font style="color: #905;">Rate </font></h1> <br/> 
                    <select required="" name="rateGrade">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>

                    </select>
                    <br/>
                    <h1> <font style="color: #905;">Positives: </font></h1> <br/> 
                    <textarea name="Positives" required="" rows="5" cols="70" placeholder="Positives">
                    </textarea><br/>
                    <h1> <font style="color: #905;">  Negatives</font></h1> <br/>    
                    <textarea name="Negatives" required="" rows="5" cols="70" placeholder="Negatives">
                    </textarea><br/>
                    <input  style="padding-left: 5px;
                            background-color: #333; border: 1px;  
                            color: #FFF; text-align: center;width:200px; height: 40px; margin-left: 30px; margin-top: 20px;"
                            type="submit"  name="Rate" value="Rate">   
                </form>
                        <% }catch(Exception ex){
                        
                        out.println("<br/><font color='red'>Something wrong is happened,Eror:"+ex.getMessage()+"</font>");
                        }
                        
                        %>
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
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }
%>
