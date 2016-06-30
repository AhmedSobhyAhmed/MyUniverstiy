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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ModelOfMyAdmin;

/**
 *
 * @author Mohamed
 */
public class SendProblem extends HttpServlet {

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
            String UserName = request.getParameter("UserName");
            String UserEmail = request.getParameter("UserEmail");
            String UserMessage = request.getParameter("UserMessage");
            boolean isEveryThingOk = true;

            if (UserName == null || UserName.isEmpty()
                    || UserEmail == null || UserEmail.isEmpty()
                    || UserMessage == null || UserMessage.isEmpty()) {
                isEveryThingOk = false;
                out.println("<script type='text/javascript' > alert('Please fill all fields^_^ ');history.back();</script>");
            } else {
                if (isEveryThingOk) {

                    int rowAffected;
                    try {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                        String MessageDate = sdf1.format(new Date());

                        ModelOfMyAdmin modelOfMyAdmin = new ModelOfMyAdmin();
                        rowAffected = modelOfMyAdmin.SaveProblem(UserName, UserEmail, UserMessage, MessageDate);

                        if (rowAffected > 0) {
                         out.println("<script type='text/javascript' > alert('Message was Sending successfully ^_^ ');history.back();</script>");

                        } else {
                            out.println("<script type='text/javascript' > alert('Message Sending was  Failed ^_^ ');history.back();</script>");
                        }

                    } catch (Exception ex) {
                        System.err.println("SendProblem Servlet Error:" + ex.getMessage());
                        out.println("SendProblem Servlet Error:" + ex.getMessage());
                        out.println("<center><a href='index.jsp' >Home</a></center>");

                    }
                }//end of isEveryThingOK or not

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
