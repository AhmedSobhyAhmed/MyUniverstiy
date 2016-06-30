/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ModelOfStudent;
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class RegisterCourses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if (session.getAttribute("UserObject") != null) {
                MyPerson p = (MyPerson) session.getAttribute("UserObject");
                if (p.getUserType() == 6) {
                    boolean isEveryThingOK = true;
                    String messages = "<center><br/><br/><br/><br/><br/><br/>";
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String StudentRegisterSubjectDate = sdf1.format(new Date());
                    String[] s = request.getParameterValues("SelectedSubjects");
                    String[] Term = null;
                    String[] OpenFromDate = null;
                    String[] OpenToDate = null;
                    int[] Subject_Code = null;
                    //  p= new ModelOfStudent();

                    ModelOfStudent modelOfStudent = new ModelOfStudent();
                    ResultSet rs = modelOfStudent.ViewRegisterCourses(p.getFaculityID(), p.getUniversityID(), p.getLevelID(), p.getDepartmentID(), p.getCode());

                    if (s == null || s.length == 0) {
                        out.println("<script type='text/javascript' > alert('Register Courses please   ^_^ ');history.back();</script>");
                        isEveryThingOK = false;

                    } else {
                        Subject_Code = new int[s.length];
                        Term = new String[s.length];
                        OpenFromDate = new String[s.length];
                        OpenToDate = new String[s.length];

                        for (int i = 0; i < s.length; i++) {
                            //out.println("<br/>s[=" + i + "]=" + s[i]);
                            try {
                                Subject_Code[i] = Integer.parseInt(s[i]);
                               // out.println("Subject_Code[i]" + Subject_Code[i] + "<br/>");
                                // out.println("Term[i]" + Term + "<br/>");

                            } catch (Exception ex) {
                                messages += "<br/> Subject_Code : " + Subject_Code[i] + "must be an integer please ^_^(Error:" + ex.getMessage() + ")";
                                //out.println("<script type='text/javascript' > alert('Subject Code  is an integer value please   ^_^ ');history.back();</script>");
                                System.out.println("Subject ID RegisterCourse Servlet Error:" + ex.getMessage());
                                isEveryThingOK = false;
                            }
                        }//end of for loop get SubjectsCodes
                        try {
                            int i = 0;
                            while (rs.next()) {
                                OpenFromDate[i] = rs.getString("OpenFromDate");
                                OpenToDate[i] = rs.getString("OpenToDate");

                                Term[i] = rs.getString("Term");
                                //  out.println("OpenFromDate of Subject_Code[" + i + "] is " + OpenFromDate[i]);
                                //out.println("OpenToDate of Subject_Code[" + i + "] is " + OpenToDate[i]);
                                //out.println("Subject_Code[" + i + "]  " + Subject_Code[i]);
                                i++;
                            }

                        } catch (Exception ex) {
                        }
                    }//end of else length>0

                    if (isEveryThingOK) {
                        int[] rowAffected = new int[s.length];
                        for (int i = 0; i < s.length; i++) {
                            try {
                                rowAffected[i] = modelOfStudent.StudentRegisterCourseMethod(Subject_Code[i], p.getCode(), StudentRegisterSubjectDate, Term[i], OpenFromDate[i], OpenToDate[i]);
                                if (rowAffected[i] > 0) {
                                    messages += "<br/> <font color='blue' >Subject_Code : " + Subject_Code[i] + "is registered Sucesfully  ^_^</font>";
                                    //out.println("<script type='text/javascript' > alert('Course was Registered successfully ^_^ ');history.back();</script>");

                                } else {
                                    // out.println("<script type='text/javascript' > alert('Registration Course was  Failed ^_^ ');history.back();</script>");
                                    messages += "<br/> <font color='red' >Subject_Code : " + Subject_Code[i] + "is registered Failed ,try again please or contact with your admin ^_^</font>";

                                }

                            } catch (Exception ex) {
                                System.err.println("RegisterCourse Servlet Error:" + ex.getMessage());
                                messages += "<br/>RegisterCourse Servlet Error:(" + ex.getMessage() + ")";
                                messages += "<br/><center><a href='MyAccount.jsp' >Home</a></center>" + "";

                            }
                        }//end of for loop

                    }//end of isEveryThingOK or not 
                    messages += "<br/><a href='MyAccount.jsp?page=Courses'>Courses </a></center>.";
                    out.println("" + messages);
                    /* try {
                     Thread.sleep(10000);
                     response.sendRedirect("MyAccount.jsp?page=Courses");

                     } catch (InterruptedException ex) {
                     Logger.getLogger(RegisterCourses.class.getName()).log(Level.SEVERE, null, ex);
                     }*/
                }//end of is this is faculity admin or not
                else {
                    out.println("<br/><br/><br/><br/><br/>");
                    out.println("<center><font color='blue'><h1>You shouldn't be here ^_^ </h1></font></center>");
                    out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                }
            }//end of if session is exit or not
            else {
                RequestDispatcher RD = request.getRequestDispatcher(response.encodeURL("index.jsp"));
                RD.forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
