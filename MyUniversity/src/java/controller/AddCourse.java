/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class AddCourse extends HttpServlet {

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

                out.println("<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>" + "<br/>");
                out.println("<center>");
                MyPerson p = (MyPerson) session.getAttribute("UserObject");
                if (p.getUserType() == 3) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String RegisterCourseInDate = sdf1.format(new Date());
                    ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();
                    String CourseName = request.getParameter("CourseName");
                    String CourseDescription = request.getParameter("CourseDescription");
                    String CourseCode = request.getParameter("CourseCode");
                    String WrittenGradeString = request.getParameter("WrittenGrade");

                    String MidtermGradeString = request.getParameter("MidtermGrade");

                    String FinalGradeString = request.getParameter("FinalGrade");
                    boolean isEveryThingOK = true;
                    boolean isEveryThingOK2 = true;

                    if (CourseName.isEmpty() || CourseName == null
                            || CourseName.isEmpty() || CourseName == null
                            || CourseDescription.isEmpty() || CourseDescription == null
                            || CourseCode.isEmpty() || CourseCode == null
                            || WrittenGradeString.isEmpty() || WrittenGradeString == null
                            || MidtermGradeString.isEmpty() || MidtermGradeString == null
                            || FinalGradeString.isEmpty() || FinalGradeString == null) {

                        out.println("<script type='text/javascript' > alert('Please fill al fields   ^_^ ');history.back();</script>");
                        isEveryThingOK2 = false;
                    }
                    if (isEveryThingOK2) {
                        int WrittenGrade = 0;
                        int MidtermGrade = 0;
                        int FinalGrade = 0;
                        try {
                            /*               
                             out.println(request.getParameter("WrittenGrade"));
                             out.println(request.getParameter("MidtermGrade"));
                             out.println(request.getParameter("FinalGrade"));
                             out.println(request.getParameter("CourseName"));
                             out.println(request.getParameter("CourseDescription"));
                             out.println(request.getParameter("CourseCode"));
                             */
                            WrittenGrade = Integer.parseInt(WrittenGradeString);

                            MidtermGrade = Integer.parseInt(MidtermGradeString);

                            FinalGrade = Integer.parseInt(FinalGradeString);

                            //  System.out.println("1" + CourseName + "2" + CourseCode + "3" + CourseDescription + "4" + WrittenGrade + "5" + MidtermGrade + "6" + FinalGrade);
                        } catch (Exception ex) {
                            System.out.println("" + ex.getMessage());

                            out.println("<script type='text/javascript' > alert('Grades  are an integer value please   ^_^ ');history.back();</script>");
                            isEveryThingOK = false;
                        }
                        if (isEveryThingOK) {
                            int rowAffected;
                            try {
                                rowAffected = modelOfFaculityAdmin.AddCourseMethod(CourseName, CourseCode, CourseDescription, WrittenGrade, MidtermGrade, FinalGrade, p.getFaculityID(), p.getUniversityID(), p.getUserEmail(), RegisterCourseInDate);
                                if (rowAffected > 0) {
                                    out.println("<script type='text/javascript' > alert('Course was Adding successfully ^_^ ');history.back();</script>");
                                // response.sendRedirect("MyAccount.jsp?page=CoursePosts&Subject_Code="+Subject_Code+"");
                                    //  out.println("<script type='text/javascript' > alert('Post was Adding Successfully  ^_^ ');history.back();</script>");

                                } else {
                                    out.println("<script type='text/javascript' > alert('Adding Course was  Failed ^_^ ');history.back();</script>");
                                }

                            } catch (Exception ex) {
                                System.err.println("AddCourse Servlet Error:" + ex.getMessage());
                                out.println("AddCourse Servlet Error:" + ex.getMessage());
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                            }
                        }//end of isEveryThingOK or not
                        
                    }//if fields are not empty

                }//end of is this is faculity admin or not
                else {
                    out.println("<br/><br/><br/><br/><br/>");
                    out.println("<font color='blue'><h1>You shouldn't be here ^_^ </h1></font>");
                    out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                }
                out.println("</center>");

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
