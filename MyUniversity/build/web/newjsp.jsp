<%-- 
    Document   : newjsp
    Created on : Mar 15, 2016, 5:16:51 PM
    Author     : Badr
--%>

<%@page import="models.ModelOfProfessor"%>
<%@page import="models.ModelOfStudent"%>
<%@page import="models.MyPerson"%>
<%@page import="Database.MyDatabase"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

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
    <br><br>
        
       

            <%! int Subject_Code;
                boolean IsUserRegistgerInThisSubjectOrNot;
            %>
            <%
                        try
                        {
                                 Connection connection = null;
         MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();
try {
                    Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
                
}
catch(Exception ex){out.println(""+ex.getMessage());


}
   IsUserRegistgerInThisSubjectOrNot = false;

                    // String Subject_Code2=    (String)session.getAttribute("Subject_Code");
                    if (p.getUserType() == 6) {

                        ModelOfStudent modelOfStudent = new ModelOfStudent();
                        ResultSet rs = modelOfStudent.ViewMyCourses(p.getCode());

                        while (rs.next()) {
                            //SubjectsStudentCode.add(rs.getInt("ID"));
                            if (rs.getInt("ID") == Subject_Code) {
                                IsUserRegistgerInThisSubjectOrNot = true;
                            }

                        }
                    } else if (p.getUserType() == 4 || p.getUserType() == 5) {

                        ModelOfProfessor modelOfProfessor = new ModelOfProfessor();
                        ResultSet rs = modelOfProfessor.ViewMyCoursesProfessor(p.getCode());

                        while (rs.next()) {
                            //  SubjectsAssistantCode.add(rs.getInt("ID"));

                            if (rs.getInt("ID") == Subject_Code) {
                                IsUserRegistgerInThisSubjectOrNot = true;
                            }
                        }
                    }
                    if(IsUserRegistgerInThisSubjectOrNot==true){
String sqlString = "SELECT * FROM matrerial where SubjectID='"+Subject_Code+"'";
                                Statement myStatement = connection.createStatement();
                                ResultSet rs=myStatement.executeQuery(sqlString);
%>
 <table border="1" width="25%" cellpadding="5">
            <thead> 
               <th colspan="3">Uploaded Files</th>        
            </thead>
                <tbody>
                    <tr>
                        <td><center><b>Id</b></center><td><center><b>Title</b></center></td><td><center><b>File</b></center></td>
                    </tr>
<%
                                if(!rs.isBeforeFirst())
                                {
                                    %>
                                        <tr>
                                        <td colspan="3"><center><%out.print("No Files!"); %></center></td>
                                        </tr>
                                    <%
                                }    
                                
                                while(rs.next())
                                {   
                            %>
                                    <tr>
                                        <td><center><%out.print(rs.getString("id")); %></center></td>
                                        <td><center><%out.print(rs.getString("Filename")); %></center></td>
                                       <td><center><a href="view_file.jsp?id=<%=rs.getString("ID")%>&File_Type=<%= rs.getString("Type")%>">View</a></center></td>
                                    </tr>
                            <%
                                }
                            %>
                            
                </tbody> 
        </table>
                            
                            <%
                                rs.close();
                                myStatement.close();
                            
                         }//end of IsUserRegistgerInThisSubjectOrNot
                    else{
                    out.println("<br/><br/><br/><br/><br/><br/><center><font color='red' ><h1>You didn't register in this course ^_^</h1></font></center> ");
                    }
                        }//end of true Connection to DB
                        
                        catch(Exception e){e.printStackTrace();}    
                        
                    %>
        
        <div style="position: relative">
            <div style="position: fixed ; bottom: 0 ; width:100% ; text-align:center ">
            </div>
        </div> 
    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }%>