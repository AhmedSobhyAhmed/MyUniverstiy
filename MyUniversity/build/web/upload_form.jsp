
<%@page import="models.ModelOfStudent"%>
<%@page import="models.ModelOfProfessor"%>
<%@page import="models.MyPerson"%>
<%@page import="Database.MyDatabase"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
       

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>
    </head>

    <body>
     
 <%! int Subject_Code;
                boolean IsUserRegistgerInThisSubjectOrNot;
            %>
                    <div id="rightcontentRigs">

       
           <% try {
                    Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
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

           <form method="post" action="UploadFileController" enctype="multipart/form-data">
            <center>
                <table border="1" width="25%" cellpadding="5">
                    <thead> 
                    <th colspan="3">Upload File</th>        
                    </thead>
                    <tbody>
                        <tr>    
                            <td>Title : </td>
                            <td><input type="text" name="title" size="30" required=""></td>
                        </tr>
                        <tr>    
                            <td>Type : </td>
                            <td>
                                <select name="File_Type" style=" width: 200px" required=""  >
                                     <option> </option>
                                    <option> Word</option>
                                    <option> Pdf</option>
                                    <option> PPt</option>
                                    <option> Text</option>
                                    <option> Excel</option>

                        </tr>
                        <tr>
                            <td>Choose File : </td>
                            <td><input type="file" name="file_uploaded" required="" /></td>
                            <td> <input type="hidden" name="Subject_Code"  value="<%=Subject_Code%>" /></td>
                        </tr>
                        <tr>
                            <td colspan="3"><center><input type="submit" value="Upload"></center></td>
                    </tr>
                    </tbody>             
                </table>
            </center>
        </form>

        <br><br>

        <table border="1" width="25%" cellpadding="5">
            <thead> 
            <th colspan="3">Uploaded Files</th>        
        </thead>
        <tbody>
            <tr>
                <td><center><b>Id</b></center><td><center><b>Title</b></center></td><td><center><b>File</b></center></td>
</tr>


<%
    try {
        Connection connection = null;
         MyDatabase ViewCourseDatabaseObject = MyDatabase.getInstance();
            connection = ViewCourseDatabaseObject.connect_to_DB();


String sqlString = "SELECT * FROM matrerial where SubjectID='"+Subject_Code+"'";
    Statement myStatement = connection.createStatement();
        ResultSet rs = myStatement.executeQuery(sqlString);

        if (!rs.isBeforeFirst()) {
%>
<tr>
    <td colspan="3"><center><%out.print("No Files!"); %></center></td>
</tr>
<%
    }

    while (rs.next()) {
%>
<tr>
    <td><center><%out.print(rs.getString("ID")); %></center></td>
<td><center><%out.print(rs.getString("Filename"));%></center></td>

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
    } catch (Exception e) {
        e.printStackTrace();
    }

%>

<div style="position: relative">
    <div style="position: fixed ; bottom: 0 ; width:100% ; text-align:center ">

    </div>
</div>  
<% }//end of IsUserRegistgerInThisSubjectOrNot
                    else{
                    out.println("<br/><br/><br/><br/><br/><br/><center><font color='red' ><h1>You didn't register in this course ^_^</h1></font></center> ");
                    }
            }//end of true Get Subject Code
            catch (Exception ex) {
                    out.println("" + ex.getMessage());
                }%> 
                    </div>
</body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }%>