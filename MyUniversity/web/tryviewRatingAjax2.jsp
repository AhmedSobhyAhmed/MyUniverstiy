<%@page import="java.util.Date"%>
<%@page import="javax.servlet.RequestDispatcher"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.MyPerson"%>
<% if (session.getAttribute("UserObject") != null) { MyPerson p = (MyPerson) session.getAttribute("UserObject");
                String Comment = request.getParameter("mycomment");
                if (Comment.isEmpty() || Comment == null) {
                    out.println("<script type='text/javascript' > alert('Write Something  please   ^_^ ');history.back();</script>");

                } else {
                    int POSTID = 0;
                    int Subject_Code = 0;

                    try {
                        Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
                        POSTID = Integer.parseInt(request.getParameter("POSTID"));
                    } catch (Exception ex) {
                        out.println("<script type='text/javascript' > alert('POST ID is an integer value please   ^_^ ');history.back();</script>");
                    }
                    int rowAffected;
                    try {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String RegistrationCommentDate = sdf1.format(new Date());

                        rowAffected = p.AddCommentMethod(p.getCode(), Comment, POSTID,RegistrationCommentDate);

                        if (rowAffected > 0) {
//response.sendRedirect("MyAccount.jsp?page=CourseViewPOSTContents&POSTID=" +POSTID+"&Subject_Code="+Subject_Code+"");
                          //  response.sendRedirect("MyAccount.jsp?page=viewPost&POSTID=" + POSTID + "&Subject_Code=" + Subject_Code + "");

//  out.println("<script type='text/javascript' > alert('Comment was Adding Successfully  ^_^ ');history.back();</script>");
                        } else {
                            out.println("<script type='text/javascript' > alert('Comment was Failed ^_^ ');history.back();</script>");
                        }

                    } catch (Exception ex) {
                        System.err.println("AddComment Servlet Error:" + ex.getMessage());
                        out.println("AddComment Servlet Error:" + ex.getMessage());
                        out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                    }/* RequestDispatcher RD = request.getRequestDispatcher("index.jsp");
                     RD.forward(request, response);*/

                }//end of comment is empty or not
            }//end of if session is exit or not
            else {
                RequestDispatcher RD = request.getRequestDispatcher(response.encodeURL("index.jsp"));
                RD.forward(request, response);
            }%>