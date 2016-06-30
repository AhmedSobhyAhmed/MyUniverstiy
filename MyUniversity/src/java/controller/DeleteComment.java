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
public class DeleteComment extends HttpServlet {

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

                int CommentId = 0;
                String Subject_Code = request.getParameter("Subject_Code");
                String POSTID = request.getParameter("POSTID");
                try {

                    CommentId = Integer.parseInt(request.getParameter("CommentID"));

                    // out.println(Subject_Code);
                } catch (Exception ex) {
                    out.println("<script type='text/javascript' > alert('Comment Id is an integer value please   ^_^ ');history.back();</script>");
                    System.out.println("Comment ID DeletCommentServlet Error:" + ex.getMessage());
                }
                int rowAffected;
                try {
                    rowAffected = p.DeleteCommentMethod(CommentId, p.getCode());

                    if (rowAffected > 0) {
                        //out.println("<script type='text/javascript' > alert('Post was success ^_^ ');history.back();</script>");
                        response.sendRedirect("MyAccount.jsp?page=CourseViewPost&POSTID=" + POSTID + "&Subject_Code=" + Subject_Code + "");
                      //  out.println("<script type='text/javascript' > alert('Post was Adding Successfully  ^_^ ');history.back();</script>");

                    } else {
                        out.println("<script type='text/javascript' > alert('Deleting Comment is Failed ^_^ ');history.back();</script>");
                    }

                } catch (Exception ex) {
                    System.err.println("DeleteComment Servlet Error:" + ex.getMessage());
                    out.println("DeleteComment Servlet Error:" + ex.getMessage());
                    out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                }/* RequestDispatcher RD = request.getRequestDispatcher("index.jsp");
                 RD.forward(request, response);*/

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
