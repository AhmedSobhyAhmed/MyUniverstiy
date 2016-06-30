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
import models.MyPerson;

/**
 *
 * @author Mohamed
 */
public class AddNews extends HttpServlet {

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
                String Post = request.getParameter("Post");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String RegistrationPostDate = sdf1.format(new Date());
                boolean isEveryThingisOK = true;
                if (Post == null || Post.isEmpty()) {
                    isEveryThingisOK = false;
                    out.println("<script type='text/javascript' > alert('Fill all fields please   ^_^ ');history.back();</script>");

                }

                int rowAffected;
                if (isEveryThingisOK) {
                    try {
                        rowAffected = p.AddPNewsMethod(p.getCode(), Post, p.getUniversityID(), p.getFaculityID(), RegistrationPostDate);

                        if (rowAffected > 0) {
                            //out.println("<script type='text/javascript' > alert('Post was success ^_^ ');history.back();</script>");
                            response.sendRedirect("MyAccount.jsp?page=News");
                            //  out.println("<script type='text/javascript' > alert('Post was Adding Successfully  ^_^ ');history.back();</script>");

                        } else {
                            out.println("<script type='text/javascript' > alert('Post was Failed ^_^ ');history.back();</script>");
                        }

                    } catch (Exception ex) {
                        System.err.println("AddPost Servlet Error:" + ex.getMessage());
                        out.println("AddPost Servlet Error:" + ex.getMessage());
                        out.println("<center><a href='MyAccount.jsp' >Home</a></center>");

                    }
                }//end of every thing is ok or not
                /* RequestDispatcher RD = request.getRequestDispatcher("index.jsp");
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
