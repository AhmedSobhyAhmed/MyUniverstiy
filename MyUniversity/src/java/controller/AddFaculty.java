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
import models.ModelOfUniversityAdmin;
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class AddFaculty extends HttpServlet {

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

                ModelOfUniversityAdmin modelOfUniversityAdmin = new ModelOfUniversityAdmin();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String RegisterCourseInDate = sdf1.format(new Date());

                if (p.getUserType() == 2) {
                    String FacultyName = request.getParameter("FacultyName");
                    String SystemType = request.getParameter("SystemType");
                    boolean isEveryThingOk = true;
                    int SystemTypeID = 0;
                    if (FacultyName == null || FacultyName.isEmpty()
                            || SystemType == null || SystemType.isEmpty()) {
                        isEveryThingOk = false;
                        out.println("<script type='text/javascript' > alert('Please fill all fields^_^ ');history.back();</script>");

                    } else {

                        if (SystemType.equals("CreditHours")) {
                            SystemTypeID = 1;
                        } else if (SystemType.equals("Normal")) {
                            SystemTypeID = 2;
                        } else {
                            out.println("<script type='text/javascript' > alert('Invalid System Type ^_^ ');history.back();</script>");

                            isEveryThingOk = false;
                        }
                        if (isEveryThingOk) {

                            int rowAffected;
                            try {

                                rowAffected = modelOfUniversityAdmin.AddfacultyMethod(FacultyName, p.getUniversityID(), SystemTypeID, p.getUserEmail(), RegisterCourseInDate);
                                if (rowAffected > 0) {
                                    out.println("<script type='text/javascript' > alert('Faculty was Adding successfully ^_^ ');history.back();</script>");
                                    // response.sendRedirect("MyAccount.jsp?page=CoursePosts&Subject_Code="+Subject_Code+"");

                                } else {
                                    out.println("<script type='text/javascript' > alert('Faculty Registration was  Failed ^_^ ');history.back();</script>");
                                }

                            } catch (Exception ex) {
                                System.err.println("Addfaculty Servlet Error:" + ex.getMessage());
                                out.println("Addfaculty Servlet Error:" + ex.getMessage());
                                out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                            }
                        }//end of isEveryThingOK or not

                    }
                } //end of is this is faculity admin or not
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
