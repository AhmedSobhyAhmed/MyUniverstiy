<%@page import="models.MyPerson"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.ModelOfFaculityAdmin"%>
<% if (session.getAttribute("UserObject") != null) {
        MyPerson p = (MyPerson) session.getAttribute("UserObject");
%>
<html>
    <head>
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
        <% if (p.getUserType() == 3) { %>
        <form>
            <%
                ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
                ResultSet Instructors = modelOfFaculityAdmin.ViewMyUsersByUserTypeRatings(p.getFaculityID());

            %>
            <div id="rightcontentRigs">
                <center>

                    <form>


                        <h1> <font style="color: #905;">Instructors Names </font></h1> <br/>    
                        <select required="" name="Code" onchange="showUser(this.value)">
                            <option value=""></option>   
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


                    </form>
                    <br>
                    <div id="txtHint"><b>Person info will be listed here...</b></div>
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