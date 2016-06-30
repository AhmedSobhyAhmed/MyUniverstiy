/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class DeleteCourse extends HttpServlet {

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
                    ModelOfFaculityAdmin modelOfFaculityAdmin = new ModelOfFaculityAdmin();

                    try {

                        Subject_Code = Integer.parseInt(request.getParameter("Subject_Code"));

                        // out.println(Subject_Code);
                    } catch (Exception ex) {
                        out.println("<script type='text/javascript' > alert('Subject Code  is an integer value please   ^_^ ');history.back();</script>");
                        System.out.println("Subject ID DeleteCourse Servlet Error:" + ex.getMessage());
                        isEveryThingOK = false;
                    }
                    if (isEveryThingOK) {
                        int rowAffected;
                        try {
                            rowAffected = modelOfFaculityAdmin.DeleteCourseMethod(Subject_Code, p.getUserEmail());
                            if (rowAffected > 0) {
                                //out.println("<script type='text/javascript' > alert('Course was Deleting successfully ^_^ ');history.back();</script>");
                                response.sendRedirect("MyAccount.jsp?page=3FaculityAdminViewSubjects");
                                //  out.println("<script type='text/javascript' > alert('Post was Adding Successfully  ^_^ ');history.back();</script>");

                            } else {
                                out.println("<script type='text/javascript' > alert('Deleting Course was  Failed ^_^ ');history.back();</script>");
                            }

                        } catch (Exception ex) {
                            System.err.println("DeleteCourse Servlet Error:" + ex.getMessage());
                            out.println("DeleteCourse Servlet Error:" + ex.getMessage());
                            out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

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
