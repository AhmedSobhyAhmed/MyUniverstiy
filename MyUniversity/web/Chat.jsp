<%@page import="java.sql.ResultSet"%>
<%@page import="models.MyPerson"%>
<!DOCTYPE html>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html >
    <head>
        <meta charset="UTF-8">
        <title>Chat Widget</title>


        <link rel="stylesheet" href="css/reset.css">

        <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'>







        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

        <link rel="stylesheet" type="text/css" href="css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="stylechat.css">

        <link rel="stylesheet" href="reset.css">
        <link rel="stylesheet" type="text/css" href="css/normalize.css">
        <link href='https://fonts.googleapis.com/css?family=Raleway:400,300,500,600,700' rel='stylesheet' type='text/css'>


        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>




        <script src="jquery-1.10.1.min"></script>
        <script src="script.js"></script>

<script>
            function showUser(str) {
                if (str == "") {
                    document.getElementById("txtHint").innerHTML = "";
                    return;
                } else {
                    if (window.XMLHttpRequest) {
                        // code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlhttp = new XMLHttpRequest();
                    } else {
                        // code for IE6, IE5
                        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlhttp.onreadystatechange = function () {
                        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                            document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
                        }
                    };
                    xmlhttp.open("GET", "3FaculityAdminViewRatingsAjax2.jsp?Code=" + str, true);
                    xmlhttp.send();
                }
            }
        </script>

    </head>

    <body>



        <div id="rightcontent2" >
       <div id="Mymenue" >
                    <ul> <li>
                            <a href="MyAccount.jsp"><i class="fa fa-home" style="font-size:28px;color:#02b3e4"></i>  Home</a>
                        </li>
          

                    </ul>           

                </div>
            <div id="topchat">
                <h1> Chat </h1>
            </div>

            <div class="container clearfix">
                <div class="people-list" id="people-list">
                    <div class="search">
                        <input type="text" placeholder="search" />
                        <i class="fa fa-search"></i>
                    </div>
                    <ul class="list">
                        <%
                            try {
                                ResultSet rs = p.ViewAllUsers(p.getFaculityID());
                                while (rs.next()) {
                        %>
                        <li class="clearfix">
                            <img src="images/my.png" alt="avatar" />
                            <div class="about">
                                <div class="name">
                                    <a href="Chat.jsp?to=<%= rs.getString("Code")%>">
                                        <%= rs.getString("FName")%> <%= rs.getString("LName")%>
                                    </a>
                                </div>
                                <div class="status">
                                    <i class="fa fa-circle online"></i> online
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
                <div class="chat">

                    <div class="chat-header clearfix">
                        <img src="images/my.png" alt="avatar" />

                        <div class="chat-about">
                            <div class="chat-with">Chat with <%= MyPerson.ReturnUserName(toParamCode)%> </div>
                            <div class="chat-num-messages">already 1 102 messages</div>
                        </div>
                        <i class="fa fa-star"></i>
                    </div> <!-- end chat-header -->

                    <div class="chat-history">
                        <% ResultSet resultMessages = p.ViewmyMessages(p.getCode(), toParamCode);%>

                        <ul>
                            <% if (!resultMessages.isBeforeFirst()) {
                                    out.println("<li>No messages here ^_^</li>");
                                } %>
                            <% String class_message_data = "", classMessage = "";
                                while (resultMessages.next()) {%>
                            <li class="clearfix">
                                <% if (p.getCode() == resultMessages.getInt("UserID")) {
                                        class_message_data = "align-right";
                                        classMessage = "other-message float-right";

                                %>   
                                <% } else {
                                    classMessage = "my-message";

                                %>
                                <% }%>
                                <div class="message-data <%= class_message_data%>">
                                    <span class="message-data-time" > <%= resultMessages.getString("MessageDate")%></span> &nbsp; &nbsp;
                                    <span class="message-data-name" >
                                        <% if (p.getCode() == resultMessages.getInt("UserID")) {

                                        %>   
                                        <%=MyPerson.ReturnUserName(p.getCode())%> 
                                        <% } else {

                                        %><%=MyPerson.ReturnUserName(resultMessages.getInt("UserID"))%> 
                                        <% }%>
                                    </span> <i class="fa fa-circle me"></i>
                                </div>
                                <div class="message <%= classMessage%>">

                                    <%= resultMessages.getString("Message")%>       
                                </div>
                            </li>


                            <% }//end of while %>


                        </ul>

                    </div> <!-- end chat-history -->

                    <div class="chat-message clearfix">
                        <form action="SendMessage" method="POST">
                            <textarea name="Message" id="message-to-send" placeholder ="Type your message" rows="3"></textarea>

                            <i class="fa fa-file-o"></i> &nbsp;&nbsp;&nbsp;
                            <i class="fa fa-file-image-o"></i>
                            <input type="hidden" name="RecieverID" value="<%=toParam%>" />


                            <input type="submit" value="Send" class="btn btn-info" />
                        </form>

                    </div> <!-- end chat-message -->

                </div> <!-- end chat -->

                <%
                        } catch (Exception ex) {
                            out.println("<script type='text/javascript' > alert('Code is an integer please ^_^ ');history.back();</script>");

                        }
                    }//end of else
                %>
            </div> <!-- end container -->


            <div id="end">

                Copy rights reserved© <a href="http://myuniversity.com">university.com 2016</a> 
            </div>


        </div>

        <script id="message-template" type="text/x-handlebars-template">
            <li class="clearfix">
            <div class="message-data align-right">
            <span class="message-data-time" >{{time}}, Today</span> &nbsp; &nbsp;
            <span class="message-data-name" >Olia</span> <i class="fa fa-circle me"></i>
            </div>
            <div class="message other-message float-right">
            {{messageOutput}}
            </div>
            </li>
        </script>

        <script id="message-response-template" type="text/x-handlebars-template">
            <li>
            <div class="message-data">
            <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
            <span class="message-data-time">{{time}}, Today</span>
            </div>
            <div class="message my-message">
            {{response}}
            </div>
            </li>
        </script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js'></script>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js'></script>

        <script src="index.js"></script>






    </body>
</html>
<% } else {
        response.sendRedirect(response.encodeURL("InvalidRequest.jsp"));

    }%>