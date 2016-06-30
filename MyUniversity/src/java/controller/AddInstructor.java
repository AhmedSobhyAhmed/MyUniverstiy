/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class AddInstructor extends HttpServlet {

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
                    String OpeningFromDate = "";
                    request.getParameter("OpenFromDate");
                    String OpeningToDate = "";
                    request.getParameter("OpenToDate");
                    String Term = "";
                    request.getParameter("Term");

                    int SubjectCode = 0;
                    int UserCode = 0;
                    boolean isEveryThingOK = true;
                    try {

                        SubjectCode = Integer.parseInt(request.getParameter("SubjectCode"));

                        UserCode = Integer.parseInt(request.getParameter("UserCode"));

                    } catch (Exception ex) {
                        System.out.println("AddInstructor in Try covert string to number error:" + ex.getMessage());

                        out.println("<script type='text/javascript' > alert('SubjectCode and UserCode  are an integer value please   ^_^ ');history.back();</script>");
                        isEveryThingOK = false;
                    }
                    String[] subjectObject = {"", "", ""};
                    try {
                        subjectObject = modelOfFaculityAdmin.ViewSpecialFaculityCourse(SubjectCode, p.getFaculityID(), p.getUniversityID());

                        out.println("<br/>OpeningFromDate:" + subjectObject[0] + "<br/>");
                        out.println("OpeningToDate:" + subjectObject[1] + "<br/>");

                        out.println("Term:" + subjectObject[2] + "<br/>");

                    } catch (Exception ex) {
                        System.out.println("AddInstructor in Try get Subject Object error:" + ex.getMessage());

                        out.println("<script type='text/javascript' > alert('error in Trying getting Subject Object    ^_^ ');history.back();</script>");
                        isEveryThingOK = false;
                    }
                    for (int i = 0; i < subjectObject.length; i++) {
                        if (subjectObject[i].equals("")) {
                            out.println("<br/>subjectObject[" + i + "] is empty<br/> ");
                            isEveryThingOK = false;
                        }
                    }

                    if (isEveryThingOK) {
                        out.println("<br/>isEveryThingOK is ok ");

                        int rowAffected = 0;
                       // out.println("<br/>SubjectCode:" + SubjectCode + "<br/>UserCode: " + UserCode + "<br/>RegisterCourseInDate:" + RegisterCourseInDate + "<br/>Term:" + subjectObject[2] + "<br/>OpeningFromDate: " + subjectObject[0] + "<br/>OpeningToDate:" + subjectObject[1] + "<br/>Mail:" + p.getUserEmail() + "<br/>");

                        try {

                            rowAffected = modelOfFaculityAdmin.AddInstructorsMethod(SubjectCode, UserCode, subjectObject[2], RegisterCourseInDate, subjectObject[0], subjectObject[1], p.getUserEmail());
                            if (rowAffected > 0) {
                                //out.println("<br/>1" + SubjectCode + "<br/>2" + UserCode + "<br/>3" + RegisterCourseInDate + "<br/>4" + Term + "<br/>5" + OpeningFromDate + "<br/>6" + OpeningToDate + "<br/>");

                                out.println("<script type='text/javascript' > alert('Instructor was Adding successfully ^_^ ');history.back();</script>");
                                // response.sendRedirect("MyAccount.jsp?page=CoursePosts&Subject_Code="+Subject_Code+"");
                                //  out.println("<script type='text/javascript' > alert('Post was Adding Successfully  ^_^ ');history.back();</script>");

                            } else {
                                out.println("<script type='text/javascript' > alert('Adding Instructor was  Failed ^_^ ');history.back();</script>");
                            }

                        } catch (Exception ex) {
                            System.err.println("AddInstructor Servlet Error:" + ex.getMessage());
                            out.println("AddInstructor Servlet Error:" + ex.getMessage());
                            out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                        }
                    }//end of isEveryThingOK or not
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
