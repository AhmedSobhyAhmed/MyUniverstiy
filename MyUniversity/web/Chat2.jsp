<%@page import="java.sql.ResultSet"%>
<%@page import="models.MyPerson"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <!--[if IE]>
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <![endif]-->
        <title> CHAT </title>
        <!-- BOOTSTRAP CORE STYLE CSS -->
        <link href="bootstrapChat.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <!-- <link rel="stylesheet" type="text/css" href="style.css"/>-->

    </head>
    <body style="font-family:Verdana">

        <div class="container">
            <div id="Mymenue" >
                <ul> <li>
                        <a href="MyAccount.jsp"><i class="fa fa-home" style="font-size:28px;color:#02b3e4"></i>  Home</a>
                    </li>


                </ul>           

            </div>


            <div class="row " style="padding-top:40px;">


                <h3 class="text-center" > CHAT  </h3>

                <br /><br />
                <div class="col-md-8">
                    <%! String toParam, fromParam;%>
                    <%
                        toParam = request.getParameter("to");
                        // fromParam = request.getParameter("from");
                        if (toParam == null || toParam.isEmpty()) {
                            out.println("<center><br/><br/><br/><br/><br/><font color='blue'><h3>No Messages here ^_^</h3></font></center>");
                        } else {
                            int toParamCode = 0;
                            try {
                                toParamCode = Integer.parseInt(toParam);


                    %>
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            RECENT CHAT HISTORY
                        </div>
                        <div class="panel-body">
                            <% ResultSet resultMesages = p.ViewmyMessages(p.getCode(), toParamCode);%>

                            <ul class="media-list">
                                <% if (!resultMesages.isBeforeFirst()) {
                                        out.println("<li>No messages here ^_^</li>");
                                    } %>
                                <% while (resultMesages.next()) {%>
                                <li class="media">

                                    <div class="media-body">

                                        <div class="media">
                                            <a class="pull-left" href="#">
                                                <% String img = "";
                                                %>     <%if (MyPerson.ReturnUserTypeID(resultMesages.getInt("UserID")) == 1) {

                                                        img = "Admin";
                                                    } else if (MyPerson.ReturnUserTypeID(resultMesages.getInt("UserID")) == 2) {
                                                        img = "universityAdmin";
                                                    } else if (MyPerson.ReturnUserTypeID(resultMesages.getInt("UserID")) == 3) {
                                                        img = "FacultyAdmin";
                                                    } else if (MyPerson.ReturnUserTypeID(resultMesages.getInt("UserID")) == 4) {
                                                        img = "professor1";
                                                    } else if (MyPerson.ReturnUserTypeID(resultMesages.getInt("UserID")) == 5) {
                                                        img = "professorasistant";
                                                    } else if (MyPerson.ReturnUserTypeID(resultMesages.getInt("UserID")) == 6) {
                                                        img = "student";
                                                    }


                                                %> 
                                                <!-- 
                                                                                             <img style="width:60px; height:60px; border:1px solid #cccccc;" class="img-circle"   src="images/myaccc.png"/>-->

                                                <img class="media-object img-circle " width="64px" height="64px" src="./imgChat/<%=img%>.png" />

                                            </a>
                                            <div class="media-body" >
                                                <%= resultMesages.getString("Message")%> 
                                                <br />
                                                <small class="text-muted">
                                                    <% if (p.getCode() == resultMesages.getInt("UserID")) {%>   
                                                    <%=MyPerson.ReturnUserName(p.getCode())%> 
                                                    <% } else {%><%=MyPerson.ReturnUserName(resultMesages.getInt("UserID"))%> 
                                                    <% }%>

                                                    | <%= resultMesages.getString("MessageDate")%> </small>
                                                <hr />
                                            </div>
                                        </div>

                                    </div>
                                </li>
                                <% }//end of while %>
                            </ul>
                        </div>
                        <form action="SaveMessage" method="POST">
                            <div class="panel-footer">
                                <div class="input-group">
                                    <textarea name="Message" rows="4" cols="20" class="form-control" placeholder="Enter Message">
                                    </textarea>
                                    <!-- <input type="text" name="Message" class="form-control" placeholder="Enter Message" />
                                    --><input type="hidden" name="RecieverID" value="<%=toParam%>" />

                                    <span class="input-group-btn">
                                        <input type="submit" value="Send" class="btn btn-info" />

                                    </span>

                                </div>
                            </div>
                        </form>
                    </div>
                    <%
                            } catch (Exception ex) {
                                out.println("<script type='text/javascript' > alert('Code is an integer please ^_^ ');history.back();</script>");

                            }
                        }//end of else
                    %>
                </div>


                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            USERS
                        </div>

                        <div class="panel-body">
                            <ul class="media-list">
                                <%
                                    try {
                                        ResultSet rs = p.ViewAllUsers(p.getFaculityID());
                                        while (rs.next()) {
                                %>
                                <li class="media">

                                    <div class="media-body">

                                        <div class="media">
                                            <a class="pull-left" href="#">
                                                <% String img = "";
                                                %>     <%if (rs.getInt("UserTypeID") == 1) {

                                                        img = "Admin";
                                                    } else if (rs.getInt("UserTypeID") == 2) {
                                                        img = "universityAdmin";
                                                    } else if (rs.getInt("UserTypeID") == 3) {
                                                        img = "FacultyAdmin";
                                                    } else if (rs.getInt("UserTypeID") == 4) {
                                                        img = "professor1";
                                                    } else if (rs.getInt("UserTypeID") == 5) {
                                                        img = "professorasistant";
                                                    } else if (rs.getInt("UserTypeID") == 6) {
                                                        img = "student";
                                                    }


                                                %>
                                                <img class="media-object img-circle" style="max-height:40px;" src="./imgChat/<%=img%>.png" />
                                            </a>
                                            <div class="media-body" >
                                                <a href="tryChat1.jsp?to=<%= rs.getString("Code")%>"> <h5><%= rs.getString("FName")%> <%= rs.getString("LName")%></h5>
                                                </a>
                                                <small class="text-muted"></small>
                                            </div>
                                        </div>

                                    </div>
                                </li>
                                <% }
                                    } catch (Exception ex) {
                                        out.println("Chat Error:" + ex.getMessage());

                                    }
                                %>

                            </ul>
                        </div>
                    </div>

                </div>

            </div>
        </div>

    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("index.jsp"));

    }%>