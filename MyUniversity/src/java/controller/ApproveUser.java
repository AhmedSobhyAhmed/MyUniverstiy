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
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class ApproveUser extends HttpServlet {

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
                if (p.getUserType() == 3 || p.getUserType() == 2) {

                    int code = 0;
                    boolean isEverythingIsOk = true;
                    //  String Approve = request.getParameter("Approve");
                    String ApproveButton = request.getParameter("ApproveButton");

                    try {

                        code = Integer.parseInt(request.getParameter("Code"));

                        // out.println(Subject_Code);
                    } catch (Exception ex) {
                        isEverythingIsOk = false;
                        out.println("<script type='text/javascript' > alert('Code an integer value please   ^_^ ');history.back();</script>");
                        System.out.println("Post ID Delet user ErrorServlet Error:" + ex.getMessage());
                    }
                    if (isEverythingIsOk) {

                        int rowAffected = 0;

                        try {
                            if (ApproveButton.equals("Delete")) {
                                rowAffected = p.DeleteUserMethod(code);
                            } else if (ApproveButton.equals("Approve")) {
                                rowAffected = p.AllowUserMethod(code);

                            }
                            if (rowAffected > 0) {
                                System.out.println("code=" + code);
                                System.out.println("ApproveButton=" + ApproveButton);
                               // out.println("<script type='text/javascript' > alert('Approve User  is done sucessfull ^_^ ');history.back();</script>");
                                if (p.getUserType() == 2) {
                                    response.sendRedirect("MyAccount.jsp?page=2UniversityAdminViewUsers");
                                } else if (p.getUserType() == 3) {
                                    response.sendRedirect("MyAccount.jsp?page=3FaculityAdminViewStudents");
                                }
                            } else {
                                out.println("<script type='text/javascript' > alert('Approve User  is Failed ^_^ ');history.back();</script>");
                            }

                        } catch (Exception ex) {
                            System.err.println("ApproveUser Servlet Error:" + ex.getMessage());
                            out.println("ApproveUser Servlet Error:" + ex.getMessage());
                            out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                        }

                    }//end of is every thing is ok or not
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
