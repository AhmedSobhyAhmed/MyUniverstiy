
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
<%


%>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>

        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="style.css"/>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>

       
      
    </head>

    <body>

        <%! int Subject_Code;
            boolean IsUserRegistgerInThisSubjectOrNot;
        %>
        <div id="rightcontentRigs">


            <% try {
                    Subject_Code = Integer.parseInt(request.getParameter("CourseCode"));


            %>

            <div id="Mymenue" >
                <ul>
                          <li>

                        <a href="index.jsp">
                            <!--       <form action="?page=CoursePosts" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Posts"/>
       
                                   </form>-->
                            Home
                        </a>
                    </li>
                    <li>

                        <a href="CourseGuestPosts.jsp?CourseCode=<%=Subject_Code%>">
                            <!--       <form action="?page=CoursePosts" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Posts"/>
       
                                   </form>-->
                            Posts
                        </a>
                    </li>
                    <li>

                        <a href="CourseGuestFiles.jsp?CourseCode=<%=Subject_Code%>">
                            <!--       <form action="?page=upload_form" method="POST">
                                       <input hidden="" type="text" name="Subject_Code" value="<%=Subject_Code%>" />
                                       <input type="submit" value="Files"/>
       
                                   </form>-->
                            Files
                        </a>
                    </li>




                </ul>
            </div>



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

                        String sqlString = "SELECT * FROM matrerial where SubjectID='" + Subject_Code + "'";
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
            <%    }//end of true Get Subject Code
                catch (Exception ex) {
                    out.println("<br/><br/><br/><br/><br/><br/><center><font color='red' ><h1>Error:" + ex.getMessage() + " ^_^</h1></font></center> ");
                    out.println("<br/><br/><br/><br/><br/><br/><center><font color='red' ><h1><a href='index.jsp'>Home</a></h1></font></center> ");

    }%> 
        </div>
    </body>
</html>

