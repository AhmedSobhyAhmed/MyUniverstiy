/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.ModelOfFaculityAdmin;
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class OpenCourses extends HttpServlet {

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
            HttpSession session = request.getSession();
            if (session.getAttribute("UserObject") != null) {
                MyPerson p = (MyPerson) session.getAttribute("UserObject");
                if (p.getUserType() == 3) {
                    boolean isEveryThingOK = true;
                    int Subject_Code = 0;
                    int levelID = 0;
                    int DepartmentID = 0;
                    /*  String Str = new String("Welcome to Tutorialspoint.com");

                     System.out.print("Return Value :" );
                     System.out.println(Str.substring(10) );

                     System.out.print("Return Value :" );
                     System.out.println(Str.substring(10, 15) );
                     }
                     }
                     This produces the following result:

                     Return Value : Tutorialspoint.com
                     Return Value : Tuto*/
                    ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
                    System.out.println("RegisterationFromDate:" + request.getParameter("RegisterationFromDate") + "<br/>");
                    System.out.println("RegisterationToDate:" + request.getParameter("RegisterationToDate") + "<br/>");
                    System.out.println("OpenFromDate:" + request.getParameter("OpenFromDate") + "<br/>");
                    System.out.println("OpenToDate:" + request.getParameter("OpenToDate") + "<br/>");

                    /*  out.println("Level:" + request.getParameter("Level") + "<br/>");
                     out.println("Department:" + request.getParameter("Department") + "<br/>");
                     out.println("Term:" + request.getParameter("Term") + "<br/>");
                     out.println("Registerdate:" + request.getParameter("Registerdate") + "<br/>");
                     */
                    String Term = request.getParameter("Term");
                    //  out.println("Term:" + Term + "<br/>");
                    String fromDateString = request.getParameter("RegisterationFromDate");
                    String toDateString = request.getParameter("RegisterationToDate");
                    String OpenFromDate = request.getParameter("OpenFromDate");
                    String OpenToDate = request.getParameter("OpenToDate");

                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String OpenCourseInDate = sdf1.format(new Date());
                    DateTimeFormatter formatter;
                    LocalDate fromDate = null;
                    LocalDate toDate = null;

                    if (Term.isEmpty() || Term == null
                            || fromDateString.isEmpty() || fromDateString == null
                            || toDateString.isEmpty() || toDateString == null
                            || OpenFromDate.isEmpty() || OpenFromDate == null
                            || OpenToDate.isEmpty() || OpenToDate == null) {
                        isEveryThingOK = false;
                        out.println("<script type='text/javascript' > alert('Please fill all fields correctly ^_^ ');history.back();</script>");

                    }

                    try {

                        Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));
                        levelID = Integer.parseInt(request.getParameter("Level"));
                        DepartmentID = Integer.parseInt(request.getParameter("Department"));
                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                        fromDate = LocalDate.parse(fromDateString, formatter);
                        toDate = LocalDate.parse(toDateString, formatter);

                        System.out.println("Subject_Code=" + Subject_Code);
                        System.out.println("levelID=" + levelID);
                        System.out.println("DepartmentID=" + DepartmentID);

                    } catch (Exception ex) {
                        out.println("<script type='text/javascript' > alert('Subject Code  is an integer value please   ^_^ ');history.back();</script>");
                        System.out.println("Subject ID OpenCourse Servlet Error:" + ex.getMessage());
                        isEveryThingOK = false;
                    }

                    try {

                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                        fromDate = LocalDate.parse(fromDateString, formatter);
                        toDate = LocalDate.parse(toDateString, formatter);

                    } catch (Exception ex) {
                        out.println("<script type='text/javascript' > alert('Registration Date From ,Registration Date To are  a date value please   ^_^ ');history.back();</script>");
                        System.out.println("Registration Date From ,Registration Date To in  OpenCourse Servlet Error:" + ex.getMessage());
                        isEveryThingOK = false;
                    }
                    if (isEveryThingOK) {
                        //2016-06-12
                       /* String[] fromDateArray = fromDateString.split("-");

                         String[] toDateArray = toDateString.split("-");
                         String OpenForYear = fromDateArray[2];
                         if (Term.equals("FirstTerm")) {
                         out.println("OpenForYear" + OpenForYear);

                         OpenForYear += OpenForYear + 1;
                         out.println("OpenForYear" + OpenForYear);
                         }*/
                        /*    String DayFrom = fromDateArray[2]; //fromDate.substring(8);
                         if (DayFrom.startsWith("0")) {
                         //out.println("DayFrom:" + DayFrom.substring(1, 2) + "<br/>");
                         DayFrom = DayFrom.substring(1, 2);
                         }
                         System.out.println("DayFrom:" + DayFrom + "<br/>");

                         String MonthFrom = fromDateArray[1];// fromDate.substring(5, 7);
                         if (MonthFrom.startsWith("0")) {
                         //out.println("MonthFrom:" + MonthFrom.substring(1, 2) + "<br/>");
                         MonthFrom = MonthFrom.substring(1, 2);
                         }
                         System.out.println("MonthFrom:" + MonthFrom + "<br/>");

                         String DayTo = toDateArray[2];//toDate.substring(8);
                         if (DayTo.startsWith("0")) {
                         //out.println("DayTo:" + DayTo.substring(1, 2) + "<br/>");
                         DayTo = DayTo.substring(1, 2);
                         }
                         System.out.println("DayTo:" + DayTo + "<br/>");

                         String MonthTo = toDateArray[1];//toDate.substring(5, 7);
                         if (MonthTo.startsWith("0")) {
                         //out.println("MonthTo:" + MonthTo.substring(1, 2) + "<br/>");
                         MonthTo = MonthTo.substring(1, 2);
                         }
                         System.out.println("MonthTo:" + MonthTo + "<br/>");

                         String OpenForYear = toDateArray[0];//toDate.substring(0, 4);
                         System.out.println("OpenForYear:" + OpenForYear + "<br/>");
                         */
                        //out.println("<br/>fromDateString:" + fromDateString);
                        // out.println("<br/>toDateString:" + toDateString);

                        if (fromDate.isEqual(toDate) || fromDate.isBefore(toDate)) {
                            int rowAffected = 0;
                            try {
                                rowAffected = modelOfFaculityAdmin.UpdateOpenCourseMethod(Subject_Code, Term, levelID, DepartmentID, p.getUserEmail(), fromDateString, toDateString, OpenCourseInDate, OpenFromDate, OpenToDate);
                                // DayFrom, MonthFrom, DayTo, MonthTo, OpenForYear, 
                                if (rowAffected > 0) {
                                    out.println("<script type='text/javascript' > alert('Course was Opening successfully ^_^ ');history.back();</script>");
                                    // response.sendRedirect("MyAccount.jsp?page=3FaculityAdminViewSubjects");

                                } else {
                                    out.println("<script type='text/javascript' > alert('Opening Course was  Failed ^_^ ');history.back();</script>");
                                }

                            } catch (Exception ex) {
                                System.err.println("OpenCourse Servlet Error:" + ex.getMessage());
                                out.println("OpenCourse Servlet Error:" + ex.getMessage());
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");
                            }
                        } else {
                            out.println("<script type='text/javascript' > alert('From Date must be less than or equal To Date');history.back();</script>");
                        }

                    }//end of isEveryThingOK or not 

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
